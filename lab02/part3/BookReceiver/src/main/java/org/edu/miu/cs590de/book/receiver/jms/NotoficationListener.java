package org.edu.miu.cs590de.book.receiver.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {
    @Autowired
    private QueueProperties queueProperties;

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "#{@queueProperties.rentalPaymentQueue}", concurrency = "2")
    public void hanldeBookNotification(PaymentNotification notification) {
        savePayment(notification, "RENTAL");
    }

}
