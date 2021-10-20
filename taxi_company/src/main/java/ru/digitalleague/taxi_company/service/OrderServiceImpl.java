package ru.digitalleague.taxi_company.service;

import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.core.model.TaxiDriverInfoModel;
import ru.digitalleague.taxi_company.api.DriverService;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.mapper.CityMapper;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.mapper.TaxiDriverInfoMapper;
import ru.digitalleague.taxi_company.model.OrderModel;
import ru.digitalleague.taxi_company.utils.Converter;

/**
 * Сервис обработки заказов.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TaxiDriverInfoMapper taxiDriverInfoMapper;

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private DriverService driverService;

    @Override
    public void proceedOrder(OrderDetails order) {
        if (order == null) return;

        String city = order.getCity();
        Long cityIdByName = cityMapper.findCityIdByName(city);
        order.setCityId(cityIdByName);

        TaxiDriverInfoModel driver = taxiDriverInfoMapper.findDriver(order.getCityId());

        OrderModel orderModel = Converter.convertOrderDetailsIntoOrder(order, driver);
        orderMapper.saveOrder(orderModel);

        System.out.println("Заказ сохранен: " + driver.getDriverId());
    }

    @Override
    public void calculateOrderSum(Long orderId) {
        OrderModel orderById = orderMapper.findOrderById(orderId);
        OffsetDateTime startTrip = orderById.getStartTrip();
        OffsetDateTime endTrip = orderById.getEndTrip();
        long duration = ChronoUnit.MINUTES.between(startTrip, endTrip);
        int minuteCost = driverService.getMinuteCost(orderById.getDriverId());
        long sum = duration * minuteCost;
        saveOrderWithFinalSum(orderId, sum);
    }

    @Override
    public void saveOrderWithFinalSum(Long orderId, Long sum) {
        orderMapper.saveOrderWithFinalSum(orderId, sum);
    }

    @Override
    public OrderModel findOrderById(Long orderId) {
        return orderMapper.findOrderById(orderId);
    }

    @Override
    public void saveOrder(OrderModel order) {
        orderMapper.saveOrder(order);
    }

    @Override
    public void updateStartOrderTime(OrderModel order) {
        orderMapper.updateStartOrderTime(order);
    }

    @Override
    public void updateFinishOrderTime(OrderModel order) {
        orderMapper.updateFinishOrderTime(order);
    }

}
