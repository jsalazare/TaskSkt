package org.microservice.service;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.StringUtils;
import org.common.configuration.Configurations;
import org.common.interfaces.IChannelFactory;
import org.common.util.SerializationUtilities;
import org.microservice.interfaces.IProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService implements IProducerService{

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

	private Configurations configurations;
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;


    public ProducerService (Configurations configurations, IChannelFactory channelFactory) throws IOException, TimeoutException {
        this.configurations = configurations;
        channel = channelFactory.getNewChannel();

    }

    
    /**
     *  This method publishes a message
     * @param message
     */
    @Override
    public void produceMessage(Object message) throws IOException {

            
            
        channel.queueDeclare(configurations.getQueueManagnent(), true, false, false, null);

        channel.basicPublish(StringUtils.EMPTY, configurations.getQueueManagnent(), null, SerializationUtilities.getBytes(message));

        logger.debug(" [x] Sent '" + message + "'");
          

    }
    
}
