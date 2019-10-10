package com.zzr.spring.boot.starter.hivemq.conf;

import com.zzr.spring.boot.starter.hivemq.utils.PublishUtils;
import com.zzr.spring.boot.starter.hivemq.utils.SubscribeUtils;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName HiveMqAutoConfiguration
 * @Author zzrdark
 * @Date 2019-10-10 16:00
 * @Description TODO
 **/
@Configuration
@EnableConfigurationProperties(HivemqProperties.class)
public class HiveMqAutoConfiguration {


    @Autowired
    private HivemqProperties hivemqProperties;


    @Bean
    public PublishUtils publishClient(){
        PublishUtils publish = new PublishUtils(hivemqProperties.getTopic(),hivemqProperties.getBroker()
        ,hivemqProperties.getUsername(),hivemqProperties.getPassword(),hivemqProperties.getClientId());
        return publish;
    }

    @Bean
    public SubscribeUtils subscribeClient(){

        String className = hivemqProperties.getSubscribeCallback();

        if (className!= null&&!className.trim().equals("")){
            Class clazz;

            try {
                clazz = HiveMqAutoConfiguration.class.getClassLoader().loadClass(hivemqProperties.getSubscribeCallback());
                SubscribeUtils subscribe = new SubscribeUtils(hivemqProperties.getBroker(),hivemqProperties.getTopic()
                        ,1,hivemqProperties.getClientId(),hivemqProperties.getUsername(),hivemqProperties.getPassword()
                        , (MqttCallback) clazz.newInstance());
                return subscribe;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
