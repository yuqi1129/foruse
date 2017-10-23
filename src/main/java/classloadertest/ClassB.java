package classloadertest;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/10/11
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */

public class ClassB {
    public ClassB() {
        try {
            Class<?> cl = ClassLoader.getSystemClassLoader().loadClass("classloadertest.classA");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
