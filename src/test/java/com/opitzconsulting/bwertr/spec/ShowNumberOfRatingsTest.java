package com.opitzconsulting.bwertr.spec;

public class ShowNumberOfRatingsTest extends AbstractSpringBwertrSpec {

    public String numberOfRatingsShownWhenThereAre(int numberOfRatings) {
        ensureRatingsExist(numberOfRatings);
        bwertrDriver.visitBwertr();
        return bwertrDriver.getWelcomePage().numberOfRatingsShown();
    }

    private void ensureRatingsExist(int numberOfRatings) {
        resetApplication();
        createRatings(numberOfRatings);
    }

    private void createRatings(int numberOfRatings) {
        for (int count = 0; count < numberOfRatings; count++) {
            bwertrDriver.rateWith("Poor");
        }
    }

}
