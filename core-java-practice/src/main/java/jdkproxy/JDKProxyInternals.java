package jdkproxy;

import java.lang.reflect.Proxy;

public class JDKProxyInternals {
    public static void main(String[] args) {

        final ATMService atmService = new ATMServiceImpl();

        final Object atmProxy = Proxy.newProxyInstance(ATMService.class.getClassLoader(), ATMService.class.getInterfaces(), new ATMServiceProxy(atmService));

        System.out.println(atmProxy);
        System.out.println(atmProxy instanceof ATMServiceImpl);
    }
}
