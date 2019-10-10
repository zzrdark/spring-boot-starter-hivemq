package com.zzr.spring.boot.starter.hivemq.utils;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @ClassName SubscribeUtils
 * @Author zzrdark
 * @Date 2019-10-10 16:02
 * @Description TODO
 **/
public class SubscribeUtils {

    private String HOST ;
    private String TOPIC ;
    private int qos = 1;
    private String clientid ;
    private String userName ;
    private String passWord ;
    private MqttCallback callback;

    public SubscribeUtils(String HOST, String TOPIC, int qos,
                          String clientid, String userName, String passWord,
                          MqttCallback callback) {
        this.HOST = HOST;
        this.TOPIC = TOPIC;
        this.qos = qos;
        this.clientid = clientid;
        this.userName = userName;
        this.passWord = passWord;
        this.callback = callback;
        Subscribe();
    }


    private void Subscribe(){
        MqttConnectOptions options = new MqttConnectOptions();

        options.setCleanSession(true);

        options.setUserName(userName);

        options.setPassword(passWord.toCharArray());

        options.setConnectionTimeout(20);

        options.setKeepAliveInterval(20);


        try {
            MqttClient client = new MqttClient(HOST,clientid,new MemoryPersistence());
            client.setCallback(callback);
            executor(client,options);
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    private void executor (MqttClient client,MqttConnectOptions options) throws MqttException {
        client.connect(options);

        client.subscribe(TOPIC, qos);
    }
}
