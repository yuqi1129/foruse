package main;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.Set;

public class CollectionTests {
    public static final long A = 10;
    public static void main(String[] args) {

        Set<String> stringSet = Sets.newHashSet("A", "B");
        Gson gson = new Gson();
        System.out.println(gson.toJson(stringSet));
        System.out.println(gson.toJson(Lists.<String>newArrayList("A", "B")));

//        try (InputStream in = CollectionTests.class.getClass().getResourceAsStream("/tmp/test")) {
//            System.out.println("");
//        }

    }
}
