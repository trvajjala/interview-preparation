package com.tvajjala.runner;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
/**
 *  Console runner used to run test cases
 *
 * @author ThirupathiReddy V
 *
 */
public class ConsoleRunner {

    public static void main(String[] args) {

	final JUnitCore core=new JUnitCore();
	core.addListener(new TextListener(System.out));
	core.run(RunnerHamcrestTest.class);
    }
}
