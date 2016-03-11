package com.trvajjala.object.pool;

import java.sql.Connection;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public class ObjectPoolExample {

    public static void main(String[] args) {

        final JDBCConnectionPool pool = new JDBCConnectionPool.Builder().withUrl("jdbc:mysql://localhost:3306/test").usingDriver("com.mysql.jdbc.Driver")
                .withUsername("root").withPassword("admin").build(5, 20, 5);

        System.out.println("Pool Size : " + pool.getPoolSize());
        final Connection conn = pool.acquire();

        System.out.println("Pool Size : " + pool.getPoolSize());
        pool.release(conn);
        System.out.println("Pool Size : " + pool.getPoolSize());

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(pool.acquire());

            System.out.println("PoolSize : " + pool.getPoolSize());
        }

    }
}
