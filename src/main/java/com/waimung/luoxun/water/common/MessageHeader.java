package com.waimung.luoxun.water.common;

import io.netty.buffer.ByteBuf;

public class MessageHeader {
	public static final int Length = 16;
	private byte[] start;// @，2字节
	private int len;// 1字节
	private int port;// 1字节
	private int deviceId;// 4字节
	private String time;// 4字节
	private String serialNum;//2字节
	private String deviceType;//2字节
	private byte status;//1字节
	private int batteryLevel;//1字节
	private int auxiliaryLevel;//1字节
	private int rssi;//1字节
	private byte[] rawBytes;
	private int lenBytes;//
	
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public byte[] getRawBytes() {
		return rawBytes;
	}
	public void setRawBytes(byte[] rawBytes) {
		this.rawBytes = rawBytes;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public int getBatteryLevel() {
		return batteryLevel;
	}
	public void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}
	public int getAuxiliaryLevel() {
		return auxiliaryLevel;
	}
	public void setAuxiliaryLevel(int auxiliaryLevel) {
		this.auxiliaryLevel = auxiliaryLevel;
	}
	public int getRssi() {
		return rssi;
	}
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}
	public byte[] getStart() {
		return start;
	}
	public void setStart(byte[] start) {
		this.start = start;
	}
	
	public void setStart(ByteBuf byteBuf) {
		byte[] dst = new byte[2];
		byteBuf.readBytes(dst);
		this.start = dst;
	}
	public int getLenBytes() {
		return lenBytes;
	}
	public void setLenBytes(int lenBytes) {
		this.lenBytes = lenBytes;
	}
}
