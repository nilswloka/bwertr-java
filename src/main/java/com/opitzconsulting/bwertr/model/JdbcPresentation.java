package com.opitzconsulting.bwertr.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class JdbcPresentation implements Presentation {

    private JdbcTemplate jdbcTemplate;
    private Ratings ratings;

    @Autowired
    public JdbcPresentation(JdbcTemplate jdbcTemplate, Ratings ratings) {
        this.jdbcTemplate = jdbcTemplate;
        this.ratings = ratings;
    }

    public List<String> possibleRatings() {
        return ratings.possibleRatings();
    }

    public String averageRating() {
        List<Map<String, Object>> ratingRows = allRatingRows();
        if (ratingRows.isEmpty())
            return ratings.textForUnknown();
        return ratings.textFor(averageOf(ratingRows));
    }

    private List<Map<String, Object>> allRatingRows() {
        return jdbcTemplate.queryForList("SELECT RATING FROM RATINGS");
    }

    private int averageOf(List<Map<String, Object>> ratingRows) {
        return (int) Math.round((double) sumOfRatings(ratingRows) / ratingRows.size());
    }

    private int sumOfRatings(List<Map<String, Object>> ratingRows) {
        int sumOfRatings = 0;
        for (Map<String,Object> ratingRow : ratingRows) {
            sumOfRatings += (Integer) ratingRow.get("RATING");
        }
        return sumOfRatings;
    }

    public void addRating(String rating) {
        jdbcTemplate.update("INSERT INTO RATINGS (RATING) VALUES (?)", ratings.valueOf(rating));
    }

    public int numberOfRatings() {
        return jdbcTemplate.queryForInt("SELECT COUNT(*) FROM RATINGS");
    }

}