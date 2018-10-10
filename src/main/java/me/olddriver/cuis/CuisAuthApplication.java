package me.olddriver.cuis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author <a href="mailto:Li7nux@gmail.com">Li7nux</a>
 * Date: 2018-01-20
 * Description:
 */

@SpringBootApplication
@EnableConfigurationProperties
public class CuisAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuisAuthApplication.class, args);
    }

}
