package org.nearsoft.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


import org.common.configuration.Configurations;
import org.common.interfaces.IConfigurations;
import org.nearsoft.interfaces.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;

@Service
public class ConsumerService implements IConsumerService {

    private IConfigurations configurations;

    private Channel channel;

    @Autowired
    public ConsumerService(IConfigurations configurations) throws IOException, TimeoutException {
        this.configurations = configurations;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(configurations.getHost());
        factory.setUsername(configurations.getUsername());
        factory.setPassword(configurations.getPassword());
        channel = factory.newConnection().createChannel();
    }


    @Override
    public void listenerService(Consumer consumer) throws IOException {

        channel.queueDeclare(configurations.getQueueManagnent(), true, false, false, null);

        channel.basicConsume(configurations.getQueueManagnent(), true, consumer);

    }

    @Override
    public Channel getChannel() {
        return channel;
    }


}
