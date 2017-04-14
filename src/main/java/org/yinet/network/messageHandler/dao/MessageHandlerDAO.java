package org.yinet.network.messageHandler.dao;
import org.yinet.network.model.Request;
import io.netty.channel.Channel;
public interface MessageHandlerDAO extends Cloneable{
	
	/**
	 * 消息反序列化
	 */
	void MessageDecode(Request request);
	
	/**
	 * 消息处理
	 */
	void MessageHandler(Channel channel);
	
	/**
	 * 消息转发
	 */
	void MessageTransmit(Channel channel);
	/**
	 * 由于实现该接口的协议类都被spring ioc容器的单利模式管理
	 * 当多个线程同时访问同一个数据时会出现数据被更改的情况
	 * 所以谁用java提供的Cloneable类进行clone这样每个线程调用的都是不同的clone子类
	 * 避免了数据被修改的问题
	 * @return
	 */
	Object cloned();
}
