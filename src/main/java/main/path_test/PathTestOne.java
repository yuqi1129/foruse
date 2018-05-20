package main.path_test;

import org.apache.commons.io.IOUtils;
import org.apache.flink.util.FileUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author yuqi
 * Time 18/1/18 09:51
 **/
public class PathTestOne {
    private InnerClass innerClass;

    public InnerClass getInnerClass() {
        return innerClass;
    }

    public void setInnerClass(InnerClass innerClass) {
        this.innerClass = innerClass;
    }

    public static void main(String[] args) {
        //
        try {
            String path = "demo2.conf";

            if (Paths.get(path).isAbsolute()) {
                System.out.println(FileUtils.readFile(new File(path), "UTF_8"));
            } else {
                //System.out.println(FileUtils.readFile(new File(path), "UTF_8"));
                System.out.println(IOUtils.toString(PathTestOne.class.getClassLoader().getResourceAsStream(path)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        PathTestOne testOne = new PathTestOne();
        PathTestOne.InnerClass innerClass = testOne.new InnerClass();
        innerClass.haha();

        List<String> stringList = new ArrayList<>();
        stringList.add("h");
        String[] result = stringList.toArray(new String[0]);
        System.out.println(Arrays.toString(result));

    }

    public void speak() {
        System.out.println("from outer class PathTestOne");
    }

    private class InnerClass {
        public void haha() {
           PathTestOne.this.speak();
        }
    }
}
