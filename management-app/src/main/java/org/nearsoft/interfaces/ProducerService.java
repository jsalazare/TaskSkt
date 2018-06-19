package org.nearsoft.interfaces;

import java.io.IOException;


public interface ProducerService {

    void produceMessage(Object message) throws IOException;
}
