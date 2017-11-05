package com.sizatn.udpserver.server;

import com.sizatn.udpserver.utils.IntByteUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
		// 通过content()来获取消息内容
		ByteBuf byteBuf = packet.content();
		byte[] bytes = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(bytes);
		int value = IntByteUtil.bytesToInt(bytes, 0);
		System.out.println(value);
		// bytes就是收到的数据
		int i = 0;
		if (value == 1001) {
			i = 1;
		}
		/**
		 * 重新 new 一个DatagramPacket对象，我们通过packet.sender()来获取发送者的消息。 重新发达出去！
		 */
		ctx.writeAndFlush(
				new DatagramPacket(Unpooled.copiedBuffer(response(i)), packet.sender()));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		cause.printStackTrace();
	}
	
	private byte[] response(int i) {
		return IntByteUtil.intToBytes(i);
	}

}
