package com.tvajjala.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.config.Config;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.MulticastConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class CacheConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {
        final Config config = new Config();
        final GroupConfig groupConfig = new GroupConfig();
        groupConfig.setName("trvajjala");
        groupConfig.setPassword("trvajjala");
        config.setGroupConfig(groupConfig);

        final NetworkConfig networkConfig = config.getNetworkConfig();
        final MulticastConfig multicastConfig = new MulticastConfig();
        multicastConfig.setEnabled(false);
        networkConfig.getJoin().setMulticastConfig(multicastConfig);
        networkConfig.getJoin().getAwsConfig().setEnabled(false);
        networkConfig.getJoin().getTcpIpConfig().setEnabled(true);

        final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        return hazelcastInstance;
    }

    @Bean
    public ObjectMapper jacksonObjectMapper() {
        return new ObjectMapper();
    }
}
