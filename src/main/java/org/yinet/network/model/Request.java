package org.yinet.network.model;

import org.springframework.stereotype.Component;

@Component
public class Request {
	/**
	 * id
	 */
	private int id;
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
