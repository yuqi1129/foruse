package netty.test1;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/20 下午5:12
 */

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientPointHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf byteBuf = (ByteBuf) msg;

		byte[] bytes = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(bytes);

		System.out.println("Receive respond from server: " + new String(bytes));
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//try


		String message = "This is from Client ";

		for (int i = 0; i < 100; i++) {
			ByteBuf byteBuf = Unpooled.copiedBuffer((message + i + "\n").getBytes());
			ctx.writeAndFlush(byteBuf);
			Thread.currentThread().sleep(400);
		}

		ctx.writeAndFlush(Unpooled.copiedBuffer("end\n".getBytes()));

	}
}
