package org.common.rabbit;


import com.rabbitmq.client.ConnectionFactory;
import org.common.interfaces.IChannelFactory;
import com.rabbitmq.client.Channel;
import org.common.interfaces.IConfigurations;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Component
public class ChannelFactory implements IChannelFactory {


    private IConfigurations configurations;


    public ChannelFactory(IConfigurations configurations){
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
