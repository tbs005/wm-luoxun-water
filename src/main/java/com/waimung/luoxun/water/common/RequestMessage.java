package com.waimung.luoxun.water.common;

public class RequestMessage extends Message<Operation>{

	@Override
	public Class getMessageBodyDecodeClass(int type) {
		return null;
	}

}
