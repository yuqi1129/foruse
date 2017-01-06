package other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/1/6
 * Time: 18:10
 * To change this template use File | Settings | File Templates.
 */

public class RegTest {

    public static void main(String [] args){
        //
        Pattern pattern = Pattern.compile("([a-z]+)(\\d+)");
        //Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher("aaa123456bbb122345ccc12345");

        //System.out.println(matcher.matches()); // 只有全部配 全行

        /*
        matcher.find();
        System.out.println(matcher.start(2));
        System.out.println(matcher.end(2));
        System.out.println(matcher.group(2));

        System.out.println(matcher.lookingAt());// 只有开始起能配才返会
        System.out.println(matcher.find());//这个才是找个 任意匹配的
        */

        while(matcher.find()){
            System.out.println("group1 = " + matcher.group(1));
            System.out.println("group2 = " + matcher.group(2));
        }



    }
}
