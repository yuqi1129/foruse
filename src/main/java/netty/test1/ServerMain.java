package netty.test1;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/20 下午4:47
 */

public class ServerMain {

	public static void main(String[] args) {
		new NettyServer(10001).start();
	}

}
