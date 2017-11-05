package com.sizatn.udpserver.utils;

public class IntByteUtil {

	/**
	 * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和bytesToInt（）配套使用
	 * 
	 * @param value
	 *            要转换的int值
	 * @return byte数组
	 */
	public static byte[] intToBytes(int value) {
		byte[] bytes = new byte[4];
		bytes[3] = (byte) ((value >> 24) & 0xFF);
		bytes[2] = (byte) ((value >> 16) & 0xFF);
		bytes[1] = (byte) ((value >> 8) & 0xFF);
		bytes[0] = (byte) (value & 0xFF);
		return bytes;
	}

	/**
	 * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytes（）配套使用
	 * 
	 * @param bytes
	 *            byte数组
	 * @param offset
	 *            从数组的第offset位开始
	 * @return int数值
	 */
	public static int bytesToInt(byte[] bytes, int offset) {
		return (int) ((bytes[offset] & 0xFF) | ((bytes[offset + 1] & 0xFF) << 8) | ((bytes[offset + 2] & 0xFF) << 16)
				| ((bytes[offset + 3] & 0xFF) << 24));
	}

}
