package com.innominds.ensure.capacity;

public class StringBufferCapacity {

    public static void main(String[] args) {

        final StringBuffer buffer = new StringBuffer();
        System.out.println(buffer.capacity());// default capacity 16
        buffer.ensureCapacity(40);
        // default capacity increases with below logic
        // newCapacity= (oldCapacity*2)+2
        System.out.println(buffer.capacity());
    }
}
