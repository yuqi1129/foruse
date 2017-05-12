package flink.package1;

import com.google.common.io.CharStreams;

import org.apache.commons.lang3.StringUtils;

import sun.nio.cs.StandardCharsets;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/3/13
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */

public class TestClassLoader {

    public static void main(String[] args) {
        ClassLoader classLoader = TestClassLoader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("error.log");

        try {
            InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            String s = CharStreams.toString(reader);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }


}
