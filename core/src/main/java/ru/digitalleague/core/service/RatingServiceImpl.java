package ru.digitalleague.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.digitalleague.core.api.RatingService;
import ru.digitalleague.core.mapper.RatingMapper;
import ru.digitalleague.core.model.FeedBackModel;

/**
 * Сервис по работе с рейтингом водителя.
 */
@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingMapper ratingMapper;

    @Override
    public void save(FeedBackModel feedBackModel) {
        ratingMapper.save(feedBackModel);
        updateDriverRating(feedBackModel.getDriverId());
    }

    @Override
    public void updateDriverRating(Long driverId) {
        List<FeedBackModel> driverRatings = ratingMapper.getDriverRatings(driverId);

        double ratingSum = driverRatings.stream().mapToDouble(FeedBackModel::getRating).sum();
        double newRating = ratingSum/driverRatings.size();
        ratingMapper.updateDriverRating(driverId, newRating);
    }
}
