package classloadertest;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/10/11
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */

public class ClassA {
    public static void main(String[] args) {
        //IpGetProvinceUdf udf = new IpGetProvinceUdf();
        try {
            //System.out.print(udf.evaluate("43.230.90.181"));

            //NewIP ip = NewIP.getInstance();
            //System.out.println(ip.get("43.230.90.181"));
            System.out.print(JSONObject.class.getProtectionDomain().getCodeSource().getLocation().getPath());

            File file = new File("test");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileUtils.write(file, "test");

            File an = new File("test");
            an.renameTo(new File("test.txt"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
