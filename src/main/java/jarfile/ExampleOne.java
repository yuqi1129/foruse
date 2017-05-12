package jarfile;

import com.google.common.collect.Lists;

import java.io.FileInputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import other.DateFormatTest;


/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/4/11
 * Time: 20:12
 * To change this template use File | Settings | File Templates.
 */

public class ExampleOne {

    public static void main(String[] args) {
        String jarPath = "foruse-1.0-SNAPSHOT.jar";

        List<String> className = Lists.newArrayList();
        try {
            ZipInputStream zip = new ZipInputStream(new FileInputStream(jarPath));
            for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    String name = entry.getName().replace("/", ".");
                    className.add(name.substring(0, name.length() - ".class".length()));
                }
            }

            for (String file : className) {
                try {
                    Class<?> c = new ExampleOne().getClass().getClassLoader().loadClass(file);
                    if (DateFormatTest.class.isAssignableFrom(c)) {
                        System.out.println(c);
                    }
                } catch (Throwable e) {
                    //e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
