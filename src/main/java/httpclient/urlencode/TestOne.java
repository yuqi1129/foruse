package httpclient.urlencode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/12/12
 * Time: 18:05
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {

    public static void main(String [] args){
        String china = "中国" ;
        String encode = null ;
        try{
            encode = URLEncoder.encode(china,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(china + " encode value equals:" + encode);

        china = null ;
        try{
            china = URLDecoder.decode(encode,"UTF-8");
            System.out.println(URLDecoder.decode("%E4%B8%AD","UTF-8"));
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(encode + " decode value equals:" + china);

        File f = null ;
        try {
            f = new File("data.txt");
            if (!f.exists()) {
                f.createNewFile();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(f.lastModified());
        try{
            OutputStream outputStream = new FileOutputStream(f);
            outputStream.write("test.txt".getBytes());
            System.out.println(f.lastModified());
        }catch (Exception e){
            e.printStackTrace();
        }




    }
}
