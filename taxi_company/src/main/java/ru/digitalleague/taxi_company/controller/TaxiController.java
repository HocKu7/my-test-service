package ru.digitalleague.taxi_company.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.model.OrderModel;

/**
 * Контроллер получающий информацию о поездке.
 */
@RestController
public class TaxiController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * Метод получает инфо о начале поездки.
     *
     * @param order инфо о заказе
     */
    @PostMapping("/start-trip")
    public ResponseEntity<String> startTrip(@RequestBody OrderModel order) {
        System.out.println("Trip is started");
        if(order == null) return ResponseEntity.badRequest().body("Передан не правильный запрос");

        OrderModel orderById = orderMapper.findOrderById(order.getOrderId());

        orderMapper.updateStartOrderTime(orderById);
        return ResponseEntity.ok("Поездка началась");
    }

    /**
     * Метод получает инфо о начале поездки.
     *
     * @param order инфо о заказе
     */
    @PostMapping("/finish-trip")
    public ResponseEntity<String> finishTrip(@RequestBody @Validated OrderModel order) {
        System.out.println("Trip is finished");
        if(order == null) return ResponseEntity.badRequest().body("Передан не правильный запрос");

        OrderModel orderById = orderMapper.findOrderById(order.getOrderId());

        orderMapper.updateFinishOrderTime(orderById);
        return ResponseEntity.ok("Поездка окончена");
    }
}
