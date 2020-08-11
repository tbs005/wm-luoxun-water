package com.waimung.luoxun.water.common;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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
		byte[] headOriginalBytes = messageHeader.getRawBytes();
		String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss"));
		
		String[] dateTimeArray = dateTime.split(" ");
		String[] dateArray = dateTimeArray[0].split("-");
		String[] timeArray = dateTimeArray[1].split(":");
		byte[] versionAndSerialNum = Arrays.copyOfRange(headOriginalBytes, 2, 6);
		byte[] dstAddr = Arrays.copyOfRange(headOriginalBytes, 12, 18);
		byte[] srcAddr = Arrays.copyOfRange(headOriginalBytes, 18, 24);
		
		byte[] crcBytes = new byte[25];
		System.arraycopy(versionAndSerialNum, 0, crcBytes, 0, versionAndSerialNum.length);
		
		crcBytes[4] = Integer.valueOf(timeArray[2]).byteValue();
		crcBytes[5] = Integer.valueOf(timeArray[1]).byteValue();
		crcBytes[6] = Integer.valueOf(timeArray[0]).byteValue();
		crcBytes[7] = Integer.valueOf(dateArray[2]).byteValue();
		crcBytes[8] = Integer.valueOf(dateArray[1]).byteValue();
		crcBytes[9] = Integer.valueOf(dateArray[0]).byteValue();
		
		System.arraycopy(srcAddr, 0, crcBytes, 10, dstAddr.length);
		System.arraycopy(dstAddr, 0, crcBytes, 16, srcAddr.length);
		
		crcBytes[22] = 0x00;
		crcBytes[23] = 0x00;
		crcBytes[24] = 0x03;//确认
		
		byte crc = ByteUtil.crc(crcBytes,crcBytes.length);
		int crcInt = ByteUtil.byte2ToUnsignedInt(crc);
		
		byteBuf.writeByte(64);//@
		byteBuf.writeByte(64);//@
		byteBuf.writeBytes(crcBytes);//流水号，版本号，时间
		byteBuf.writeByte(crcInt);//CRC
		byteBuf.writeByte(35);//#
		byteBuf.writeByte(35);//#
	}

	public void decode(ByteBuf byteBuf) throws Exception {
		byte[] originalBytes = new byte[byteBuf.capacity()];
		byteBuf.getBytes(0, originalBytes);
		
		//TODO crc check
		
		byte[] headOriginal = new byte[MessageHeader.Length];
		byteBuf.getBytes(0, headOriginal);
		log.info("MessageHeader -length:{},data:[{}]", headOriginal.length, ByteUtil.hexString(headOriginal));
		
		byte[] crcBytes = Arrays.copyOfRange(originalBytes, 2, originalBytes.length - 3);
		log.info("crcBytes -length:{},data:[{}]", crcBytes.length, ByteUtil.hexString(crcBytes));
		log.info("crc is :{}",ByteUtil.byte2ToUnsignedInt(ByteUtil.crc(crcBytes,crcBytes.length)));
		
		MessageHeader header = new MessageHeader();
		header.setStart(byteBuf);

		this.messageHeader = header;
		log.info("解析后的数据：header{}", gson.toJson(messageHeader));

		int type = ByteUtil.byte2ToUnsignedInt(byteBuf.readByte());
		int num = ByteUtil.byte2ToUnsignedInt(byteBuf.readByte());

		if(header.Length <= 0) {
			throw new DecoderException("数据格式错误，长度不够:" + header.getDataLen());
		}
		byte[] datas = new byte[header.getDataLen() - 2];
		byteBuf.readBytes(datas);
		log.info("数据16进制:{}，长度:{}", ByteUtil.hexString(datas), datas.length);

		int crc = ByteUtil.byte2ToUnsignedInt(byteBuf.readByte());

		byte[] dst = new byte[2];
		byteBuf.readBytes(dst);
		String end = null;
		try {
			end = new String(dst, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		log.info("数据type:{},num:{},crc:{},end:{}", type, num, crc, end);

//		DataType dataType = DataType.getByType(type);
//		switch (dataType) {
//		case COMPONENT_STATE:
//			log.info("接收到请求[{}]-[{}].", dataType.getType(), dataType.getDescription());
//			if (datas.length <= 0 || datas.length != ((ComponentState.length) * num)) {
//				log.info("请求[{}]长度不合法.", dataType.getDescription());
//				return;
//			}
//			ComponentStateOperation componentStateOperation = new ComponentStateOperation();
//			componentStateOperation.setSrcAddres(header.getSrcAddress());
//			componentStateOperation.setType(type);
//			componentStateOperation.setNum(num);
//			componentStateOperation.setDataType(dataType);
//			componentStateOperation.decode(datas);
//			this.messageBody = (Operation) componentStateOperation;
//			break;
//		case COMPONENT_ANALOG_QUANTITY:
//			log.info("接收到请求[{}]-[{}].", dataType.getType(), dataType.getDescription());
//			if (datas.length <= 0 || datas.length != (AnalogQuantity.length * num)) {
//				log.info("请求[{}]长度不合法.", dataType.getDescription());
//				return;
//			}
//			AnalogQuantityOperation analogQuantityOperation = new AnalogQuantityOperation();
//			analogQuantityOperation.setSrcAddres(header.getSrcAddress());
//			analogQuantityOperation.setType(type);
//			analogQuantityOperation.setNum(num);
//			analogQuantityOperation.setDataType(dataType);
//			analogQuantityOperation.decode(datas);
//			this.messageBody = (Operation) analogQuantityOperation;
//			break;
//		default:
//			log.info("未实现的操作，数据单元类型：{}，数据描述：{}", dataType.getType(), dataType.getDescription());
//			break;
//		}

		log.info("解析后的数据：header{},body{},type{},num{},crc{},end{}", gson.toJson(messageHeader),
				gson.toJson(messageBody), type, num, crc, end);

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

}
