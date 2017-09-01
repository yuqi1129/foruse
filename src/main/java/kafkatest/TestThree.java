package kafkatest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/7/31
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */

public class TestThree {

    public static void main(String[] args) {
        //Pattern pattern = Pattern.compile("\"sid\":(\\d+),$");
        Pattern pattern = Pattern.compile("\"([0-9\\.]+)\"");
        String content = "asdfasf\"112.12.15.30\"".replace("\\","");
        System.out.println("Origin:" + content);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            System.out.println("haha");
            System.out.println(matcher.group(1));
        }
    }
}
