package ru.digitalleague.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import ru.digitalleague.core.model.FeedBackModel;

/**
 * Маппер для работы с рейтингом водителя.
 */
@Repository
@Mapper
public interface RatingMapper {

    /**
     * Сохраняем рейтинг водителя.
     *
     * @param feedBackModel модель с данными о рейтинге водителя.
     */
    @Insert("insert into drivers_rating (driver_id, rating) VALUES (#{driverId}, #{rating})")
    void save(FeedBackModel feedBackModel);

    /**
     * Получить рейтинги водителя.
     *
     * @param driverId идентификатор водителя
     * @return полученные рейтинги
     */
    @Results(id = "ratings", value = {
            @Result(property = "driverId", column = "driver_id"),
            @Result(property = "rating", column = "rating")
    })
    @Select("select driver_id, rating from drivers_rating where driver_id=#{driverId}")
    List<FeedBackModel> getDriverRatings(Long driverId);

    /**
     * Обновить рейтинг водителя.
     *
     * @param driverId идентификатор водителя
     * @param rating   рейтинг водителя
     */
    @Update("update taxi_drive_info set rating = #{rating} where driver_id = #{driverId}")
    void updateDriverRating(Long driverId, double rating);
}
