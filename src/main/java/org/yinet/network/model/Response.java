package org.yinet.network.model;

import org.springframework.stereotype.Component;

@Component
public class Response {
	private int id;
	/**
	 * 结果�?
	 */
	private int Result_Code;
	/**
	 * 数据
	 */
	private byte[] DATA;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getResult_Code() {
		return Result_Code;
	}
	public void setResult_Code(int result_Code) {
		Result_Code = result_Code;
	}
	public byte[] getDATA() {
		return DATA;
	}
	public void setDATA(byte[] dATA) {
		DATA = dATA;
	}
	public int getDataLength(){
		if(DATA != null){
			return DATA.length;
		}
		return 0;
	}
}
