package netty.test1;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/20 下午5:08
 */

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

public class NettyClient {
	private int serverPort;

	public NettyClient(int serverPort) {
		this.serverPort = serverPort;
	}

	public void start() {

		Bootstrap bootstrap = new Bootstrap();
		bootstrap
				.group(new NioEventLoopGroup(2))
				.channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1000));
						socketChannel.pipeline().addLast(new ClientPointHandler());
					}
				});

		try {
			ChannelFuture f = bootstrap.connect("127.0.0.1", serverPort).sync();
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
