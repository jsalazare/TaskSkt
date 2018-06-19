package org.nearsoft.service;

import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.common.interfaces.IChannelFactory;
import org.common.interfaces.IConfigurations;
import org.common.util.SerializationUtilities;
import org.nearsoft.interfaces.IProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Service
public class ProducerService implements IProducerService{
    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    private IConfigurations configurations;

    private Channel channel;


    public ProducerService(IConfigurations configurations, IChannelFactory channelFactory) throws IOException, TimeoutException {
        this.configurations = configurations;
        channel = channelFactory.getNewChannel();

    }

    /**
     * This method publishes an object
     *
     * @param message
     * @throws IOException
     */

    @Override
    public void produceMessage(Object message) throws IOException {

        channel.queueDeclare(configurations.getQueueMicroservice(), true, false, false, null);

        channel.basicPublish(StringUtils.EMPTY, configurations.getQueueMicroservice(), null, SerializationUtilities.getBytes(message));

        logger.debug(" [x] Sent '" + message + "'");

    }

}
