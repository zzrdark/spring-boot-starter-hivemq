package com.zzr.spring.boot.starter.hivemq.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.beans.ConstructorProperties;

/**
 * @ClassName hivemqProperties
 * @Author zzrdark
 * @Date 2019-10-10 15:51
 * @Description TODO
 **/

@ConfigurationProperties(prefix = "spring.hivemq")
public class HivemqProperties {

    private String topic;
    private String content;
    private String broker;
    private String username;
    private String password;
    private String clientId;
    private String SubscribeCallback;

    public String getSubscribeCallback() {
        return SubscribeCallback;
    }

    public void setSubscribeCallback(String subscribeCallback) {
        SubscribeCallback = subscribeCallback;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
