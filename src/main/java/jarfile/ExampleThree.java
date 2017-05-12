package jarfile;

import org.mdkt.compiler.InMemoryJavaCompiler;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/4/17
 * Time: 14:40
 * To change this template use File | Settings | File Templates.
 */

public class ExampleThree {
    public static void main(String[] args) {
        try {
            addURL(new File("E:\\jar\\udftest-1.0-SNAPSHOT.jar").toURI().toURL());

            StringBuilder builder = new StringBuilder();
            builder.append("package com.netease.yuqi;\n");
            builder.append("public class HelloClass {\n");
            builder.append("public Integer hello() { return new udf.TestUDF().eval(1,1);\n}");
            builder.append("}");

            Class<?> helloClass = InMemoryJavaCompiler.compile("com.netease.yuqi.HelloClass", builder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addURL(URL url) throws Exception {
        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class clazz = URLClassLoader.class;

        Method method = clazz.getDeclaredMethod("addURL",new Class[] {URL.class});
        method.setAccessible(true);
        method.invoke(classLoader, new Object [] {url});

    }
}
