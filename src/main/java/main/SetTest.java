package main;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;


import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/8/29
 * Time: 11:22
 * To change this template use File | Settings | File Templates.
 */

public class SetTest {
    private static final Set<Integer> INTEGER_SET = Sets.newHashSet(1,2,3,4,5,6,7,8);
    private static final Set<Integer> shareSet = Sets.newConcurrentHashSet(INTEGER_SET);
    private static final List<Integer> TEST_LIST = Lists.newArrayList(1,2,3,4,5,6,7,8);

    public static void main(String[] args) {

        Thread thread1 = new MyThread();
        //Thread thread2 = new MyThread();
        thread1.start();
        //thread2.start();

    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
//            for (Integer integer : shareSet) {
//                System.out.println(Thread.currentThread().getName() + " remove: " + integer);
//                shareSet.remove(integer);
//            }


//            Iterator<Integer> integerIterator = TEST_LIST.iterator();
//            while(integerIterator.hasNext()) {
//                Integer tmp = integerIterator.next();
//                if (tmp != 10){
//                    integerIterator.remove();
//                    System.out.println("remove: " + tmp );
//                    integerIterator = TEST_LIST.iterator();
//                }
//            }

//            for (Integer integer : TEST_LIST) {
//                if (integer < 10) {
//                    TEST_LIST.remove(integer);
//                }
//            }

//            int size = TEST_LIST.size();
//            for (int i = 0; i < size; i++) {
//                TEST_LIST.remove(TEST_LIST.get(i));
//            }


//            Gson gson = new Gson();
//            List<String> list = Lists.newArrayList();
//
//            list.add("abc");
//            list.add("def");
//            System.out.println(gson.toJson(list));
            //JsonArray jsonObject = new JsonParser().parse(gson.toJson(list)).getAsJsonObject().getAsJsonArray();
            //String test = jsonObject.get(0).getAsString();

            String sql = "CREATE TABLE\n" +
                    "  source(id INT, name VARCHAR(100), price INT) PROPERTIES (\n" +
                    "    category='source',\n" +
                    "    type='kafka',\n" +
                    "    version='0.9.0.1',\n" +
                    "    separator=',',\n" +
                    "    topic='dd.test',\n" +
                    "    brokers='hadoop693.lt.163.org:9092,hadoop694.lt.163.org:9092,hadoop711.lt.163.org:9092,hadoop712.lt.163.org:9092',\n" +
                    "    group_id='test'\n" +
                    "  );\n" +
                    "CREATE TABLE\n" +
                    "  sink (id INT, name VARCHAR(100), total_price INT) PROPERTIES (\n" +
                    "    category='sink',\n" +
                    "    type='mysql',\n" +
                    "    table_name='result',\n" +
                    "    url='jdbc:mysql://hadoop691.lt.163.org:3306/sloth?useUnicode=true&characterEncoding=UTF8',\n" +
                    "    username='sloth',\n" +
                    "    password='20170215'\n" +
                    "  );\n" +
                    "INSERT INTO\n" +
                    "  sink\n" +
                    "SELECT\n" +
                    "  id,\n" +
                    "  name,\n" +
                    "  SUM(price) as total_price\n" +
                    "FROM\n" +
                    "  (\n" +
                    "    SELECT\n" +
                    "      DISTINCT id,\n" +
                    "      name,\n" +
                    "      price\n" +
                    "    FROM\n" +
                    "      source\n" +
                    "  )\n" +
                    "GROUP BY\n" +
                    "  id,\n" +
                    "  name;";


            String sql1 = "CREATE TABLE xxx (id int) PROPERTIES (xxx = xxxx);";

            Pattern pattern = Pattern.compile("CREATE TABLE[\\s\\S]+?PROPERTIES[\\s\\S]+?\\(([\\s\\S]+?)\\);");

            //Pattern pattern = Pattern.compile("CREATE TABLE([\\s\\S]+?)PROPERTIES");
            //Pattern pattern = Pattern.compile("CREATE TABLE([a-zA-Z0-9 ,\\(\\)]?)PROPERTIES");
            Matcher matcher = pattern.matcher(sql);

            String copy = new String(sql);
            while (matcher.find()) {
                //System.out.println(matcher.group(1));
                String content = matcher.group(1);
                if (content.contains("category='sink'")) {
                    copy = copy.replace(content, "\n  category='sink',\n  type='file',\n  path='/tmp/file'\n");
                }
            }

            System.out.println(copy);

//            String test = "SlothRecord{ field=1,2,3 }\n" +
//                    "SlothRecord{ field=3,4,5 }\n";
//
//            Pattern pattern1 = Pattern.compile("SlothRecord\\{([\\s\\S]+?)\\}");
//            Matcher matcher1 = pattern1.matcher(test);
//            while(matcher1.find()) {
//                System.out.println(matcher1.group(1));
//            }
//
//            System.out.println(new Gson().toJson(Lists.newArrayList(1,2,4)));
//
            Integer a = 1;
            Integer b = 2;
            Integer c = 1;
            List<Integer> list = Lists.newArrayList(a, b, c);
            list.remove(1);
            System.out.print(list);

        }
    }
}
