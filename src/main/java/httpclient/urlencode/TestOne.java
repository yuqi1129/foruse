package httpclient.urlencode;

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


    }
}
