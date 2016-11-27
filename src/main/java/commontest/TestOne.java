package commontest;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/11
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */

import common.Person;

/**
 *  测试静态变量的效果
 */

public class TestOne {

    public static void main(String [] args){
        Person p = Person.getInstance();

        System.out.println("start:" + p);

        p.setId(2);
        p.setName("nice");

        Person p1 = Person.getInstance();
        System.out.println("middle:" + p);

    }

}
