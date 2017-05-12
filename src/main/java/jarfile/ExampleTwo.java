package jarfile;

import org.mdkt.compiler.InMemoryJavaCompiler;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import scala.collection.mutable.StringBuilder;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/4/14
 * Time: 11:32
 * To change this template use File | Settings | File Templates.
 */

public class ExampleTwo {

    public static void main(String[] args) {
       URL[] urls;
        try {
            urls = new URL[]{new File("E:\\jar\\udftest-1.0-SNAPSHOT.jar").toURI().toURL()};
            ClassLoader classLoader = new URLClassLoader(urls);

            //Class<?> clazz = classLoader.loadClass("udf.TestUDF");

            StringBuilder builder = new StringBuilder();
            builder.append("package com.netease.yuqi;\n");
            builder.append("public class HelloClass {\n");
            builder.append("public Integer hello() { return new udf.TestUDF().eval(1,1);\n}");
            builder.append("}");

            Class<?> helloClass = InMemoryJavaCompiler.compile("com.netease.yuqi.HelloClass", builder.toString());


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
