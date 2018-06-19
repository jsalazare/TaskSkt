package org.nearsoft;

import org.common.configuration.Configurations;
import org.common.rabbit.ChannelFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableAutoConfiguration
public class WebApplication {


    @Autowired
    private ApplicationContext context;


    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    @ConfigurationProperties(prefix = "rabbit")
    public Configurations configurations(){
        return new Configurations();
    }

    @Bean
    public ChannelFactoryImpl channelFactory(){
        return new ChannelFactoryImpl(context.getBean(Configurations.class));
    }
}
