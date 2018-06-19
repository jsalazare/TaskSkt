package org.nearsoft.interfaces;

import java.io.IOException;


public interface IProducerService {

    void produceMessage(Object message) throws IOException;
}
