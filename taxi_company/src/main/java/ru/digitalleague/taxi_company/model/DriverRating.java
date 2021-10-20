package ru.digitalleague.taxi_company.model;

import lombok.Data;

/**
 * Рейтинг водителя.
 */
@Data
public class DriverRating {

    /**
     * Идентификатор водителя.
     */
    private Long driverId;

    /**
     * Рейтинг водителя.
     */
    private int rating;
}
