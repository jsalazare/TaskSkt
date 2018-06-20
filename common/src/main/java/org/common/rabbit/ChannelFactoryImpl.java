package org.common.rabbit;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import org.common.configuration.Configurations;
import org.common.interfaces.ChannelFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Component
public class ChannelFactoryImpl implements ChannelFactory {

    private Configurations configurations;

    public ChannelFactoryImpl(Configurations configurations){
        this.configurations = configurations;

    }

    public Channel getNewChannel () throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(configurations.getHost());
        factory.setUsername(configurations.getUsername());
        factory.setPassword(configurations.getPassword());
        return factory.newConnection().createChannel();
    }
}
