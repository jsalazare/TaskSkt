package org.nearsoft.listener;


import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * This is the queue listener class, its receiveMessage() method ios invoked with the
 * message as the parameter.
 */
@Component
public class ProductMessageListener {

    /**
     * This method is invoked whenever any new message is put in the queue.
     * See {@link guru.springframework.SpringBootRabbitMQApplication} for more details
     * @param message
     */
    public void receiveMessage(Map<String, String> message) {
        System.out.println("Received <" + message + ">");
        String id = message.get("id");
        System.out.println("Message procesed: " + id);
    }
}
