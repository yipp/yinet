package org.yinet.network.messageHandler;
import org.yinet.network.messageHandler.dao.DataPool;
public class Main {
	public static void main(String[] args) {
		/**
		 * 定时器，任务
		 */
		ServerConnection connection = (ServerConnection) DataPool.ctx.getBean("serverConnection");
		connection.Start(11020);//服务器开启
		
		
	}
}
