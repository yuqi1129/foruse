package netty.test1;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/20 下午4:57
 */

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerPointHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf byteBuf = (ByteBuf) msg;

		int len = byteBuf.readableBytes();
		byte[] bytes = new byte[len];

		byteBuf.readBytes(bytes);


		System.out.println("Read from client: " + new String(bytes));


		String message = "nice\n";
		ByteBuf toWrite = Unpooled.copiedBuffer(message.getBytes());

		ctx.writeAndFlush(toWrite);
	}
}
