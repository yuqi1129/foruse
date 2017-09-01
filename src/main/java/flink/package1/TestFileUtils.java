package flink.package1;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/8/21
 * Time: 16:06
 * To change this template use File | Settings | File Templates.
 */

public class TestFileUtils {

    public static void main(String[] args) {
        System.out.println(FileUtils.getUserDirectory());
        System.out.println(System.getProperty("user.dir"));
        System.out.println(new TestFileUtils().getClass().getClassLoader().getResource("").getPath());


        Path path = Paths.get("");
        System.out.println(path.toAbsolutePath().toString());
        System.out.println(TestFileUtils.class.getCanonicalName());

        File f = new File(TestFileUtils.class.getResource("").getPath());
        System.out.print(f);
    }
}
