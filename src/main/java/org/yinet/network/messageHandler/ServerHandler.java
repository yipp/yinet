package org.yinet.network.messageHandler;
import org.yinet.business.LoginDataManage;
import org.yinet.network.messageHandler.dao.DataPool;
import org.yinet.network.model.Request;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<Request>{
	private OperationHandler operationHandler = null;

	private void messageTransimt(Channel channel,Request request) throws CloneNotSupportedException {
		operationHandler = (OperationHandler) DataPool.ctx.getBean("operationHandler");
		operationHandler.messageReceived(channel, request);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//客户端在
		System.out.println("用户进来"+ctx.channel().remoteAddress());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		//客户端下
		System.out.println("用户出去"+ctx.channel().remoteAddress());
		/**
		 * 得到string类的账号
		 * 用channel移除对应的账
		 * //key = channel value = 玩家账号(username)
		 */
		String username = LoginDataManage.playerIdMap.get(ctx.channel());
		Integer id = LoginDataManage.playerUsernameMap.get(username);
		//Channel channel = LoginDataManage.playerLoginMap.get(id);
		//System.out.println(username + "-:-"+id+"-:-"+channel);
		
		LoginDataManage.playerIdMap.remove(ctx.channel());
		/**
		 * 用账号移除对应的integer
		 * key = 账号username value = integer（数据库id�?
		 */
		LoginDataManage.playerUsernameMap.remove(username);
		/**
		 * 用integer移除channel
		 * key = integer value = channel
		 */
		LoginDataManage.playerLoginMap.remove(id);

		ctx.channel().close();
		
//		System.out.println(LoginDataManage.playerIdMap.size()+"----");
//		System.out.println(LoginDataManage.playerUsernameMap.size()+"----");
//		System.out.println(LoginDataManage.playerLoginMap.size()+"----");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("用户下线");
		String username = LoginDataManage.playerIdMap.get(ctx.channel());
		Integer id = LoginDataManage.playerUsernameMap.get(username);
		//Channel channel = LoginDataManage.playerLoginMap.get(id);
		//System.out.println(username + "-:-"+id+"-:-"+channel);
		
		LoginDataManage.playerIdMap.remove(ctx.channel());
		/**
		 * 用账号移除对应的integer
		 * key = 账号username value = integer（数据库id�?
		 */
		LoginDataManage.playerUsernameMap.remove(username);
		/**
		 * 用integer移除channel
		 * key = integer value = channel
		 */
		LoginDataManage.playerLoginMap.remove(id);
		
		ctx.channel().close();
		//super.exceptionCaught(ctx, cause);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
		//System.out.println(ctx.channel());
		//将消息发送到消息分发
		messageTransimt(ctx.channel(), request);
	}

}
