package com.opitzconsulting.bwertr.spec;

public class SeeAverageRatingTest extends AbstractSpringBwertrSpec {

    public String averageRatingFor(String listOfRatings) {
        resetApplication();
        addRatingsFrom(listOfRatings);
        bwertrDriver.visitResults();
        return bwertrDriver.getResultsPage().averageRating();
    }

    private void addRatingsFrom(String listOfRatings) {
        if (!listOfRatings.isEmpty()) {
            for (String rating : listOfRatings.split(",")) {
                bwertrDriver.rateWith(rating);
            }
        }
    }

}
