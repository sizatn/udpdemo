package com.sizatn.udpclient.client;

import java.net.InetSocketAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sizatn.udpclient.utils.IntByteUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

@Component
public class UdpClient {
	
	@Value("${udp.client.hostname}")
	private String hostname;
	
	@Value("${udp.client.port}")
	private int port;

	public void sendPackage() throws Exception {

		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group);
			b.channel(NioDatagramChannel.class);
			// 允许广播
			b.option(ChannelOption.SO_BROADCAST, true);
			// 设置消息处理器
			b.handler(new UdpClientHandler());
			Channel ch = b.bind(0).sync().channel();
			// 向网段内的所有机器广播UDP消息。
			ch.writeAndFlush(
					new DatagramPacket(Unpooled.copiedBuffer(createData()), new InetSocketAddress(hostname, port))).sync();
//			if (!ch.closeFuture().await(15000)) {
//				System.out.println("查询超时！");
//			}
		} catch (Exception e) {
			group.shutdownGracefully();
		}
	}
	
	private byte[] createData() {
		return IntByteUtil.intToBytes(1001);
		
	}

}
