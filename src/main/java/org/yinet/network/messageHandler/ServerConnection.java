package org.yinet.network.messageHandler;
import org.springframework.stereotype.Component;
import org.yinet.network.code.RequestDecoder;
import org.yinet.network.code.ResponseEncoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
@Component
public class ServerConnection {
	
	public void Start(int port){
		//创建服务类
		ServerBootstrap bootstrap = new ServerBootstrap();
		//创建boss和worker
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		
		try {
			//设置循环线程组事例
			bootstrap.group(boss, worker);
			//设置channel工厂
			bootstrap.channel(NioServerSocketChannel.class);
			//设置管道
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new RequestDecoder());
					ch.pipeline().addLast(new ResponseEncoder());
					ch.pipeline().addLast(new ServerHandler());
				}
			});
			//链接缓冲池列队大小2048
			bootstrap.childOption(ChannelOption.SO_BACKLOG, 2048);
			//绑定端口
			bootstrap.bind(port).sync();
			System.out.println("服务器开启");
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
}
