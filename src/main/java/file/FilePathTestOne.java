package file;

import java.io.File;
import java.net.URL;

/**
 * Created with foruse.Ø
 * User: hzyuqi1
 * Date: 2017/9/22
 * Time: 9:57
 * To change this template use File | Settings | File Templates.
 */

public class FilePathTestOne {
    public static void main(String[] args) {
        try {
//            System.out.println(FileUtils.readFileToString(new File("test2.txt")));
            String path1 = "file/test2.txt";
            String path2 = "test2.txt";
            String path3 = "/file/test2.txt";

            String path4 = "file/test1.txt";
            String path5 = "test1.txt";
            String path6 = "/file/test1.txt";
            String path7 = "/test1.txt";

            URL url1 = FilePathTestOne.class.getClassLoader().getResource("");
            URL url2 = FilePathTestOne.class.getResource("");
            URL url3 = FilePathTestOne.class.getResource("/");

            //System.out.println(IOUtils.toString(in));
            System.out.println(url1);
            System.out.println(url2);
            System.out.println(url3);
            System.out.println(new File(path4).toURI().getPath());
            System.out.println(new File("file.log"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
