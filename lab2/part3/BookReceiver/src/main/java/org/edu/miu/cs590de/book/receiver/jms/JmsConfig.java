package org.edu.miu.cs590de.book.receiver.jms;

import org.apache.activemq.artemis.jms.client.ActiveMQJMSConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfig {
    @Value("${spring.jms.message.broker-url}")
    private String BROKER_URL;

    @Bean
    public ActiveMQJMSConnectionFactory getConnectionFactory() {
        return new ActiveMQJMSConnectionFactory(BROKER_URL);
    }
}
