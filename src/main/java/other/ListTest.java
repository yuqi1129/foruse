package other;

import com.google.common.collect.Lists;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/12/23
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */

public class ListTest {

    public static void main(String [] args){
        List<String> origin = Lists.newArrayList("1","2","3");

        List<String> copy = Lists.newArrayList(origin);
        origin.clear();
        SimpleDateFormat a = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");


        System.out.println(origin);
        System.out.println(copy);
        clearData(copy);
        System.out.println(copy);
        System.out.println(a.format(new Date()));
        clearData(a);
        System.out.println(a.format(new Date()));

    }

    private static void clearData(List<String> data){
        data.clear();
    }

    private static void clearData(SimpleDateFormat data){
        data = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");;
    }

}
