package multiLevelCacheSystem.exceptions;

public class InvalidOperationException extends  Exception{
    public InvalidOperationException(String s){
        super("Invalid command/operation");
    }
}
