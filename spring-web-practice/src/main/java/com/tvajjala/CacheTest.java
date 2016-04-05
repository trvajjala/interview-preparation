package com.tvajjala;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class CacheTest {

    public static void main(String[] args) {

        final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        final IMap<String, String> map = hazelcastInstance.getMap("userTokenMap");

        map.put("one", "some value");

        System.out.println(map.get("one"));

    }
}
