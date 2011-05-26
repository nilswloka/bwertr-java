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

    public String averageRating() {
        List<Map<String, Object>> allRatings = allRatings();
        if (allRatings.isEmpty())
            return ratings.textForUnknown();
        return ratings.textFor(averageOf(allRatings));
    }

    private int averageOf(List<Map<String, Object>> allRatings) {
        return (int) Math.round((double) sumOfRatings(allRatings) / allRatings.size());
    }

    private int sumOfRatings(List<Map<String, Object>> allRatings) {
        int sumOfRatings = 0;
        for (Map<String, Object> ratingRow : allRatings) {
            sumOfRatings += (Integer) ratingRow.get("RATING");
        }
        return sumOfRatings;
    }

    private List<Map<String, Object>> allRatings() {
        return simpleJdbcTemplate.queryForList("SELECT RATING FROM RATINGS");
    }

    public void addRating(String rating) {
        simpleJdbcTemplate.update("INSERT INTO RATINGS (RATING) VALUES (?)", ratings.valueOf(rating));
    }

    public int numberOfRatings() {
        return simpleJdbcTemplate.queryForInt("SELECT COUNT(*) FROM RATINGS");
    }

    public List<String> possibleRatings() {
        return ratings.possibleRatings();
    }

}