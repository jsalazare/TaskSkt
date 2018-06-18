package org.nearsoft.interfaces;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;

import java.io.IOException;

/**
 * Created by jsalazar on 6/18/18.
 */
public interface IConsumerService {

     Channel getChannel();

     void listenerService(Consumer consumer) throws IOException;

}
