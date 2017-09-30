package classloadertest;

import com.google.common.collect.Lists;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import file.FilePathTestOne;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/9/26
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */

public class ClassLoaderTest {
    public static void main(String[] args) {
        List<byte[]> bytes = Lists.newArrayList();


        try {
            //bytes.add(Files.readAllBytes(Paths.get("E:\\JavaWorkSpace1\\sloth-udf\\target\\nim-udf-1.0-SNAPSHOT.jar")));
            //bytes.add(Files.readAllBytes(Paths.get("E:\\JavaWorkSpace1\\foruse\\target\\foruse-1.0-SNAPSHOT.jar")));
            //bytes.add(Files.readAllBytes(Paths.get("E:\\sloth_project\\sloth1\\sloth\\sloth-server\\target\\sloth-server-0.4-SNAPSHOT.jar")));


            ClassLoader classLoader = new ByteArrayClassLoader(ClassLoader.getSystemClassLoader(), bytes);
            //Class<?> cl = classLoader.loadClass("com.netease.sloth.server.common.UDAFConstants");
            Class<?> cl = classLoader.loadClass("file.FilePathTestOne");

            FilePathTestOne o = (FilePathTestOne) cl.newInstance();
            o.main(null);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
