package org.microservice.interfaces;

import java.io.IOException;

/**
 * Created by jsalazar on 6/18/18.
 */
public interface IProducerService {

    void produceMessage(Object message) throws IOException;

    void produceMessage(String message) throws IOException;
}
