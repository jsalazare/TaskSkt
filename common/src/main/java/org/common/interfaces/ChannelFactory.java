package org.common.interfaces;


import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface ChannelFactory {

    Channel getNewChannel () throws IOException, TimeoutException;
}
