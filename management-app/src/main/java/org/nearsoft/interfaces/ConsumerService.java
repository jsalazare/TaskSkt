package org.nearsoft.interfaces;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;

import java.io.IOException;


public interface ConsumerService {

     Channel getChannel();

     void listenerService(Consumer consumer) throws IOException;

}
