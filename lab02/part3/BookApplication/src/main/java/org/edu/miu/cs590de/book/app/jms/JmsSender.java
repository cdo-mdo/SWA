package org.edu.miu.cs590de.book.app.jms;

import org.edu.miu.cs590de.book.app.dto.BookActivityDTO;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsSender {
    private final JmsTemplate jmsTemplate;

    public JmsSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendBookActivity(BookActivityDTO bookActivityDTO) {
        jmsTemplate.convertAndSend("book.activity.queue", bookActivityDTO);
        System.out.println("Sent: " + bookActivityDTO.toString());
    }
}

