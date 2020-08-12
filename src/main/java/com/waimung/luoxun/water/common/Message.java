package com.waimung.luoxun.water.common;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderException;

public abstract class Message<Operation extends MessageBody> {
	private static final Logger log = LoggerFactory.getLogger(Message.class);
	private MessageHeader messageHeader;
	private Operation messageBody;
	private static final Gson gson = new Gson();

	public Operation getMessageBody() {
		return messageBody;
	}

	public abstract Class<Operation> getMessageBodyDecodeClass(int type);

	public void encode(ByteBuf byteBuf) {
		//应答报文
	}

	public void decode(ByteBuf byteBuf) throws Exception {
		byte[] originalBytes = new byte[byteBuf.capacity()];
		byteBuf.getBytes(0, originalBytes);
		
		byte[] headOriginal = new byte[MessageHeader.Length];
		byteBuf.getBytes(0, headOriginal);
		log.info("MessageHeader -length:{},header data:[{}]", headOriginal.length, ByteUtil.hexString(headOriginal));
		
		byte[] crcBytes = Arrays.copyOfRange(originalBytes, 32, originalBytes.length);
		log.info("crcBytes -length:{},data:[{}],crc is :{}", crcBytes.length, ByteUtil.hexString(crcBytes),ByteUtil.byte2ToUnsignedInt(ByteUtil.crc(crcBytes,crcBytes.length)));
		
		MessageHeader header = new MessageHeader();
		header.setStart(byteBuf);
		header.setLen(byteBuf);
		
		if((header.getLen()-header.Length+2) <14) {
			throw new DecoderException("数据格式错误，不够34位长度:" + header.getLen());
		}
		
		header.setPort(byteBuf); 
		header.setDeviceId(byteBuf);
		header.setTime(byteBuf);
		header.setSerialNum(byteBuf);
		header.setDeviceType(byteBuf);
		header.setStatus(byteBuf.readByte());
		header.setBatteryLevel(byteBuf);
		header.setAuxiliaryLevel(byteBuf);
		header.setRssi(byteBuf);

		byte[] datas = new byte[header.dataLen];
		byteBuf.readBytes(datas);
		log.info("扩展区数据:{}，长度:{}", ByteUtil.hexString(datas), datas.length);
		header.setDatas(datas);
		
		byte[] crc = new byte[2];
		byteBuf.readBytes(crc);
		header.setCrc(crc);
		
		this.messageHeader = header;
		log.info("解析后的header:{},status:{}", gson.toJson(messageHeader),messageHeader.getStatusAsString());



		DeviceType deviceType = DeviceType.getByType(header.getDeviceType());
		DeviceOperation deviceOperation = null;
		switch (deviceType) {
		case WATER_LEVEL:
			log.info("接收到-[{}][{}]上报数据.", deviceType.getType(), deviceType.getDeviceName());
			if (datas.length <= 0 || datas.length != (Device.length * 1)) {
				log.info("请求[{}]长度不合法.", deviceType.getDeviceName());
				return;
			}
			deviceOperation = new DeviceOperation();
			deviceOperation.setDeviceType(deviceType);
			deviceOperation.decode(datas);
			this.messageBody = (Operation) deviceOperation;
			break;
		case WATER_PRESSURE:
			log.info("接收到请求[{}]-[{}].", deviceType.getType(), deviceType.getDeviceName());
			if (datas.length <= 0 || datas.length != (Device.length * 1)) {
				log.info("请求[{}]长度不合法.", deviceType.getDeviceName());
				return;
			}
			deviceOperation = new DeviceOperation();
			deviceOperation.setDeviceType(deviceType);
			deviceOperation.decode(datas);
			this.messageBody = (Operation) deviceOperation;
			break;
		default:
			log.info("未实现的操作，设备类型：{}，设备名称：{}", deviceType.getType(), deviceType.getDeviceName());
			break;
		}

		log.info("解析后的数据：header{},body{},crc{},end{}", gson.toJson(messageHeader),
				gson.toJson(messageBody));

	}

	public MessageHeader getMessageHeader() {
		return messageHeader;
	}

	public void setMessageHeader(MessageHeader messageHeader) {
		this.messageHeader = messageHeader;
	}

	public void setMessageBody(Operation messageBody) {
		this.messageBody = messageBody;
	}

	public static void main(String[] args) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date(0);
		long time = date.getTime();
		System.out.println(sdf.format(date));
		System.out.println(sdf.format(new Date(time)));
		System.out.println(sdf.format(new Date(1585066141000L)));
		System.out.println(sdf.format(new Date(time+1585066141000L)));
		System.out.println(sdf.format(new Date(1585066141*1000L)));
	}
}
