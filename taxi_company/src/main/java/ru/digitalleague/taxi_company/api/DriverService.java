package ru.digitalleague.taxi_company.api;

import ru.digitalleague.taxi_company.model.DriverRating;

/**
 * Сервис работы с водителями.
 */
public interface DriverService {

    /**
     * Обновить статус водителя на 'занят'.
     *
     * @param driverId идентификатор водителя
     */
    void lockDriver(Long driverId);

    /**
     * Обновить статус водителя на 'свободен'.
     *
     * @param driverId идентификатор водителя
     */
    void unlockDriver(Long driverId);

    /**
     * Получение стоимости минуты поездки водителя.
     *
     * @param driverId идентификатор водителя
     */
    int getMinuteCost(Long driverId);

    /**
     * Сохранить рейтинг водителя.
     *
     * @param driverRating модель с рейтингом
     */
    void saveDriverRating(DriverRating driverRating);
}
