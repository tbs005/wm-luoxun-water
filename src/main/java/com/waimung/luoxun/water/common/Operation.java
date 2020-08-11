package com.waimung.luoxun.water.common;

public abstract class Operation extends MessageBody {

    public abstract OperationResult execute();
    
    public abstract void decode(byte[] datas) throws Exception;

}
