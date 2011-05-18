package com.opitzconsulting.bwertr.model;

import java.util.List;

public interface Presentation {
    int numberOfRatings();

    void addRating(String rating);

    String averageRating();

    List<String> validRatings();
}
