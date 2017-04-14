package org.yinet.business.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yinet.business.code.CMD_1Decoder;
import org.yinet.dao.clazz.User;
import org.yinet.dao.utils.UserDao;
import org.yinet.network.messageHandler.dao.CMDIple;
import org.yinet.network.model.Request;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
@Component
public class CMD_1 extends CMDIple{
	@Autowired
	private CMD_1Decoder cmd_1Decoder;
	@Autowired
	private UserDao dao;
	public void MessageDecode(Request request) {
		//将客户端的数据反序列化
		//String str = cmd_1Decoder.getObjectForDecoder(request.getDATA());
//System.out.println(str);
	}
	public void MessageHandler(Channel channel) {
		User user = dao.selectUser(50);//链接数据库
System.out.println(user);

		ByteBuf buf = Unpooled.buffer();
		buf.writeInt(3);//将一个3的数值返回回去
		byte[] data = new byte[buf.readableBytes()];
		buf.readBytes(data);
		//参数1：协议id， 2：结果码 ，3：要返回的数据
		super.setResponseObjcet(1,1, data);
	}
	/**
	 * 注MessageTransmit(Channel channel)和cloned()两个方法
	 * 只需要实现自父类就可以，不需要再做更改
	 */
	public void MessageTransmit(Channel channel) {
		super.MessageTransmit(channel);
	}

	public Object cloned() {
		return super.cloned();
	}

}
