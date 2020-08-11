package com.waimung.luoxun.water;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.springframework.util.StringUtils;

public class Client {
	public static void main(String args[]) {
		String cmdInfor = "aa752062777777779d307a5e5e00c70000b400be00009001c406ec046807d5070000";
		send(cmdInfor);
	}

	public static String send(String testData) {
		String strReturn = null;
		try {
			// 要连接的服务端IP地址
			String host = "localhost";
			// 要连接的服务端对应的监听端口
			int port = 8888;
			// 将十六进制的字符串转换成字节数组
			byte[] testBytes = hexStrToBinaryStr(testData);

			// 1.建立客户端socket连接，指定服务器位置及端口
			Socket clientSocket = new Socket(host, port);

			// 2.得到socket读写流
			OutputStream os = clientSocket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			// 输入流
			InputStream is = clientSocket.getInputStream();

			// 3.利用流按照一定的操作，对socket进行读写操作
			os.write(testBytes);
			os.flush();
			clientSocket.shutdownOutput();

			// 接收服务器的响应
			int line = 0;
			byte[] buf = new byte[is.available()];

			// 接收收到的数据
			while ((line = is.read(buf)) != -1) {
				// 将字节数组转换成十六进制的字符串
				strReturn = BinaryToHexString(buf);
			}
			// 4.关闭资源
			is.close();
			pw.close();
			os.close();
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strReturn;
	}

	/**
	 * 将十六进制的字符串转换成字节数组
	 *
	 * @param hexString
	 * @return
	 */
	public static byte[] hexStrToBinaryStr(String hexString) {

		if (StringUtils.isEmpty(hexString)) {
			return null;
		}

//		hexString = hexString.replaceAll(" ", "");

		int len = hexString.length();
		int index = 0;

		byte[] bytes = new byte[len / 2];

		while (index < len) {

			String sub = hexString.substring(index, index + 2);

			bytes[index / 2] = (byte) Integer.parseInt(sub, 16);

			index += 2;
		}

		return bytes;
	}

	/**
	 * 将字节数组转换成十六进制的字符串
	 *
	 * @return
	 */
	public static String BinaryToHexString(byte[] bytes) {
		String hexStr = "0123456789ABCDEF";
		String result = "";
		String hex = "";
		for (byte b : bytes) {
			hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
			hex += String.valueOf(hexStr.charAt(b & 0x0F));
			result += hex + " ";
		}
		return result;
	}
}