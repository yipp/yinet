package org.yinet.business.code.iplm;

public interface Decoder <T>{
	/**
	 * 将客户端发送过来的字节数组进行反序列化
	 */
	public T getObjectForDecoder(byte[] bytes);
}
