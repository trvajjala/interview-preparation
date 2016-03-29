package com.tvajjala.condition;

import java.util.Arrays;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class UserCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        final Environment env = context.getEnvironment();
        final String[] profiles = env.getActiveProfiles();

        for (final String profile : profiles) {
            System.out.println("Active Profiles " + profile);
        }

        return !Arrays.asList(profiles).contains("prod");
    }
}
