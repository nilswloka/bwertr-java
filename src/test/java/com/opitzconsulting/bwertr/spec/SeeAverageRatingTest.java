package com.opitzconsulting.bwertr.spec;

public class SeeAverageRatingTest extends AbstractSpringBwertrSpec {

    public String averageRatingFor(String listOfRatings) {
        resetApplication();
        if (!listOfRatings.isEmpty())
            createRatings(listOfRatings);
        bwertrDriver.visitResults();
        return bwertrDriver.getResultsPage().averageRating();
    }

    private void createRatings(String listOfRatings) {
        for (String rating : listOfRatings.split(",")) {
            bwertrDriver.rateWith(rating);
        }
    }

}
