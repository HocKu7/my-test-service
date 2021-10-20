package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import ru.digitalleague.taxi_company.model.OrderModel;

@Repository
@Mapper
public interface OrderMapper {

    /**
     * Сохранить заказ.
     *
     * @param order информация о заказе.
     */
    @Insert("insert into orders (order_id, client_number, driver_id, start_trip, end_trip)" +
            "        values(#{orderId}, #{clientNumber}, #{driverId}, #{startTrip}, #{endTrip})")
    @SelectKey(statement = "select nextval('order_seq')", keyProperty = "orderId", before = true, resultType = Long.class)
    void saveOrder(OrderModel order);

    /**
     * Поиск заказа по идентификатору.
     *
     * @param orderId идентификатор заказа.
     * @return заказ
     */
    @Select("select order_id, client_number, driver_id, start_trip, end_trip " +
            "        from orders " +
            "        where order_id = #{orderId}")
    OrderModel findOrderById(Long orderId);

    /**
     * Обновление времени заказа.
     *
     * @param order информация о заказе.
     */
    @Update("update orders " +
            "        set start_trip    = now() " +
            "        where order_id = #{orderId}")
    void updateStartOrderTime(OrderModel order);

    /**
     * Обновление времени заказа.
     *
     * @param order инфорамция о заказе.
     */
    @Update("update orders " +
            "        set end_trip = now() " +
            "        where order_id = #{orderId}")
    void updateFinishOrderTime(OrderModel order);

    /**
     * Сохраняем информацию о стоимости заказа.
     *
     * @param orderId идентификатор заказа
     * @param total   конечная сумма
     */
    @Insert("insert into order_total(order_id, sum) values (#{orderId}, #{total})")
    void saveOrderWithFinalSum(Long orderId, Long total);
}
