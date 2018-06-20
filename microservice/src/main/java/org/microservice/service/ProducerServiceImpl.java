package org.microservice.service;


import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.common.configuration.Configurations;
import org.common.interfaces.ChannelFactory;
import org.common.util.SerializationUtilities;
import org.microservice.interfaces.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class ProducerServiceImpl implements ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerServiceImpl.class);

	private Configurations configurations;
    private Channel channel;

    public ProducerServiceImpl(Configurations configurations, ChannelFactory channelFactory) throws IOException, TimeoutException {
        this.configurations = configurations;
        channel = channelFactory.getNewChannel();

    }

    @Override
    public void produceMessage(Object message) throws IOException {
            
        channel.queueDeclare(configurations.getQueueManagnent(), true, false, false, null);

        channel.basicPublish(StringUtils.EMPTY, configurations.getQueueManagnent(), null, SerializationUtilities.getBytes(message));

        logger.debug(" [x] Sent '" + message + "'");

    }
    
}
