package com.opitzconsulting.bwertr.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcPresentationTest {

    private SimpleJdbcTemplate simpleJdbcTemplate;
    private Ratings ratings;
    private Presentation presentation;

    @Before
    public void setUp() throws Exception {
        simpleJdbcTemplate = mock(SimpleJdbcTemplate.class);
        ratings = mock(Ratings.class);
        presentation = new JdbcPresentation(simpleJdbcTemplate, ratings);
    }

    @Test
    public void averageRating_with_one_returns_text_for_one() {
        int one = 1;
        String textForOne = "One";
        givenRatings(one);
        andGivenTextForRating(textForOne, one);
        assertEquals(textForOne, presentation.averageRating());
    }

    @Test
    public void averageRating_with_one_and_three_return_text_for_two() {
        int[] oneAndThree = {1, 3};
        String textForTwo = "Foo";
        givenRatings(oneAndThree);
        andGivenTextForRating(textForTwo, 2);
        assertEquals(textForTwo, presentation.averageRating());
    }

    @Test
    public void averageRating_with_one_and_two_return_text_for_two() {
        int[] oneAndTwo = {1, 2};
        String textForTwo = "Two";
        givenRatings(oneAndTwo);
        andGivenTextForRating(textForTwo, 2);
        assertEquals(textForTwo, presentation.averageRating());
    }

    @Test
    public void averageRating_with_no_ratings_returns_text_for_unknown() {
        givenNoRatings();
        String textForUnknown = "textForUnknown";
        when(ratings.textForUnknown()).thenReturn(textForUnknown);
        assertEquals(textForUnknown, presentation.averageRating());
    }

    private void andGivenTextForRating(String textForRating, int rating) {
        when(ratings.textFor(rating)).thenReturn(textForRating);
    }

    private void givenRatings(int... ratings) {
        List<Map<String, Object>> resultWithRatingOneAndThree = new ArrayList<Map<String, Object>>();
        for (int rating : ratings) {
            Map<String, Object> rowWithRating = new HashMap<String, Object>();
            rowWithRating.put("RATING", rating);
            resultWithRatingOneAndThree.add(rowWithRating);
        }
        when(simpleJdbcTemplate.queryForList(anyString())).thenReturn(resultWithRatingOneAndThree);
    }

    private void givenNoRatings() {
        givenRatings();
    }

}
