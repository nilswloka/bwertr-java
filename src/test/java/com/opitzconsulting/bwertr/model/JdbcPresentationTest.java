package com.opitzconsulting.bwertr.model;

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

    private SimpleJdbcTemplate simpleJdbcTemplate = mock(SimpleJdbcTemplate.class);
    private Ratings ratings = mock(Ratings.class);
    private Presentation presentation = new JdbcPresentation(simpleJdbcTemplate, ratings);

    @Test
    public void withOneRating_averageRating_shouldReturnTextForThatRating() {
        String textForThatRating = "Two";
        givenRatings(2);
        andTextForRating(textForThatRating, 2);
        assertEquals(textForThatRating, presentation.averageRating());
    }

    @Test
    public void withOneAndThree_averageRating_shouldReturnTextForTwo() {
        String textForTwo = "Two";
        givenRatings(1, 3);
        andTextForRating(textForTwo, 2);
        assertEquals(textForTwo, presentation.averageRating());
    }

    @Test
    public void withOneAndTwo_averageRating_shouldReturnTextForTwo() {
        String textForTwo = "Two";
        givenRatings(1, 2);
        andTextForRating(textForTwo, 2);
        assertEquals(textForTwo, presentation.averageRating());
    }

    @Test
    public void withOneAndOneAndTwo_averageRating_shouldReturnTextForOne() {
        String textForOne = "One";
        givenRatings(1, 1, 2);
        andTextForRating(textForOne, 1);
        assertEquals(textForOne, presentation.averageRating());
    }

    @Test
    public void withNoRatings_averageRating_shouldReturnTextForUnknown() {
        String textForUnknown = "Unknown";
        givenNoRatings();
        andTextForUnknown(textForUnknown);
        assertEquals(textForUnknown, presentation.averageRating());
    }

    private void givenNoRatings() {
        givenRatings();
    }

    private void givenRatings(int... theRatings) {
        List<Map<String, Object>> resultWithRatings = new ArrayList<Map<String, Object>>();
        for (int rating : theRatings) {
            Map<String, Object> rowWithRating = new HashMap<String, Object>();
            rowWithRating.put("RATING", rating);
            resultWithRatings.add(rowWithRating);
        }
        when(simpleJdbcTemplate.queryForList(anyString())).thenReturn(resultWithRatings);
    }

    private void andTextForRating(String textForThatRating, int rating) {
        when(ratings.textForRating(rating)).thenReturn(textForThatRating);
    }

    private void andTextForUnknown(String textForUnknown) {
        when(ratings.textForUnknown()).thenReturn(textForUnknown);
    }

}
