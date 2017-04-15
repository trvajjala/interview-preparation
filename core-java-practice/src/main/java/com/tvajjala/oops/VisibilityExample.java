package com.tvajjala.oops;

public class VisibilityExample {

    public static void main(String[] args) {

    }
}

class Parent{

    public void someMethod(){

    }
}
class  Child extends Parent{

    //You cant reduce visibility in the inherited method
    //private void someMethod(){}
}
