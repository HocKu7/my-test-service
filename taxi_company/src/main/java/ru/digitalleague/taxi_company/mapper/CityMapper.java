package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CityMapper {

    @Select("select city_id from city_queue where name = #{cityName}")
    Long findCityIdByName(String cityName);
}
