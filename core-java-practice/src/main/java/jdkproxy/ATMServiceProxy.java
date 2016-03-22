package jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Dynamic Proxy class must implement {@link InvocationHandler} interface. it has single method that will be used to forward methods to an target instance.
 *
 * @author ThirupathiReddy V
 *
 */
public class ATMServiceProxy implements InvocationHandler {

    private final Object targetInstance;

    public ATMServiceProxy(Object targetInstance) {
        this.targetInstance = targetInstance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        final Object result = method.invoke(targetInstance, args);
        System.err.println(method.getName() + " executed with and returning results as " + result);
        return result;
    }

}
