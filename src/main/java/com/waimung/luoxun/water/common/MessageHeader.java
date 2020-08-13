package com.waimung.luoxun.water.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.buffer.ByteBuf;

public class MessageHeader {
	public static final int Length = 20;
	public static final int dataLen = 12;//
	private byte[] start;// 0xAA,0X75 2字节
	private int len;// 1字节
	private int port;// 1字节
	private byte portByte;//1字节
	private int deviceId;// 4字节
	private byte[] deviceIdBytes;//4字节
	private int time;// 4字节
	private int serialNum;//2字节
	private byte[] serialNumBytes;//
	private int deviceType;//2字节
	private byte[] deviceTypeBytes;//
	private byte status;//1字节
	private BitState status0;
	private BitState status1;
	private BitState status2;
	private BitState status3;
	private BitState status4;
	private BitState status5;
	private BitState status6;
	private BitState status7;
	private int batteryLevel;//1字节
	private int auxiliaryLevel;//1字节
	private int rssi;//1字节
	private byte[] rawBytes;
	private byte[] crc;
	private byte[] datas;
	private String dateTime;
	
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
		this.portByte = byteBuf.readByte();
		int tmp = ByteUtil.byte2ToUnsignedInt(portByte);
		this.port = tmp;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public void setDeviceId(ByteBuf byteBuf) {
		byte[] dBytes = new byte[4];
		dBytes[0] =byteBuf.readByte(); 
		dBytes[1] =byteBuf.readByte(); 
		dBytes[2] =byteBuf.readByte(); 
		dBytes[3] =byteBuf.readByte(); 
		this.deviceIdBytes = dBytes;
		
		byte[] dst = new byte[4];
		dst[3] =dBytes[0]; 
		dst[2] =dBytes[1]; 
		dst[1] =dBytes[2]; 
		dst[0] =dBytes[3]; 
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
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.dateTime = sdf.format(new Date(tmp*1000L));
	}
	public int getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public void setDeviceType(ByteBuf byteBuf) {
		this.deviceTypeBytes = new byte[2];
		deviceTypeBytes[0]=byteBuf.readByte();
		deviceTypeBytes[1]=byteBuf.readByte();
		
		byte[] dst = new byte[4];
		dst[0] =deviceTypeBytes[0]; 
		dst[1] =deviceTypeBytes[1]; 
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
		
		this.serialNumBytes = new byte[2];
		serialNumBytes[0] = byteBuf.readByte();
		serialNumBytes[1] = byteBuf.readByte();
		
		byte[] dst = new byte[4];
		dst[0] =serialNumBytes[0]; 
		dst[1] =serialNumBytes[1]; 
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
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public BitState getStatus0() {
		return status0;
	}
	public void setStatus0(BitState status0) {
		this.status0 = status0;
	}
	public BitState getStatus1() {
		return status1;
	}
	public void setStatus1(BitState status1) {
		this.status1 = status1;
	}
	public BitState getStatus2() {
		return status2;
	}
	public void setStatus2(BitState status2) {
		this.status2 = status2;
	}
	public BitState getStatus3() {
		return status3;
	}
	public void setStatus3(BitState status3) {
		this.status3 = status3;
	}
	public BitState getStatus4() {
		return status4;
	}
	public void setStatus4(BitState status4) {
		this.status4 = status4;
	}
	public BitState getStatus5() {
		return status5;
	}
	public void setStatus5(BitState status5) {
		this.status5 = status5;
	}
	public BitState getStatus6() {
		return status6;
	}
	public void setStatus6(BitState status6) {
		this.status6 = status6;
	}
	public BitState getStatus7() {
		return status7;
	}
	public void setStatus7(BitState status7) {
		this.status7 = status7;
	}
	public byte getPortByte() {
		return portByte;
	}
	public void setPortByte(byte portByte) {
		this.portByte = portByte;
	}
	public byte[] getDeviceIdBytes() {
		return deviceIdBytes;
	}
	public void setDeviceIdBytes(byte[] deviceIdBytes) {
		this.deviceIdBytes = deviceIdBytes;
	}
	public byte[] getSerialNumBytes() {
		return serialNumBytes;
	}
	public void setSerialNumBytes(byte[] serialNumBytes) {
		this.serialNumBytes = serialNumBytes;
	}
	public byte[] getDeviceTypeBytes() {
		return deviceTypeBytes;
	}
	public void setDeviceTypeBytes(byte[] deviceTypeBytes) {
		this.deviceTypeBytes = deviceTypeBytes;
	}
}
