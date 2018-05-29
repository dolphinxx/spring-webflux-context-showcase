package com.whaleread.illustration;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author Dolphin
 */
@SpringBootApplication
public class Application {
    @Bean
    public SecurityContextWebFilter securityContextWebFilter() {
        return new SecurityContextWebFilter();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(WebApplicationType.REACTIVE).run(args);
    }
}
