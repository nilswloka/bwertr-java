package com.opitzconsulting.bwertr.model;

import java.util.List;

public interface Presentation {
    String averageRating();

    void addRating(String rating);

    int numberOfRatings();

    List<String> possibleRatings();
}
