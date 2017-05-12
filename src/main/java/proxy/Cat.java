package proxy;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/4/18
 * Time: 21:00
 * To change this template use File | Settings | File Templates.
 */

public class Cat implements Animal {
    @Override
    public void makeSound(String name) {
        System.out.println("Hi, " + name + " ,miao, miao~~");
    }
}
