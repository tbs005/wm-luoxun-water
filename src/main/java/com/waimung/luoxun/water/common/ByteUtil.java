package com.waimung.luoxun.water.common;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ByteUtil {
	/**
	 * 把单个字节转换成二进制字符串
	 */
	public static String byteToBin(byte b) {
		String zero = "00000000";
		String binStr = Integer.toBinaryString(b & 0xFF);
		if (binStr.length() < 8) {
			binStr = zero.substring(0, 8 - binStr.length()) + binStr;
		}
		return binStr;
	}

	/**
	 * 获取字节在内存中某一位的值,采用字符取值方式
	 */
	public static Integer getBitByByte(byte b, int index) {
		if (index >= 8) {
			return null;
		}
		Integer val = null;
		String binStr = byteToBin(b);
		val = Integer.parseInt(String.valueOf(binStr.charAt(index)));
		return val;
	}

	/**
	 * 获取字节在内存中多位的值,采用字符取值方式(包含endIndex位)
	 */
	public static Integer getBitByByte(byte b, int begIndex, int endIndex) {
		if (begIndex >= 8 || endIndex >= 8 || begIndex >= endIndex) {
			return null;
		}
		Integer val = null;
		String binStr = byteToBin(b);
		val = Integer.parseInt(binStr.substring(begIndex, endIndex + 1), 2);
		return val;
	}

	public static int byte2ToUnsignedShort(byte[] bytes, int off) {
		int high = bytes[off];
		int low = bytes[off + 1];
		return (high << 8 & 0xFF00) | (low & 0xFF);
	}

	public static byte[] unsignedShortToByte2(int s) {
		byte[] targets = new byte[2];
		targets[0] = (byte) (s >> 8 & 0xFF);
		targets[1] = (byte) (s & 0xFF);
		return targets;
	}

	public static int byte2ToUnsignedInt(byte bt) {
		return Integer.parseUnsignedInt(ByteUtil.byteToBin(bt), 2);
	}

	public static String getTimeStamp(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		int s = Integer.parseUnsignedInt(ByteUtil.byteToBin(bytes[0]), 2);
		int mm = Integer.parseUnsignedInt(ByteUtil.byteToBin(bytes[1]), 2);
		int h = Integer.parseUnsignedInt(ByteUtil.byteToBin(bytes[2]), 2);
		int d = Integer.parseUnsignedInt(ByteUtil.byteToBin(bytes[3]), 2);
		int M = Integer.parseUnsignedInt(ByteUtil.byteToBin(bytes[4]), 2);
		int y = Integer.parseUnsignedInt(ByteUtil.byteToBin(bytes[5]), 2);
		builder.append(y).append(M).append(d).append(h).append(mm).append(s);
		return builder.toString();
	}

	/**
	 * 字节数组转16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String hexString(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	private static ByteBuffer buffer = ByteBuffer.allocate(8);

	/**
	 * byte[]转Long
	 * 
	 * @param bytes
	 * @return
	 */
	public static long bytesToLong(byte[] bytes) {
		buffer.put(bytes, 0, bytes.length);
		buffer.flip();// need flip
		return buffer.getLong();
	}

	public static List<byte[]> getSplitByteArrayToList(int splitNum, int byteLength, byte[] data) {
		List<byte[]> splitList = new ArrayList<>();

		if (data.length % byteLength != 0) {
			return splitList;
		}

		byte[] bytes = null;
		int from, to;
		for (int i = 0; i < splitNum; i++) {
			bytes = new byte[byteLength];
			from = i * byteLength;
			to = from + byteLength;
			if (to > data.length)
				to = data.length;
			bytes = Arrays.copyOfRange(data, from, to);
			splitList.add(bytes);
		}
		return splitList;
	}

	public static String byteArrToBinStr(byte[] b) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			result.append(Long.toString(b[i] & 0xff, 2) + ",");
		}
		return result.toString().substring(0, result.length() - 1);
	}

	/**
	 * 把byte转为字符串的bit
	 */
	public static String byteToBit(byte b) {
		return "" + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1) + (byte) ((b >> 5) & 0x1)
				+ (byte) ((b >> 4) & 0x1) + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1) + (byte) ((b >> 1) & 0x1)
				+ (byte) ((b >> 0) & 0x1);
	}

	public static short getShort(byte[] b) {
		return (short) (((b[1] << 8) | b[0] & 0xff));
	}

	public static int bytes2Int(byte[] bytes) {
		int int1 = bytes[0] & 0xff;
		int int2 = (bytes[1] & 0xff) << 8;
		int int3 = (bytes[2] & 0xff) << 16;
		int int4 = (bytes[3] & 0xff) << 24;
		return int1 | int2 | int3 | int4;
	}

	/**
	 * 求校验和的算法
	 * 
	 * @param b 需要求校验和的字节数组
	 * @return 校验和
	 */
	public static byte crc(byte[] b, int len) {
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum = sum + b[i];
		}
		return (byte) (sum & 0xff);
	}
	
	  /**
     * int到byte[] 由低位到高位
     * @param i 需要转换为byte数组的整行值。
     * @return byte数组
     */
    public static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        result[3] = (byte)((i >> 24) & 0xFF);
        result[2] = (byte)((i >> 16) & 0xFF);
        result[1] = (byte)((i >> 8) & 0xFF);
        result[0] = (byte)(i & 0xFF);
        return result;
    }
}
