package main;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/9/19
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 */

public class GsonExampleTest {
    public static void main(String[] args) {
        String jsonList = "[\"a\", \"b\", \"c\"]";
        Type type = new TypeToken<Set<String>>(){}.getType();

        Gson gson = new Gson();
        Set<String> list = gson.fromJson(jsonList, type);

        System.out.print(list);
    }
}
