package classloadertest;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/3/27 下午2:54
 */


import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

public class ClassLoaderContextTest {
	public static void testContextClassLoader() {
		//由SPI 提供对应的实现
		ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
		Iterator<Driver> iterator = loader.iterator();
		while (iterator.hasNext()) {
			Driver driver = (Driver) iterator.next();
			System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
		}
		System.out.println("current thread contextloader:"+Thread.currentThread().getContextClassLoader());

		System.out.println("current loader:" + ClassLoaderContextTest.class.getClassLoader());
		System.out.println("ServiceLoader loader:" + ServiceLoader.class.getClassLoader());
	}


	public static void main(String []arg){
		testContextClassLoader();
	}
}
