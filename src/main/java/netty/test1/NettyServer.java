package netty.test1;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/20 下午4:47
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * accept logic in SocketUtils
 *
 */
public class NettyServer {

	int port;

	public NettyServer(int port) {
		this.port = port;
	}

	public void start() {
		ServerBootstrap bootstrap = new ServerBootstrap();


		EventLoopGroup bossEventLoop = new NioEventLoopGroup(2);
		EventLoopGroup childEventLoop = new NioEventLoopGroup(10);


		bootstrap.group(bossEventLoop, childEventLoop)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 10)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel socketChannel) throws Exception {
						socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1000));
						socketChannel.pipeline().addLast(new ServerPointHandler());
					}
				});

		ChannelFuture channelFuture;
		try {
			channelFuture = bootstrap.bind(port).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bossEventLoop.shutdownGracefully();
			childEventLoop.shutdownGracefully();
		}


	}
}
