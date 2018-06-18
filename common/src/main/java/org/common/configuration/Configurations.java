package org.common.configuration;

import org.common.interfaces.IConfigurations;

public class Configurations implements IConfigurations {

    private String username;
    private String password;
    private String host;
    private String queueManagnent;
    private String queueMicroservice;


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String getQueueManagnent() {
        return queueManagnent;
    }

    @Override
    public void setQueueManagnent(String queueManagnent) {
        this.queueManagnent = queueManagnent;
    }

    @Override
    public String getQueueMicroservice() {
        return queueMicroservice;
    }

    @Override
    public void setQueueMicroservice(String queueMicroservice) {
        this.queueMicroservice = queueMicroservice;
    }


}
