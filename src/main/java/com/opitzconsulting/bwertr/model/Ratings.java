package com.opitzconsulting.bwertr.model;

import java.util.List;

public interface Ratings {
    String textForRating(int rating);

    String textForUnknown();

    List<String> validRatings();

    int valueOf(String rating);
}
