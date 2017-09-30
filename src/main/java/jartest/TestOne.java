package jartest;

import java.io.File;
import java.util.List;
import java.util.jar.JarFile;
import java.util.logging.FileHandler;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/9/25
 * Time: 11:03
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {
    public static void main(String[] args) {
        TestOne testOne = new TestOne();
        File file = new File(testOne.getFileList());

        if (file.isFile()) {
            JarFile jarFile = null;
            try {
                jarFile = new JarFile(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(jarFile);
        } else {
            System.out.println("this is not a jar file");
        }
    }


    private String getFileList() {
        String path = getClass().getProtectionDomain().getCodeSource().toString();//.getCodeSource().getLocation().getPath();
        return path;
    }
}
