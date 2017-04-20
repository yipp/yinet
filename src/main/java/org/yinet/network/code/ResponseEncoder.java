package org.yinet.network.code;


import org.yinet.network.model.Response;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ResponseEncoder extends MessageToByteEncoder<Response>{

	@Override
	protected void encode(ChannelHandlerContext ctx, Response response, ByteBuf buffer) throws Exception {
		buffer.writeInt(-777888);//包头:请使用一个不常用到的int类型数据
		buffer.writeInt(response.getId());
		buffer.writeInt(response.getResult_Code());//结果码
		if(response.getDataLength()<=0 || response.getDATA() == null){
			buffer.writeInt(response.getDataLength());
		}
		else {
			buffer.writeInt(response.getDataLength());
			buffer.writeBytes(response.getDATA());//数据
		}
	}
}
