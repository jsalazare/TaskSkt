package org.nearsoft.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import org.common.interfaces.IChannelFactory;
import org.common.interfaces.IConfigurations;
import org.nearsoft.interfaces.IConsumerService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class ConsumerService implements IConsumerService {

    private IConfigurations configurations;

    private Channel channel;


    public ConsumerService(IConfigurations configurations, IChannelFactory channelFactory) throws IOException, TimeoutException {
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
