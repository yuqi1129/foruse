package proxy;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/4/18
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */

public class Dog implements Animal {

    @Override
    public void makeSound(String name) {
        System.out.println("Hi, " + name + ", wang,wang~~");
    }
}
