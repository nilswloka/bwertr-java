package com.opitzconsulting.bwertr.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class JdbcPresentation implements Presentation {

    private SimpleJdbcTemplate simpleJdbcTemplate;
    private Ratings ratings;

    @Autowired
    public JdbcPresentation(SimpleJdbcTemplate simpleJdbcTemplate, Ratings ratings) {
        this.simpleJdbcTemplate = simpleJdbcTemplate;
        this.ratings = ratings;
    }

    public int numberOfRatings() {
        return simpleJdbcTemplate.queryForInt("SELECT COUNT(*) FROM RATINGS");
    }

    public void addRating(String rating) {
        simpleJdbcTemplate.update("INSERT INTO RATINGS (RATING) VALUES (?)", ratings.valueOf(rating));
    }

    public String averageRating() {
        List<Map<String, Object>> resultWithAllRatings = allRatings();
        if (resultWithAllRatings.size() == 0) {
            return ratings.textForUnknown();
        }
        return ratings.textForRating(calculateAverageRating(resultWithAllRatings));
    }

    public List<String> validRatings() {
        return ratings.validRatings();
    }

    private List<Map<String, Object>> allRatings() {
        return simpleJdbcTemplate.queryForList("SELECT RATING FROM RATINGS");
    }

    private int calculateAverageRating(List<Map<String, Object>> resultWithRatings) {
        int sumOfRatings = sumOfRatingsIn(resultWithRatings);
        return (int) Math.round((double) sumOfRatings / resultWithRatings.size());
    }

    private int sumOfRatingsIn(List<Map<String, Object>> resultWithRatings) {
        int sumOfRatings = 0;
        for (Map<String, Object> rowWithRating : resultWithRatings) {
            sumOfRatings += (Integer) rowWithRating.get("RATING");
        }
        return sumOfRatings;
    }

}