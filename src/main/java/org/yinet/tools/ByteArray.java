package org.yinet.tools;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteArray {
	public static final Charset CHARSET = Charset.forName("UTF-8");
	private ByteBuf buf = Unpooled.buffer();
	
	public byte[] WriteInt(int i){
		
		buf.writeInt(i);
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		
		return bytes;
	}
	public byte[] WriteDouble(Double double1){
		buf.writeDouble(double1);
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		return bytes;
	}
	public byte[] WriteFloat(Float float1){
		buf.writeDouble(float1);
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		return bytes;
	}
	public byte[] WriteLong(Long long1){
		buf.writeLong(long1);
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		return bytes;
	}
	public byte[] WriteShort(short sho){
		buf.writeShort(sho);
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		return bytes;
	}
	public byte[] WriteChar(char cha){
		buf.writeChar(cha);
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		return bytes;
	}
	public byte[] WriteBoolean(boolean boo){
		if(boo == true){
			buf.writeInt(1);
			byte[] bytes = new byte[buf.readableBytes()];
			buf.readBytes(bytes);
			
			return bytes;
		}else {
			buf.writeInt(0);
			byte[] bytes = new byte[buf.readableBytes()];
			buf.readBytes(bytes);
			
			return bytes;
		}
	}

	public byte[] WriteByte(byte[] bytes){
		buf.readBytes(bytes);
		
		return buf.array();
	}
	public byte[] WriteString(String str){
		if(str == null||str.isEmpty()){
			buf.writeShort((short)0);
			byte[] bytes = new byte[buf.readableBytes()];
			buf.readBytes(bytes);
			return bytes;
		}
		byte[] bytes = str.getBytes();
		short len = (short)bytes.length;
		buf.writeShort(len);
		buf.writeBytes(bytes);
		buf.readBytes(bytes);
		return bytes;
	}
	public int ReadInt(){
		return buf.readInt();
	}
	public float ReadFloat(){
		return buf.readFloat();
	}
	public double ReadDouble(){
		return buf.readDouble();
	} 
	public long ReadLong(){
		return buf.readLong();
	}
	public short ReadShort(){
		return buf.readShort();
	}
	public char ReadChar(){
		return buf.readChar();
	}
	public String ReadString(){
		int size = buf.readShort();
		if(size<=0){
			return "";
		}
		byte[] bytes = new byte[size];
		buf.readBytes(bytes);
		return new String(bytes,CHARSET);
	}
	public boolean ReadBoolean(){
		int boo = buf.readInt();
		return boo != 0;
	}
	public int Length(){
		if(buf.readableBytes()>0){
			return buf.readableBytes();
		}else {
			return 0;
		}
	}
}
