package org.common.interfaces;


import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface IChannelFactory {

    Channel getNewChannel () throws IOException, TimeoutException;
}
