package com.sizatn.udpserver.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

@Component
public class UdpServer {

	@Value("${udp.server.port}")
	private int port;

	public void initServer() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bs = new Bootstrap();
		bs.group(group);
		// 由于使用UDP协议，所以要用NioDatagramChannel来创建
		bs.channel(NioDatagramChannel.class);
		// 支持广播
		bs.option(ChannelOption.SO_BROADCAST, true);
		bs.handler(new UdpServerHandler());
		bs.bind(port).sync().channel().closeFuture().await();
	}

}
