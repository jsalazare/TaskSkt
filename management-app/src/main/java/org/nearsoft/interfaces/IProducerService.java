package org.nearsoft.interfaces;

import org.common.dto.ProductDTO;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;


public interface IProducerService {

    void produceMessage(Object message) throws IOException;
}
