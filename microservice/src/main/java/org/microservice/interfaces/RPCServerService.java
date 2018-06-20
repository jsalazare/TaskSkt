package org.microservice.interfaces;

import java.io.IOException;

/**
 * Created by jsalazar on 6/18/18.
 */
public interface RPCServerService {

    void listenerService() throws IOException, InterruptedException;
}
