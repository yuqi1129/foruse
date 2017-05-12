package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/4/18
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */

public class AnimalProxy implements InvocationHandler {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;

        System.out.println("before we use the function..");
        result = method.invoke(target, args);
        System.out.println("after we use the function..");
        return result;
    }
}
