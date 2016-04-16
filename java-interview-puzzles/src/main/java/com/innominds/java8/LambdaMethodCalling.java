package com.innominds.java8;

public class LambdaMethodCalling {

    public static void main(String[] args) {

        final College college = (name) -> {
            System.out.println("College name: " + name);
        };

        college.setName("BVRIT");

    }
}

interface College {

    void setName(String name);

}
