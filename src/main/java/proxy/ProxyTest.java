package proxy;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/4/18
 * Time: 20:57
 * To change this template use File | Settings | File Templates.
 */

public class ProxyTest {
    public static void main(String[] args) {

        AnimalProxy proxy = new AnimalProxy();
        Animal dogProxy = (Animal) proxy.getInstance(new Dog());
        dogProxy.makeSound("Tom");

        new Dog().makeSound("je je");
    }

}
