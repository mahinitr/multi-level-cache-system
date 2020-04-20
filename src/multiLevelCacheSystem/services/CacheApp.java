package multiLevelCacheSystem.services;

import multiLevelCacheSystem.core.CacheSystem;
import multiLevelCacheSystem.core.Operation;
import multiLevelCacheSystem.core.ReplacementPolicy;
import multiLevelCacheSystem.exceptions.InvalidConfigException;
import multiLevelCacheSystem.exceptions.InvalidOperationException;
import multiLevelCacheSystem.exceptions.NotSupportedOperation;

class Config{
    static final int numLayers = 3;
    static final ReplacementPolicy policy = ReplacementPolicy.FIFO;
    static final  int[][] layers = new int[][]{
            {2, 1, 1},
            {3, 1, 1},
            {5, 1, 1}
    };
}

public class CacheApp {

    CacheSystem<Object, Object> cacheSystem;

    public CacheApp() throws InvalidConfigException {
        cacheSystem = new CacheSystem<>(Config.numLayers, Config.policy);
        cacheSystem.init(Config.layers);
    }

    public void execute(String command) throws InvalidOperationException, NotSupportedOperation{
        String[] words = command.split(" ");
        if(words.length < 1){
            throw new InvalidOperationException(command);
        }

        if(words[0].equals(Operation.READ.name())){
            if(words.length < 3){
                throw new InvalidOperationException(command);
            }
            cacheSystem.read(words[2]);
        }else if(words[0].equals(Operation.WRITE.name())){
            if(words.length < 4){
                throw new InvalidOperationException(command);
            }
            cacheSystem.write(words[2], words[3]);
        }else if(words[0].equals(Operation.STAT.name())){
            cacheSystem.stat();
        }else{
            throw new NotSupportedOperation(command);
        }
    }

}
