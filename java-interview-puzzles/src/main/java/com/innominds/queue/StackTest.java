package com.innominds.queue;

import java.util.Stack;

public class StackTest {

    public static void main(String[] args) {

        final Stack<String> stack = new Stack<String>();

        System.out.println(stack.push("ONE"));
        System.out.println(stack.push("TWO"));
        stack.addElement("THREE");

        System.out.println(stack.peek());
        System.out.println(stack.pop());

        System.out.println(stack.size());
        stack.remove(0);
        System.out.println(stack.size());

    }
}
