package org.nearsoft;

import org.common.configuration.Configurations;
import org.common.dto.ProductDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EnableAutoConfiguration
public class WebApplication {



    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    @ConfigurationProperties(prefix = "rabbit")
    public Configurations configurations(){
        return new Configurations();
    }
}
