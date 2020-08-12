package com.waimung.luoxun.water.common;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;


public class DeviceOperation extends Operation {
	private static final Logger log = LoggerFactory.getLogger(Message.class);
	private String deviceId;
	private DeviceType deviceType;
	private Device[] deviceArrays = new Device[32];
	private static final Gson gson = new Gson();

	@Override
	public DeviceResult execute() {
		log.info("发送kafka消息，保存数据到数据库.");
		return null;
	}

	@Override
	public void decode(byte[] datas) throws Exception {
		log.info("处理数据:[{}],长度:[{}].", ByteUtil.hexString(datas), datas.length);
		List<byte[]> byteArrayList = ByteUtil.getSplitByteArrayToList(1, Device.length, datas);
		int index = 1;
		int arrIdx = 0;
		Device device = null;
		for (byte[] bytes : byteArrayList) {
			device = new Device();

			log.info(
					"解析[{}]第[{}]条数据，传感器状态:[{}],基站:[{}],信号强度:[{}],信噪比:[{}],水位/水压:[{}],水温:[{}],环境温度:[{}]",
					deviceType.getDeviceName(), index, device.getStatusString(),device.getBs(),device.getRsrp(),device.getSnr(),device.getVal(),device.getWaterTemperature(),device.getAmbientTemperature());
			this.deviceArrays[arrIdx] = device;
			index++;
			arrIdx++;
		}
	}
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public Device[] getDeviceArrays() {
		return deviceArrays;
	}

	public void setDeviceArrays(Device[] deviceArrays) {
		this.deviceArrays = deviceArrays;
	}
}
