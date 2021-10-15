package ru.digitalleague.taxi_company.listener;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.api.OrderService;

@EnableRabbit
@Slf4j
@ConditionalOnBean(value = RabbitTemplate.class)
@Component
public class OrderListener {

//    @Autowired
//    private ObjectMapper objectMapper;

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "${application.broker.receive-queue}")
    public void onMessage(Message message) {
        log.info("Received message from rabbitmq " + message);

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] body = message.getBody();
        try {
            OrderDetails orderDetails = objectMapper.readValue(body, OrderDetails.class);

            if (orderService != null) {
                orderService.proceedOrder(orderDetails);
            }
            System.out.println(message);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
