package classloadertest;

import java.util.List;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/9/26
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */

public class ByteArrayClassLoader extends ClassLoader {

    private List<byte[]> array;

    ByteArrayClassLoader(ClassLoader parent, List<byte[]> array) {
        super(parent);
        this.array = array;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        for (byte[] bytes : array) {
            Class cl = defineClass(name, bytes, 0, bytes.length);
            if (cl != null) {
                return cl;
            }
        }

        return super.findClass(name);
    }
}
