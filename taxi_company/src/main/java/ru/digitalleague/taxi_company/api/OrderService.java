package ru.digitalleague.taxi_company.api;

import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.model.OrderModel;

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

    /**
     * Выставить сумму поездки.
     *
     * @param orderId идентификатор закаща.
     * @param sum     сумма поездки
     */
    void saveOrderWithFinalSum(Long orderId, Long sum);

    /**
     * Поиск заказа по идентификатору.
     *
     * @param orderId идентификатор заказа.
     * @return заказ
     */
    OrderModel findOrderById(Long orderId);

    /**
     * Сохранить заказ.
     *
     * @param order информация о заказе.
     */
    void saveOrder(OrderModel order);

    /**
     * Обновление времени заказа.
     *
     * @param order инфорамция о заказе.
     */
    void updateStartOrderTime(OrderModel order);

    /**
     * Обновление времени заказа.
     *
     * @param order инфорамция о заказе.
     */
    void updateFinishOrderTime(OrderModel order);
}
