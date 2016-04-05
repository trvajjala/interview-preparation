package com.tvajjala;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

import javax.servlet.ServletContext;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Configuration: Tells spring container that file contains @Bean ,@Service, @Component definitions.
 * @ComponentScan: auto scanning of the components in the package.
 * @EnableAutoConfiguration:It creates conditional beans depending on the jar files available on the class-path
 *
 *
 * @author ThirupathiReddy V
 *
 */

@SpringBootApplication
@PropertySource(ignoreResourceNotFound = true, value = { "classpath:application.properties", "file:application.properties" })
public class Application extends SpringBootServletInitializer {

    @Override
    public WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
        return super.createRootApplicationContext(servletContext);
    }

    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        // argument --debug gives you spring boot auto configuration report
        System.setProperty("spring.profiles.default", System.getProperty("spring.profiles.default", "dev"));
        // spring.output.ansi.enabled
        final SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        // verifyKeystore();
    }

    /**
     * This method is to check self-signed keyStore file verification.
     */
    static void verifyKeystore() {
        try {
            final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

            try {
                final ClassPathResource classPathResource = new ClassPathResource("keystore");
                System.err.println(classPathResource.getFile().getAbsolutePath());
                keyStore.load(new FileInputStream(classPathResource.getFile().getAbsolutePath()), "spring".toCharArray());
            } catch (final Exception e) {
                System.err.println("Error while loading classpath resource " + e.getMessage());
            }

            final Enumeration<String> enumeration = keyStore.aliases();
            while (enumeration.hasMoreElements()) {
                final String alias = enumeration.nextElement();
                System.out.println("Alias name: " + alias);
                final Certificate certificate = keyStore.getCertificate(alias);
                System.out.println(certificate.toString());
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

}
