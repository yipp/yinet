package org.yinet.business.code;
import org.springframework.stereotype.Component;
import org.yinet.business.code.iplm.Decoder;
import org.yinet.tools.ByteArray;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
@Component
public class CMD_1Decoder implements Decoder<String>{
/**
 * 将客户端发送过来的字节数组进行反序列化
 */
	public String getObjectForDecoder(byte[] bytes) {
		String str;
		ByteBuf buffer = Unpooled.buffer();
		buffer.writeBytes(bytes);
		int size = buffer.readInt();
		if (size<=0) {
			str = "";
		}
		byte[] buf = new byte[size];
		buffer.readBytes(buf);
		str = new String(buf,ByteArray.CHARSET);
		return str;
	}

}
