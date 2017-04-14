package org.yinet.network.messageHandler.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataPool {
	public static ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
//	public static ApplicationContext ctx = 
//			new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
}
