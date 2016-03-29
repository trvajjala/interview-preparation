package com.tvajjala.rest.token;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.security.crypto.codec.Base64;

/**
 * This is SessionTokenGenerator
 * <p>
 * Simple and secure token generator code which is random and doesn't have any user specific information. <br>
 * deliberately bind to hazelCast {@link Base64} class .but you can use anyone.
 * </p>
 *
 * @author ThirupathiReddy V
 */
public class SessionTokenGenerator {

    /** The random. */
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Gets the token.
     *
     * @return the token
     */
    public static String getToken() {
        final String token = new BigInteger(130, SessionTokenGenerator.RANDOM).toString(32);
        return new String(Base64.encode(token.getBytes()));
    }

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {

        for (int i = 0; i < 20; i++) {
            System.out.println(SessionTokenGenerator.getToken());
        }
    }
}
