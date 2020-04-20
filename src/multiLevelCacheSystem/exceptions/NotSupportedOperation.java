package multiLevelCacheSystem.exceptions;

import multiLevelCacheSystem.core.ReplacementPolicy;

public class NotSupportedOperation extends  Exception{
   public NotSupportedOperation(String s ){
        super("Not supporting this operation - " + s);
    }
}
