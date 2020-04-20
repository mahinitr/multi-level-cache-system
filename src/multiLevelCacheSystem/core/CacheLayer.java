package multiLevelCacheSystem.core;


import multiLevelCacheSystem.core.ReplacementPolicy;

import java.util.HashMap;
import java.util.LinkedList;

public class CacheLayer<U, V> {

    int readTime;
    int writeTime;
    int capacity;
    ReplacementPolicy policy;
    LinkedList<U> queue;
    HashMap<U, V> keyValueStore;


    public CacheLayer(int capacity, int readTime, int writeTime, ReplacementPolicy policy){
        this.readTime = readTime;
        this.writeTime = writeTime;
        this.capacity = capacity;
        this.policy = policy;
        queue = new LinkedList<>();
        keyValueStore = new HashMap<>();
    }

    boolean isFull(){
        return queue.size() >= capacity;
    }

    void applyPolicy(){
        switch (policy){
            case FIFO:
                U key = queue.poll();
                keyValueStore.remove(key);
        }
    }

    public void write(U key, V value){
        if(isFull()){
            applyPolicy();
        }
        queue.add(key);
        keyValueStore.put(key, value);
    }

    public V read(U key){
        V val = keyValueStore.get(key);
        return  val;
    }

    public int getReadTime() {
        return readTime;
    }

    public int getWriteTime() {
        return writeTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize(){
        return this.queue.size();
    }

}
