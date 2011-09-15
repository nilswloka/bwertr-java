package com.opitzconsulting.bwertr.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class JdbcPresentation {

    private final JdbcTemplate jdbcTemplate;
    private final Ratings ratings;

    @Autowired
    public JdbcPresentation(JdbcTemplate jdbcTemplate, Ratings ratings) {
        this.jdbcTemplate = jdbcTemplate;
        this.ratings = ratings;
    }

    public String averageRating() {
        List<Map<String, Object>> allRatings = allRatings();
        if (allRatings.isEmpty())
            return ratings.textForUnknown();
        return ratings.textFor(averageOf(allRatings));
    }

    private int averageOf(List<Map<String, Object>> theRatings) {
        return (int) Math.round((double) sumOfRatings(theRatings) / theRatings.size());
    }

    private int sumOfRatings(List<Map<String, Object>> theRatings) {
        int sum = 0;
        for (Map<String, Object> rating : theRatings) {
            sum += (Integer) rating.get("RATING");
        }
        return sum;
    }

    private List<Map<String, Object>> allRatings() {
        return jdbcTemplate.queryForList("SELECT * FROM RATINGS");
    }

    public void addRating(String rating) {
        jdbcTemplate.update("INSERT INTO RATINGS (RATING) VALUES (?)", ratings.valueOf(rating));
    }

    public List<String> possibleRatings() {
        return ratings.possibleRatings();
    }

    public int numberOfRatings() {
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM RATINGS");
    }
}