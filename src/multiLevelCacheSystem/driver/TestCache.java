package multiLevelCacheSystem.driver;

import multiLevelCacheSystem.exceptions.InvalidConfigException;
import multiLevelCacheSystem.exceptions.InvalidOperationException;
import multiLevelCacheSystem.exceptions.NotSupportedOperation;
import multiLevelCacheSystem.services.CacheApp;

public class TestCache {

    public static void main(String[] args) throws InvalidOperationException, NotSupportedOperation, InvalidConfigException {
        CacheApp cacheApp = new CacheApp();
        cacheApp.execute("WRITE KEY name mahesh");
        cacheApp.execute("WRITE KEY mob 8799");
        cacheApp.execute("WRITE KEY city blr");
        cacheApp.execute("WRITE KEY key1 val1");
        cacheApp.execute("WRITE KEY email mahi@gmail.com");
        cacheApp.execute("STAT");
        cacheApp.execute("READ KEY email");
        cacheApp.execute("READ KEY key1");
        cacheApp.execute("READ KEY city");
        cacheApp.execute("READ KEY name");
        cacheApp.execute("READ KEY mob");
        cacheApp.execute("READ KEY key2");
        cacheApp.execute("STAT");

    }

}
