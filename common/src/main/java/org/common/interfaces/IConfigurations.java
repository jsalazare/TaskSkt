package org.common.interfaces;

/**
 * Created by jsalazar on 6/18/18.
 */
public interface IConfigurations {

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getHost();

    void setHost(String host);

    String getQueueManagnent();

    void setQueueManagnent(String queueManagnent);

    String getQueueMicroservice();

    void setQueueMicroservice(String queueMicroservice);
}
