package com.waimung.luoxun.water.common;

public class ResponseMessage extends Message<OperationResult> {
    @Override
    public Class getMessageBodyDecodeClass(int type) {
        return null;
    }
}
