package com.waimung.luoxun.water.common;

public enum DeviceType {

	WATER_LEVEL(198,"水位传感器"),WATER_PRESSURE (199,"水压传感器");

	private int type;
	private String deviceName;
	
	private DeviceType(int type, String deviceName) {
		this.type = type;
		this.deviceName = deviceName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public static DeviceType getByType(int type) {
		for(DeviceType dType : values()) {
			if(dType.getType() == type) {
				return dType;
			}
		}
		return null;
	}
}
