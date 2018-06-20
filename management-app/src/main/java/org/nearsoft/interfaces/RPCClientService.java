package org.nearsoft.interfaces;

import org.common.dbmodel.Product;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public interface RPCClientService {

    List<Product> produceMessage(Object message) throws IOException, InterruptedException, TimeoutException;
}
