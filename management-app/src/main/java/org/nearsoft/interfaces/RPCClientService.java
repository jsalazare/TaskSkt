package org.nearsoft.interfaces;

import org.common.dto.ProductDTO;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by jsalazar on 6/18/18.
 */
public interface RPCClientService {

    List<ProductDTO> produceMessage(Object message) throws IOException, InterruptedException, TimeoutException;
}
