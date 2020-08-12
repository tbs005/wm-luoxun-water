package com.waimung.luoxun.water.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Device {
	private static final Logger log = LoggerFactory.getLogger(Device.class);
	public static final int length = 12;
	private byte[] status;
	private BitState status0;
	private BitState status1;
	private BitState status2;
	private BitState status3;
	private BitState status4;
	private BitState status5;
	private BitState status6;
	private BitState status7;
	private BitState status8;
	private BitState status9;
	private BitState status10;
	private BitState status11;
	private BitState status12;
	private BitState status13;
	private BitState status14;
	private BitState status15;
	private int bs;//基站
	private int rsrp;//信号强度dbm
	private int snr;//信噪比
	private int val;//水压Mpa 0.001Mpa,水位米 0.001米
	private int  waterTemperature;//水温
	private int  ambientTemperature;//环境温度
	private String statusString;


	public void setState(String status) {
		this.statusString = status;
		String bitState = new String(status);
		bitState = new StringBuffer(bitState).reverse().toString();
		char[] statusArray = bitState.toCharArray();
		BitState bit = null;
		for (int i = 0; i < statusArray.length; i++) {
			bit = new BitState();
			bit.setVal(String.valueOf(statusArray[i]));
			try {
				Method method = this.getClass().getMethod("setStatus" + i, BitState.class);
				method.invoke(this, bit);
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}


	public byte[] getStatus() {
		return status;
	}


	public void setStatus(byte[] status) {
		log.info("setStatus is :{}",ByteUtil.hexString(status));
		this.status = status;
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


	public BitState getStatus8() {
		return status8;
	}


	public void setStatus8(BitState status8) {
		this.status8 = status8;
	}


	public BitState getStatus9() {
		return status9;
	}


	public void setStatus9(BitState status9) {
		this.status9 = status9;
	}


	public BitState getStatus10() {
		return status10;
	}


	public void setStatus10(BitState status10) {
		this.status10 = status10;
	}


	public BitState getStatus11() {
		return status11;
	}


	public void setStatus11(BitState status11) {
		this.status11 = status11;
	}


	public BitState getStatus12() {
		return status12;
	}


	public void setStatus12(BitState status12) {
		this.status12 = status12;
	}


	public BitState getStatus13() {
		return status13;
	}


	public void setStatus13(BitState status13) {
		this.status13 = status13;
	}


	public BitState getStatus14() {
		return status14;
	}


	public void setStatus14(BitState status14) {
		this.status14 = status14;
	}


	public BitState getStatus15() {
		return status15;
	}


	public void setStatus15(BitState status15) {
		this.status15 = status15;
	}


	public int getBs() {
		return bs;
	}


	public void setBs(int bs) {
		this.bs = bs;
	}

	public void setBs(byte[] bytes) {
		log.info("setBs is :{}",ByteUtil.hexString(bytes));
		byte[] dst = new byte[4];
		dst[0] =bytes[0]; 
		dst[1] =bytes[1]; 
		dst[2] =0x00; 
		dst[3] =0x00; 
		int tmp = ByteUtil.bytes2Int(dst);
		
		BigInteger bigInteger = new BigInteger(dst);
		log.info("setBs BigInteger value is :{}",bigInteger.intValue());
		
		this.bs = tmp;
	}

	public int getRsrp() {
		return rsrp;
	}


	public void setRsrp(byte[] bytes) {
		log.info("setRsrp is :{}",ByteUtil.hexString(bytes));
		byte[] dst = new byte[4];
		dst[0] =bytes[0]; 
		dst[1] =0x00; 
		dst[2] =0x00; 
		dst[3] =0x00; 
		int tmp = ByteUtil.bytes2Int(dst);
		
		BigInteger bigInteger = new BigInteger(bytes);
		log.info("setRsrp BigInteger value is :{}",bigInteger.intValue());
		this.rsrp = tmp;
	}

	public void setRsrp(int rsrp) {
		this.rsrp = rsrp;
	}

	public int getSnr() {
		return snr;
	}


	public void setSnr(int snr) {
		this.snr = snr;
	}

	public void setSnr(byte[] bytes) {
		log.info("setSnr is :{}",ByteUtil.hexString(bytes));
		byte[] dst = new byte[4];
		dst[0] =bytes[0]; 
		dst[1] =0x00; 
		dst[2] =0x00; 
		dst[3] =0x00; 
		int tmp = ByteUtil.bytes2Int(dst);
		
		
		BigInteger bigInteger = new BigInteger(bytes);
		log.info("setSnr BigInteger value is :{}",bigInteger.intValue());
		this.snr = tmp;
	}
	
	public int getVal() {
		return val;
	}


	public void setVal(int val) {
		this.val = val;
	}

	public void setVal(byte[] bytes) {
		log.info("setVal is :{}",ByteUtil.hexString(bytes));
		byte[] dst = new byte[4];
		dst[0] =bytes[0]; 
		dst[1] =bytes[1]; 
		dst[2] =0x00; 
		dst[3] =0x00; 
		int tmp = ByteUtil.bytes2Int(dst);
		
		BigInteger bigInteger = new BigInteger(bytes);
		log.info("setVal BigInteger value is :{}",bigInteger.intValue());
		
		this.val = tmp;
	}

	public int getWaterTemperature() {
		return waterTemperature;
	}


	public void setWaterTemperature(int waterTemperature) {
		this.waterTemperature = waterTemperature;
	}

	public void setWaterTemperature(byte[] bytes) {
		log.info("setWaterTemperature is :{}",ByteUtil.hexString(bytes));
		BigInteger bigInteger = new BigInteger(bytes);
		int tmp = bigInteger.intValue();
		this.waterTemperature = tmp;
	}

	public int getAmbientTemperature() {
		return ambientTemperature;
	}


	public void setAmbientTemperature(int ambientTemperature) {
		this.ambientTemperature = ambientTemperature;
	}

	public void setAmbientTemperature(byte[] bytes) {
		log.info("setAmbientTemperature is :{}",ByteUtil.hexString(bytes));
		BigInteger bigInteger = new BigInteger(bytes);
		int tmp = bigInteger.intValue();
		this.ambientTemperature = tmp;
	}

	public String getStatusString() {
		return statusString;
	}


	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}
}
