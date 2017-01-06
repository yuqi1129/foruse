package other;

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd-HHmmssSSSZ");

        Calendar calendar = Calendar.getInstance();

        Date date = calendar.getTime();

        try{
            System.out.println(simpleDateFormat.format(date));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
