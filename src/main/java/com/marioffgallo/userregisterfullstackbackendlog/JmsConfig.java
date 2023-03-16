package com.marioffgallo.userregisterfullstackbackendlog;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
public class JmsConfig {

    String brokerUrl = "tcp://localhost:61616";
    /*
    CODIGO PARA SUBIR NO DOCKER
    String brokerUrl = "tcp://user-register-backend:61616";
     */

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(new PooledConnectionFactory(brokerUrl));
        return factory;
    }
}
