package string;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/3/26 下午7:37
 */

import java.nio.charset.Charset;

public class TestCoding {
	public static void main(String[] args) {

		String test = "hello 中国";

		System.out.println(new String(test.getBytes(Charset.forName("UTF8"))));
		System.out.println(new String(test.getBytes(Charset.forName("GBK"))));
		System.out.println(new String(test.getBytes(Charset.forName("UTF16"))));
		System.out.println(new String(test.getBytes(Charset.forName("UNICODE"))));
	}
}
