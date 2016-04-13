package com.tvajjala.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class LifeCycleBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean {

    public LifeCycleBean(UserService userService, String property) {

        System.err.println(" DDDDD " + userService + property);
    }

    @Autowired
    UserService userService;

    public LifeCycleBean() {

    }

    @Override
    public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
        return arg0;
    }

    @Override
    public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
        return arg0;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {

        System.err.println("destroy ");
    }

    public void init() {

        System.err.println(" init method");
    }

    public void killme() {
        System.err.println("custom killing");
    }

    @Override
    public void setBeanName(String arg0) {
        System.err.println("setBeanName : " + arg0);
    }

    @Override
    public void setBeanFactory(BeanFactory arg0) throws BeansException {
        System.err.println("setBeanFactory " + arg0);
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        System.err.println("appCtx " + arg0);
    }

}
