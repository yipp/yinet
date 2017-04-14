package org.yinet.business.code.iplm;

public interface Encoder <T>{
	/**
	 * 将要返回客户端的数据进行序列化并返回一个字节数组
	 * @param t
	 * @return
	 */
	public byte[] getObjectForEncoder(T t);
}
