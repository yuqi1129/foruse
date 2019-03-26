package netty.test1;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/20 下午5:12
 */

public class ClientMain {
	public static void main(String[] args) {
		new NettyClient(10001).start();
	}
}
