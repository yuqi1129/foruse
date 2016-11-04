package jvm.ChapterTwo;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/10/27
 * Time: 10:39
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {


    // 测试java 栈
    private static int count = 0;
    public static void recursion(){
        count ++ ;
        System.out.println("deep of calling = " + count);
        recursion();
    }

    public static void recursion(int a,int b,int c){
        count ++ ;
        System.out.println("deep of calling = " + count);
        recursion();
    }

    public static void main(String [] args){
        try{
            recursion();
            //recursion(1,1,1);
        }catch (Exception e){
            System.out.println("deep of calling = " + count);
            e.printStackTrace();
        }
    }

}
