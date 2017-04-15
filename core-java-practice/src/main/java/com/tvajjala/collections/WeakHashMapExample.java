package com.tvajjala.collections;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapExample {

    public static void main(String[] args) {

        final Map<Order, Integer> orders = new WeakHashMap<>();

        for (int i = 0; i < 853949; i++) {
            orders.put(new Order(i, ""), 100);
        }

        System.out.println(orders.size());

        System.gc();// no guarantee that garbage collector is invoked

        System.out.println(orders.size());

    }
}

class Order {

    private int orderId;
    private String description;

    public Order(int orderId, String description) {

        this.orderId = orderId;
        this.description = description;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
