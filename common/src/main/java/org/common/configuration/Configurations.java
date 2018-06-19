package org.common.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {

    private String username;
    private String password;
    private String host;
    private String queueManagnent;
    private String queueMicroservice;
    private String rpcQueue;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getQueueManagnent() {
        return queueManagnent;
    }

    public void setQueueManagnent(String queueManagnent) {
        this.queueManagnent = queueManagnent;
    }

    public String getQueueMicroservice() {
        return queueMicroservice;
    }

    public void setQueueMicroservice(String queueMicroservice) {
        this.queueMicroservice = queueMicroservice;
    }

    public String getRpcQueue() {
        return rpcQueue;
    }

    public void setRpcQueue(String rpcQueue) {
        this.rpcQueue = rpcQueue;
    }
}
