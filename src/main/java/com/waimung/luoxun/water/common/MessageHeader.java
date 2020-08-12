package com.waimung.luoxun.water.common;

import io.netty.buffer.ByteBuf;

public class MessageHeader {
	public static final int Length = 20;
	public static final int dataLen = 12;//
	private byte[] start;// @，2字节
	private int len;// 1字节
	private int port;// 1字节
	private int deviceId;// 4字节
	private int time;// 4字节
	private int serialNum;//2字节
	private int deviceType;//2字节
	private byte status;//1字节
	private int batteryLevel;//1字节
	private int auxiliaryLevel;//1字节
	private int rssi;//1字节
	private byte[] rawBytes;
	private byte[] crc;
	private byte[] datas;
	
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public void setLen(ByteBuf byteBuf) {
		int tmp = ByteUtil.byte2ToUnsignedInt(byteBuf.readByte());
		this.len = tmp;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public void setPort(ByteBuf byteBuf) {
		int tmp = ByteUtil.byte2ToUnsignedInt(byteBuf.readByte());
		this.port = tmp;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public void setDeviceId(ByteBuf byteBuf) {
		byte[] dst = new byte[4];
		dst[3] =byteBuf.readByte(); 
		dst[2] =byteBuf.readByte(); 
		dst[1] =byteBuf.readByte(); 
		dst[0] =byteBuf.readByte(); 
		int tmp = ByteUtil.bytes2Int(dst);
		this.deviceId = tmp;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public void setTime(ByteBuf byteBuf) {
		byte[] dst = new byte[4];
		byteBuf.readBytes(dst);
		int tmp = ByteUtil.bytes2Int(dst);
		this.time = tmp;
	}
	public int getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public void setDeviceType(ByteBuf byteBuf) {
		byte[] dst = new byte[4];
		dst[0] =byteBuf.readByte(); 
		dst[1] =byteBuf.readByte(); 
		dst[2] =0x00; 
		dst[3] =0x00; 
		int tmp = ByteUtil.bytes2Int(dst);
		this.deviceType = tmp;
	}
	public byte[] getRawBytes() {
		return rawBytes;
	}
	public void setRawBytes(byte[] rawBytes) {
		this.rawBytes = rawBytes;
	}
	public int getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}
	public void setSerialNum(ByteBuf byteBuf) {
		byte[] dst = new byte[4];
		dst[0] =byteBuf.readByte(); 
		dst[1] =byteBuf.readByte(); 
		dst[2] =0x00; 
		dst[3] =0x00; 
		int tmp = ByteUtil.bytes2Int(dst);
		this.serialNum = tmp;
	}
	public byte getStatus() {
		return status;
	}
	public String getStatusAsString() {
		return ByteUtil.byteToBin(this.status);
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
	public void setBatteryLevel(ByteBuf byteBuf) {
		byte[] dst = new byte[4];
		dst[0] =byteBuf.readByte(); 
		dst[1] =0x00; 
		dst[2] =0x00; 
		dst[3] =0x00; 
		int tmp = ByteUtil.bytes2Int(dst);
		this.batteryLevel = tmp;
	}
	public int getAuxiliaryLevel() {
		return auxiliaryLevel;
	}
	public void setAuxiliaryLevel(int auxiliaryLevel) {
		this.auxiliaryLevel = auxiliaryLevel;
	}
	public void setAuxiliaryLevel(ByteBuf byteBuf) {
		byte[] dst = new byte[4];
		dst[0] =byteBuf.readByte(); 
		dst[1] =0x00; 
		dst[2] =0x00; 
		dst[3] =0x00; 
		int tmp = ByteUtil.bytes2Int(dst);
		this.auxiliaryLevel = tmp;
	}
	public byte[] getCrc() {
		return crc;
	}
	public void setCrc(byte[] crc) {
		this.crc = crc;
	}
	public int getRssi() {
		return rssi;
	}
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}
	public void setRssi(ByteBuf byteBuf) {
		byte[] dst = new byte[4];
		dst[0] =byteBuf.readByte(); 
		dst[1] =0x00; 
		dst[2] =0x00; 
		dst[3] =0x00; 
		int tmp = ByteUtil.bytes2Int(dst);
		this.rssi = tmp;
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
	public byte[] getDatas() {
		return datas;
	}
	public void setDatas(byte[] datas) {
		this.datas = datas;
	}
}
