package ru.digitalleague.core.model;

import lombok.Data;

/**
 * Модель предоставления отзыва об оказанной услуге.
 */
@Data
public class FeedBackModel {

    /**
     * Идентификатор заказа.
     */
    private Long driverId;

    /**
     * Рейтинг водителя (от 1 до 5).
     */
    private int rating;
}
