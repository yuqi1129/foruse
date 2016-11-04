package jvm;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/10/27jvm.j
 * Time: 10:20
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {

    public static void main(String [] args){
        System.out.println("max memory :" + Runtime.getRuntime().maxMemory()/1000/1000 + "M");
        System.out.println("total memory :" + Runtime.getRuntime().totalMemory()/1000/1000 + "M");
    }

}
