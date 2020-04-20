package multiLevelCacheSystem.exceptions;

public class InvalidConfigException extends Exception{
    public InvalidConfigException(String s){
        super("Invalid config");
    }
}
