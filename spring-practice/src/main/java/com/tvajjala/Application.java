package com.tvajjala;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import com.tvajjala.aop.UserAspect;
import com.tvajjala.condition.UserCondition;
import com.tvajjala.service.LifeCycleBean;
import com.tvajjala.service.UserService;
import com.tvajjala.service.UserServiceImpl;

@Configuration
@EnableAutoConfiguration
@Import({ ParentConfig.class, DevelopmentProfile.class })
@ImportResource("classpath:old-config.xml")
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
// using CGLib. it generates Proxies. default JDK dynamic Proxies
public class Application {

    private final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    Environment env;

    @Value("${logging.config}")
    private String config;

    /**
     * The @Bean annotation tells Spring that this method will return an object that should <br>
     * be registered as a bean in the Spring application context.
     *
     * @return userService
     */
    @Bean
    @Conditional(UserCondition.class)
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserService userService() {
        logger.debug("userService");
        return new UserServiceImpl();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(initMethod = "init", destroyMethod = "killme")
    public LifeCycleBean lifeCycleBean(UserService userService) {
        System.err.println("Creating life cycle bean  ");
        return new LifeCycleBean(userService, config);
    }

    @Bean
    public UserAspect userAspect() {
        return new UserAspect();
    }

    public static void main(String[] args) {

        final AnnotationConfigApplicationContext aCtx = new AnnotationConfigApplicationContext();

        aCtx.register(Application.class);
        aCtx.refresh();

        UserService userService = aCtx.getBean(UserService.class);

        System.err.println(" userService " + userService);

        userService = aCtx.getBean(UserService.class);

        System.err.println(" userService " + userService);

        System.err.println("UserService.getName() : " + userService.getName());

        System.err.println("########  " + userService.getSystemProperty());

        aCtx.close();
    }

}
