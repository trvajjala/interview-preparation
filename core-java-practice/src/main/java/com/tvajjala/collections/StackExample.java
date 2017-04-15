package com.tvajjala.collections;

import java.util.Stack;

public class StackExample {

    public static void main(String[] args) {

        final Stack<Integer> stack = new Stack<Integer>();

        stack.push(10);

        stack.push(200);

        System.out.println(stack.pop());

    }
}
