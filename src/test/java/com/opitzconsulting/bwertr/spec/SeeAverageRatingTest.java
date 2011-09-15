package com.opitzconsulting.bwertr.spec;

public class SeeAverageRatingTest extends AbstractSpringBwertrSpec {

    public String averageRatingFor(String listOfRatings) {
        resetApplication();
        if (!listOfRatings.isEmpty()) {
            for (String rating : listOfRatings.split(",")) {
                bwertrDriver.rateWith(rating);
            }
        }
        bwertrDriver.visitResults();
        return bwertrDriver.getResultsPage().averageRatingShown();
    }

}
