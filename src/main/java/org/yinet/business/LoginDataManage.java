package org.yinet.business;

import java.util.HashMap;
import java.util.Map;

import io.netty.channel.Channel;

public class LoginDataManage {
	/**
	 * 对象和数据库中id的映射
	 */
	public static Map<Integer, Channel> playerLoginMap = new HashMap<Integer,Channel>();
	/**
	 * 玩家账号和数据库中id的映射
	 */
	public static Map<String, Integer> playerUsernameMap = new HashMap<String,Integer>();
	/**
	 * channel对应玩家账号的映射
	 */
	public static Map<Channel,String> playerIdMap = new HashMap<Channel,String>();
}
