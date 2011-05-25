package com.opitzconsulting.bwertr.model;

import java.util.List;

public interface Presentation {
    List<String> possibleRatings();

    String averageRating();

    void addRating(String rating);

    int numberOfRatings();
}
