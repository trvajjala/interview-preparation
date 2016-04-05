package com.tvajjala.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.tvajjala.persistence.repository.UserRepository;

/**
 * sample implementation of BackGroud process using spring Scheduling
 *
 * @author ThirupathiReddy V
 *
 */
@Configuration
@EnableScheduling
public class SchedulerRunner {

    @Autowired
    private UserRepository userRepository;

    @Scheduled(initialDelay = 1000, fixedRate = 5 * 60000)
    // time in m seconds
    public void run() {
        // System.out.println("Running background userRepository " + userRepository.count());
    }
}
