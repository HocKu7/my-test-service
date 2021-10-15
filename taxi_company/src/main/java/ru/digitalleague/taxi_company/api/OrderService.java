package ru.digitalleague.taxi_company.api;

import ru.digitalleague.core.model.OrderDetails;

/**
 * Сервис обработки заказов.
 */
public interface OrderService {

    /**
     * Обработать полученный заказ.
     *
     * @param order информация о заказе.
     */
    void proceedOrder(OrderDetails order);

    /**
     * Рассчитать конечную стоимость заказа.
     *
     * @param orderId идентификатор поездки
     */
    void calculateOrderSum(Long orderId);
}
