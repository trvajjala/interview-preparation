package com.tvajjala.bigdecimal;

import java.text.DecimalFormat;

public class DecimalFormatExample {

    public static void main(String[] args) {

	final DecimalFormat d = new DecimalFormat("$#,###.00");

	final double grossPay = 2800.4;

	System.out.println(d.format(grossPay));
    }
}
