package com.marioffgallo.userregisterfullstackbackendlog;

import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

/**
 * JMS configurations to listen the ActiveMQ broker from the microservice user-register-fullstack-backend
 *
 * @author Mario F.F Gallo
 * @version 1.0
 */
@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class JmsConfig {

    @Value("${spring.activemq.broker-url}")
    String brokerUrl;

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(new PooledConnectionFactory(brokerUrl));
        return factory;
    }
}
