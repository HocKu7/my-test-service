package ru.digitalleague.taxi_company.utils;

import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.core.model.TaxiDriverInfoModel;
import ru.digitalleague.taxi_company.model.OrderModel;

/**
 * Утилитный класс для преобразования обьектов.
 */
public class Converter {

    /**
     * Формирование информации о заказе.
     *
     * @param orderDetails информация о заказе
     * @param driver       информация о водителе
     * @return инфо о заказе
     */
    public static OrderModel convertOrderDetailsIntoOrder(OrderDetails orderDetails, TaxiDriverInfoModel driver) {

        if (orderDetails == null) return null;

        OrderModel order = new OrderModel();
        order.setClientNumber(orderDetails.getClientNumber());
        order.setDriverId(driver.getDriverId());
        return order;
    }
}
