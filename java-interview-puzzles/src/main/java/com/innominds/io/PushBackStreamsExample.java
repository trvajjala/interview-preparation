package com.innominds.io;

import java.io.ByteArrayInputStream;
import java.io.PushbackInputStream;

/**
 * Things to observer
 * <ol>
 * <li>Converting String into Stream.</li>
 * <li>Read and Unread the bytes.</li>
 * <li>printing the characters and its ASCII values.</li>
 * </ol>
 *
 * @author ThirupathiReddy V
 *
 */
public class PushBackStreamsExample {

    public static void main(String[] args) throws Exception {

        final String message = "1234";

        try (PushbackInputStream pio = new PushbackInputStream(new ByteArrayInputStream(message.getBytes()))) {
            byte data;
            while ((data = (byte) pio.read()) != -1) {
                System.out.print((char) data);
                pio.unread(data);
                System.out.print(pio.read());
            }
        }

    }
}
