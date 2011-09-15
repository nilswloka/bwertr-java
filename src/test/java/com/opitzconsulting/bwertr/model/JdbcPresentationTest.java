package com.opitzconsulting.bwertr.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcPresentationTest {

    private Ratings ratings;
    private JdbcTemplate jdbcTemplate;
    private JdbcPresentation presentation;

    @Before
    public void setUp() throws Exception {
        ratings = mock(Ratings.class);
        jdbcTemplate = mock(JdbcTemplate.class);
        presentation = new JdbcPresentation(jdbcTemplate, ratings);
    }

    @Test
    public void averageRating_ratingOne_returnsTextForOne() {
        String textForOne = "textForOne";
        givenRatings(1);
        andTextForRating(textForOne, 1);
        assertThat(presentation.averageRating(), is(textForOne));
    }

    @Test
    public void averageRating_ratingsOneAndThree_returnsTextForTwo() {
        String textForTwo = "textForTwo";
        givenRatings(1, 3);
        andTextForRating(textForTwo, 2);
        assertThat(presentation.averageRating(), is(textForTwo));
    }

    @Test
    public void averageRating_ratingsOneAndTwo_returnsTextForTwo() {
        String textForTwo = "textForTwo";
        givenRatings(1, 2);
        andTextForRating(textForTwo, 2);
        assertThat(presentation.averageRating(), is(textForTwo));
    }

    @Test
    public void averageRating_noRatings_returnsTextForUnknown() {
        String textForUnknown = "foo";
        givenNoRatings();
        andTextForUnknown(textForUnknown);
        assertThat(presentation.averageRating(), is(textForUnknown));
    }

    private void andTextForUnknown(String textForUnknown) {
        when(ratings.textForUnknown()).thenReturn(textForUnknown);
    }

    private void givenNoRatings() {
        givenRatings();
    }

    private void givenRatings(int... someRatings) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (int rating : someRatings) {
            Map<String, Object> rowWithRating = new HashMap<String, Object>();
            rowWithRating.put("RATING", rating);
            result.add(rowWithRating);
        }
        when(jdbcTemplate.queryForList(anyString())).thenReturn(result);
    }

    private void andTextForRating(String text, int rating) {
        when(ratings.textFor(rating)).thenReturn(text);
    }

}
