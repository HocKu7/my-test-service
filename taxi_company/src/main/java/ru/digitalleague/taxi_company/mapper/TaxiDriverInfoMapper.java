package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import ru.digitalleague.core.model.TaxiDriverInfoModel;
import ru.digitalleague.taxi_company.model.DriverRating;

@Repository
@Mapper
public interface TaxiDriverInfoMapper {

    /**
     * Поиск водителя по параметрам.
     *
     * @param cityId идентификатор города
     * @return инфо о водителе
     */
    @Results(id = "drivers", value = {
            @Result(property = "driverId", column = "driver_id"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "level", column = "level"),
            @Result(property = "carModel", column = "car_model"),
            @Result(property = "createDttm", column = "create_dttm")
    })
    @Select("select driver_id, last_name, first_name, level, car_model, create_dttm, " +
            "               minute_cost, rating, is_free, city_id " +
            "        from taxi_drive_info " +
            "        where is_free=true " +
            "            and city_id=#{cityId} " +
            "        order by rating desc " +
            "        limit 1")
    TaxiDriverInfoModel findDriver(Long cityId);

    @Update("update taxi_drive_info set is_free=false where driver_id=#{driverId}")
    void lockDriver(Long driverId);

    @Update("update taxi_drive_info set is_free=true where driver_id=#{driverId}")
    void unlockDriver(Long driverId);

    @Select("select minute_cost from taxi_drive_info where driver_id=#{driverId}")
    int getMinuteCost(Long driverId);

    @Insert("insert into drivers_rating (driver_id, rating) VALUES (#{driverId}, #{rating})")
    void saveDriverRating(DriverRating driverRating);
}
