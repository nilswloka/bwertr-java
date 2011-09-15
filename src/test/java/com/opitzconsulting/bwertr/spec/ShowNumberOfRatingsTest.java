package com.opitzconsulting.bwertr.spec;

public class ShowNumberOfRatingsTest extends AbstractSpringBwertrSpec {

    public String numberOfRatingsShownWhenThereAre(int numberOfRatings) {
        ensureNumberOfRatingsExist(numberOfRatings);
        bwertrDriver.visitApplication();
        return bwertrDriver.getWelcomePage().numberOfRatingsShown();
    }

    private void ensureNumberOfRatingsExist(int numberOfRatings) {
        resetApplication();
        for (int count = 0; count < numberOfRatings; count++) {
            bwertrDriver.rateWith("Average");
        }
    }

}