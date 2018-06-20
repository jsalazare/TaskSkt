package org.nearsoft.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import org.common.configuration.Configurations;
import org.common.interfaces.ChannelFactory;
import org.nearsoft.interfaces.ConsumerService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    private Configurations configurations;

    private Channel channel;


    public ConsumerServiceImpl(Configurations configurations, ChannelFactory channelFactory) throws IOException, TimeoutException {
        this.configurations = configurations;
        channel = channelFactory.getNewChannel();
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
