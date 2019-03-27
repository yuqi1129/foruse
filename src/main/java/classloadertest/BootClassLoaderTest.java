package classloadertest;
/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/3/27 下午2:25
 */

import java.net.URL;

public class BootClassLoaderTest {
	public static void main(String[] args) {
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i].toExternalForm());
		}

		System.out.println("---------------------------------------------\n");
		String[] extPath = System.getProperty("java.ext.dirs").split(":");
		String[] path = System.getProperty("java.class.path").split(":");

		for (String s : extPath) {
			System.out.println(s);
		}

		System.out.println("---------------------------------------------\n");
		for (String s : path) {
			System.out.println(s);
		}

		System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());

	}
}
