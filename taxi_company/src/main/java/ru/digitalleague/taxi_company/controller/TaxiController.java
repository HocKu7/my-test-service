package ru.digitalleague.taxi_company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.digitalleague.taxi_company.api.DriverService;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.model.DriverRating;
import ru.digitalleague.taxi_company.model.OrderModel;

/**
 * Контроллер получающий информацию о поездке.
 */
@RestController
public class TaxiController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private OrderService orderService;

    /**
     * Метод получает инфо о начале поездки.
     *
     * @param order инфо о заказе
     */
    @PostMapping("/start-trip")
    public ResponseEntity<String> startTrip(@RequestBody OrderModel order) {
        System.out.println("Trip is started");
        if (order == null) return ResponseEntity.badRequest().body("Передан не правильный запрос");

        OrderModel orderById = orderService.findOrderById(order.getOrderId());
        driverService.lockDriver(orderById.getDriverId());

        orderService.updateStartOrderTime(orderById);
        return ResponseEntity.ok("Поездка началась");
    }

    /**
     * Метод получает инфо о начале поездки.
     *
     * @param order инфо о заказе
     */
    @PostMapping("/finish-trip")
    public ResponseEntity<String> finishTrip(@RequestBody OrderModel order) {
        System.out.println("Trip is finished");
        if (order == null) return ResponseEntity.badRequest().body("Передан не правильный запрос");

        OrderModel orderById = orderService.findOrderById(order.getOrderId());
        driverService.unlockDriver(order.getDriverId());

        orderService.updateFinishOrderTime(orderById);

        orderService.calculateOrderSum(order.getOrderId());
        return ResponseEntity.ok("Поездка окончена");
    }

    /**
     * Передача информации о рейтинге водителя.
     *
     * @param driverRating модель с рейтингом
     */
    @PostMapping("/rating")
    public ResponseEntity<String> rating(@RequestBody DriverRating driverRating) {
        driverService.saveDriverRating(driverRating);
        return ResponseEntity.ok("Ваш рейтинг получен, спасибо");
    }

}
