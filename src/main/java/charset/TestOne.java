package charset;

import org.apache.commons.codec.binary.Hex;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/11/22
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {

    public static void main(String[] args) {

        String st = "I am 余启";
        //default charset

        try {
            System.out.println("Default charset:" + Hex.encodeHexString(st.getBytes()));

            //iso-8859-1
            System.out.println("ISO8869 charset:" + Hex.encodeHexString(st.getBytes("ISO-8859-1")));

            System.out.println("GB2312 charset:" + Hex.encodeHexString(st.getBytes("GB2312")));

            System.out.println("GBK charset:" + Hex.encodeHexString(st.getBytes("GBK")));

            System.out.println("UTF-16 charset:" + Hex.encodeHexString(st.getBytes("UTF-16")));

            System.out.println("UTF-8 charset:" + Hex.encodeHexString(st.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
