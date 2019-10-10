package com.zzr.spring.boot.starter.hivemq.utils;
import	java.util.logging.LogManager.Cleaner;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @ClassName Publishutils
 * @Author zzrdark
 * @Date 2019-10-10 16:13
 * @Description TODO
 **/
public class PublishUtils{
    private String topic ;
    private String broker ;
    private String userName ;
    private String password ;
    private String clientId ;

    protected MqttClient client;

    public PublishUtils(String topic, String broker,
                        String userName, String password, String clientId) {
        this.topic = topic;
        this.broker = broker;
        this.userName = userName;
        this.password = password;
        this.clientId = clientId;
    }

    private void Pushlish(){

        // 内存存储
        MemoryPersistence persistence = new MemoryPersistence();

        // 创建链接参数
        MqttConnectOptions connOpts = new MqttConnectOptions();
        // 在重新启动和重新连接时记住状态
        connOpts.setCleanSession(false);
        // 设置连接的用户名
        connOpts.setUserName(userName);
        connOpts.setPassword(password.toCharArray());


        try {
            client = new MqttClient(broker,clientId,persistence);
            client.connect(connOpts);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void createMessage(String content,int qos) throws MqttException {
        // 创建消息
        MqttMessage message = new MqttMessage(content.getBytes());
        // 设置消息的服务质量
        message.setQos(qos);
        // 发布消息
        client.publish(topic, message);
    }

    public void destroy() throws MqttException {
        // 断开连接
        client.disconnect();
        // 关闭客户端
        client.close();
    }
}
