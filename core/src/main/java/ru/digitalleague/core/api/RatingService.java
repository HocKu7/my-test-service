package ru.digitalleague.core.api;

import ru.digitalleague.core.model.FeedBackModel;

/**
 * Сервис управления рейтингом водителя.
 */
public interface RatingService {

    /**
     * Сохранение отзыва о водителе.
     *
     * @param feedBackModel модель отзыва
     */
    void save(FeedBackModel feedBackModel);

    /**
     * Обновить рейтинг водителя.
     *
     * @param driverId идентификатор водителя
     */
    void updateDriverRating(Long driverId);
}
