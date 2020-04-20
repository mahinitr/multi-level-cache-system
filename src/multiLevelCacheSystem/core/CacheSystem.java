package multiLevelCacheSystem.core;

import multiLevelCacheSystem.exceptions.InvalidConfigException;

import java.util.ArrayList;
import java.util.List;

public class CacheSystem<U, V> {

    int numCacheLayers;
    List<CacheLayer<U, V>> cacheLayers;
    ReplacementPolicy policy;
    StatsTime readStatsTime;
    StatsTime writeStatsTime;


    public CacheSystem(int numCacheLayers, ReplacementPolicy policy){
        this.numCacheLayers = numCacheLayers;
        this.policy = policy;
        cacheLayers = new ArrayList<>();
        readStatsTime = new StatsTime(10);
        writeStatsTime = new StatsTime(10);
    }

    public void init(int[][] layers) throws InvalidConfigException{
        if(numCacheLayers != layers.length){
            throw new InvalidConfigException("");
        }
        for(int i=0; i<numCacheLayers; i++){
            int[] layer = layers[i];
            if(layer.length < 3){
                throw new InvalidConfigException("");
            }
            CacheLayer<U,V> cacheLayer = new CacheLayer<>(layer[0],layer[1],layer[2], policy);
            cacheLayers.add(cacheLayer);
        }
    }

    public V read(U key){
        V foundVal = null;
        int i;

        int totTime = 0;
        // search for layers
        for(i=0; i < numCacheLayers; i++){
            CacheLayer layer = cacheLayers.get(i);
            V val = (V)layer.read(key);
            totTime += layer.getReadTime();
            if( val != null){
                foundVal = val;
                break;
            }
        }

        //write in higher layers
        if(i < numCacheLayers){
            for(int j=i-1; j>=0; j--){
                CacheLayer layer = cacheLayers.get(j);
                layer.write(key, foundVal);
                totTime += layer.getWriteTime();
            }
        }

        readStatsTime.addToTottime(totTime);
        if(foundVal!=null) {
            System.out.println("Found:  " + key + " - " + foundVal + ";  Total Time: " + totTime);
        }else{
            System.out.println("Not Found for " + key + ";  Total Time: " + totTime);
        }

        return foundVal;
    }

    public void write(U key, V val){
        int i=0;
        int totTime=0;
        for(i=0; i < numCacheLayers; i++){
            CacheLayer layer = cacheLayers.get(i);
            V currVal = (V)layer.read(key);
            totTime += layer.getReadTime();
            if(currVal == val){
                break;
            } else{
                layer.write(key, val);
                totTime += layer.getWriteTime();
            }
        }

        writeStatsTime.addToTottime(totTime);
        System.out.println("Written; Total Time - " + totTime);

    }
    public void stat(){
        System.out.println("\nStats: \n Current Usage of each level:");
        for(int i=0; i < numCacheLayers; i++){
            CacheLayer layer = cacheLayers.get(i);
            System.out.println("Level-" + (i+1) + ": Occipied size " + layer.getSize() +
                    "; Total Capacity - " + layer.getCapacity());


        }
        System.out.println("Average Read time of last 10 reads - " + readStatsTime.getAvgTime());
        System.out.println("Average Read time of last 10 writes - " + writeStatsTime.getAvgTime());
        System.out.println("\n");
    }

}
