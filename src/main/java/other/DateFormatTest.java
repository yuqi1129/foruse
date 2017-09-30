package other;

import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/1/6
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 */

public class DateFormatTest {

    public static void main(String [] args){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd-HHmmssSSSZ");
//
//        Calendar calendar = Calendar.getInstance();
//
//        Date date = calendar.getTime();
//
//        try{
//            System.out.println(simpleDateFormat.format(date));
//        }catch (Exception e){
//            e.printStackTrace();
//        }


        String test = "xxx@corp.netease.com,xx@163.com,xxxxx@qq.com";
        String[] array = test.split(",");

        StringBuilder builder = new StringBuilder();
        for (String s : array) {
            builder.append(StringUtils.substringBefore(s, "@"));
        }

        System.out.println(builder.toString());
    }
}
