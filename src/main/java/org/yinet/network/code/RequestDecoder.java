package org.yinet.network.code;
import java.util.List;

import org.yinet.network.model.Request;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class RequestDecoder extends ByteToMessageDecoder{
	/**
	 * 数据包的基本长度：包�?+1级协�?+数据长度�?
	 * 每个协议都是�?个int类型的基本数据占4个字�?
	 */
	public static int BASE_LENGTH = 4+4+4;
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
	
		if(buffer.readableBytes()>=BASE_LENGTH){
			//第一个可读数据包的起始位
			int beginIndex;
			while(true){
				//包头开始移动游标位置
				beginIndex = buffer.readerIndex();
				//标记初始读游标位
				buffer.markReaderIndex();
				if (buffer.readInt() == -777888) {//如果是包头
					break;//一直循环直到读取到包头为止跳出循环执行下一个语句
				}
			}
			//读取命令号
			int id = buffer.readInt();
			//读取数据长度
			int length = buffer.readInt();
			if(length<0){
				//没有数据过来就关闭链
				ctx.channel().close();
			}
			//数据包没到齐
			if(buffer.readableBytes()<length){
				buffer.readerIndex(beginIndex);//游标归到初始位置
				return;//直接返回等待数据完整
			}
			byte[] data = new byte[length];
			buffer.readBytes(data);
			Request message = new Request();
			message.setId(id);
			message.setDATA(data);
			out.add(message);
		}
		//数据不完整，等待完整的数据包
		return;
	}
}
