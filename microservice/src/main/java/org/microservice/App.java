package org.microservice;

import org.common.configuration.Configurations;
import org.common.rabbit.ChannelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {

    @Autowired
    private ApplicationContext context;

    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }

    @Bean
    @ConfigurationProperties(prefix = "rabbit")
    public Configurations configurations(){
        return new Configurations();
    }
    @Bean
    public ChannelFactory channelFactory(){
        return new ChannelFactory (context.getBean(Configurations.class));
    }

    @ExceptionHandler
    public void handleIllegalArgumentException(IllegalArgumentException e)
            throws IOException {
        e.printStackTrace();
    }
}
