package ru.digitalleague.taxi_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.digitalleague.taxi_company.api.DriverService;
import ru.digitalleague.taxi_company.mapper.TaxiDriverInfoMapper;
import ru.digitalleague.taxi_company.model.DriverRating;

/**
 * Реализация сервиса по работе с водителями.
 */
@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private TaxiDriverInfoMapper taxiDriverInfoMapper;

    @Override
    public void lockDriver(Long driverId) {
        taxiDriverInfoMapper.lockDriver(driverId);
    }

    @Override
    public void unlockDriver(Long driverId) {
        taxiDriverInfoMapper.unlockDriver(driverId);
    }

    @Override
    public int getMinuteCost(Long driverId) {
        return taxiDriverInfoMapper.getMinuteCost(driverId);
    }

    @Override
    public void saveDriverRating(DriverRating driverRating) {
        taxiDriverInfoMapper.saveDriverRating(driverRating);
    }
}
