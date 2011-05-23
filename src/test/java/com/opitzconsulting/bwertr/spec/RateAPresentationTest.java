package com.opitzconsulting.bwertr.spec;

public class RateAPresentationTest extends AbstractBwertrSpec {

    public String ratingShownAfterRatingWith(String givenRating) {
        bwertrDriver.rateWith(givenRating);
        return bwertrDriver.getThankYouPage().ratingShown();
    }

}
