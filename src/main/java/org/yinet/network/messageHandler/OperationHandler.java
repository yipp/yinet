package org.yinet.network.messageHandler;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Component;
import org.yinet.network.messageHandler.dao.DataPool;
import org.yinet.network.messageHandler.dao.MessageHandlerDAO;
import org.yinet.network.model.Request;
import io.netty.channel.Channel;
@Component
public class OperationHandler {
	/**
	 * 消息处理中心
	 * @param channel
	 * @param request
	 * @throws CloneNotSupportedException 
	 */
	public void messageReceived(Channel channel,Request request){
		//将客户端发过来的协议id拼接到"CMD_"字符串中
		String className = "CMD_"+request.getId();
		MessageHandlerDAO mhd = null;
		try{
			//根据拼接的字符串用spring ioc容器的依赖注入实例化协议实现具体对象
			mhd = (MessageHandlerDAO) DataPool.ctx.getBean(className);
		}catch(Exception e){
			throw new RuntimeException("找不到CMD_"+request.getId()+"这个脚本类");
		}
				//进行协议消息分发
				//1：为避免多线程同时访问同一个数据出现数据被修改的问题先clone出一个对象
			mhd =  (MessageHandlerDAO) mhd.cloned();
				//2：将客户端的数据交给MessageDecode(request)方法进行反序列化
			mhd.MessageDecode(request);
				//3：根据请求处理业务逻辑
			mhd.MessageHandler(channel);
				//4：将处理结果返回客户端
			mhd.MessageTransmit(channel);
	}
	public void channelClose(Channel channel){
		channel.close();
	}
}
