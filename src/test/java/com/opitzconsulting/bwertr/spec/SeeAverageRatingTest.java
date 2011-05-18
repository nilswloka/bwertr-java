package com.opitzconsulting.bwertr.spec;

public class SeeAverageRatingTest extends AbstractSpringBwertrSpec {

    public String averageRatingFor(String ratings) {
        resetBwertr();
        createRatings(ratings);
        bwertrDriver.visitResults();
        return bwertrDriver.getResultsPage().averageRating();
    }

    private void createRatings(String ratings) {
        if (!ratings.isEmpty()) {
            for (String rating : ratings.split(",")) {
                bwertrDriver.rateWith(rating);
            }
        }
    }

}
