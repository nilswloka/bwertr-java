package com.opitzconsulting.bwertr.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcPresentationTest {

    private JdbcTemplate jdbcTemplate;
    private Ratings ratings;
    private Presentation jdbcPresentation;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = mock(JdbcTemplate.class);
        ratings = mock(Ratings.class);
        jdbcPresentation = new JdbcPresentation(jdbcTemplate, ratings);
    }

    @Test
    public void average_rating_for_one_is_text_for_one() {
        int one = 1;
        String textForOne = "one";
        givenRatings(one);
        andGivenTextForRating(textForOne, one);
        assertEquals(textForOne, jdbcPresentation.averageRating());
    }

    @Test
    public void average_rating_for_one_and_three_is_text_for_two() {
        int[] oneAndThree = {1, 3};
        String textForTwo = "two";
        givenRatings(oneAndThree);
        andGivenTextForRating(textForTwo, 2);
        assertEquals(textForTwo, jdbcPresentation.averageRating());
    }

    @Test
    public void average_rating_for_one_and_two_is_text_for_two() {
        int[] oneAndTwo = {1, 2};
        String textForTwo = "foo";
        givenRatings(oneAndTwo);
        andGivenTextForRating(textForTwo, 2);
        assertEquals(textForTwo, jdbcPresentation.averageRating());
    }

    @Test
    public void average_rating_for_no_rating_is_text_for_unknown() {
        givenRatings();
        String textForUnknown = "unknown";
        when(ratings.textForUnknown()).thenReturn(textForUnknown);
        assertEquals(textForUnknown, jdbcPresentation.averageRating());
    }

    private void givenRatings(int... ratings) {
        List<Map<String, Object>> listWithRatingRows = new ArrayList<Map<String, Object>>();
        for (int rating : ratings) {
            Map<String, Object> rowForRating = new HashMap<String, Object>();
            rowForRating.put("RATING", rating);
            listWithRatingRows.add(rowForRating);
        }
        when(jdbcTemplate.queryForList(anyString())).thenReturn(listWithRatingRows);
    }

    private void andGivenTextForRating(String textForRating, int rating) {
        when(ratings.textFor(rating)).thenReturn(textForRating);
    }

}
