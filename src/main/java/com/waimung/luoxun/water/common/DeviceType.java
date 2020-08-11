package com.waimung.luoxun.water.common;

public enum DeviceType {

	WL(198,"水位传感器"),WP(199,"水压传感器");

	private int type;
	private String remark;
	
	private DeviceType(int type, String remark) {
		this.type = type;
		this.remark = remark;
	}
}
