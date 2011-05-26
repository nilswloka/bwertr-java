package com.opitzconsulting.bwertr.model;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PoorAverageExcellent implements Ratings {

    public List<String> possibleRatings() {
        return Arrays.asList("Poor", "Average", "Excellent");
    }

    public String textFor(int rating) {
        return possibleRatings().get(rating);
    }

    public String textForUnknown() {
        return "Unknown";
    }

    public int valueOf(String rating) {
        return possibleRatings().indexOf(rating);
    }
}
