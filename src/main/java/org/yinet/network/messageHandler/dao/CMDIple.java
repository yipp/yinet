package org.yinet.network.messageHandler.dao;


import org.yinet.network.model.Request;
import org.yinet.network.model.Response;

import io.netty.channel.Channel;
/**
 * 注MessageTransmit(Channel channel)和cloned()两个方法
 * 只需要实现自父类就可以，不需要再做更改
 */
public class CMDIple implements MessageHandlerDAO {
	private Response response;
	/**
	 * 接受到客户端信息并将信息提取出来
	 * MessageDecode(Request request)
	 */
	public void MessageDecode(Request request) {
		// TODO Auto-generated method stub

	}

	/**
	 * 业务逻辑处理
	 * MessageHandler(Channel channel)
	 */
	public void MessageHandler(Channel channel) {
		// TODO Auto-generated method stub

	}
	/**
	 * 返回客户端信息
	 * MessageTransmit(Channel channel)
	 */
	public void MessageTransmit(Channel channel) {
		try{
			channel.writeAndFlush(response);
			}catch(Exception e){
				throw new RuntimeException(getClass()+"数据回发错误");
			}
	}
/**
 * 将数据进行传输模型封装
 * @param id：协议码
 * @param result_Code：结果码
 * @param data：序列化好的字节数组数据
 */
	protected void setResponseObjcet(int id,int result_Code,byte[] data){
		response = (Response) DataPool.ctx.getBean("response");
		response.setId(id);
		response.setResult_Code(result_Code);
		response.setDATA(data);
	}
	/**
	 * 调用java的Cloneable父类的clone方法
	 */
	public Object cloned() {
		try {
			return clone();
		} catch (CloneNotSupportedException e) {
		}
		return null;
	}
/**
 * java的Cloneable的clone方法
 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
