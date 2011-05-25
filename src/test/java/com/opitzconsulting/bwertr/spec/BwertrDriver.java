package com.opitzconsulting.bwertr.spec;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class BwertrDriver {

    private WebDriver webDriver = new HtmlUnitDriver();
    private WelcomePage welcomePage = new WelcomePage(webDriver);
    private ThankYouPage thankYouPage = new ThankYouPage(webDriver);
    private ResultsPage resultsPage = new ResultsPage(webDriver);

    public BwertrDriver() {
    }

    public WelcomePage getWelcomePage() {
        return welcomePage;
    }

    public ThankYouPage getThankYouPage() {
        return thankYouPage;
    }

    public void visitBwertr() {
        webDriver.get("http://localhost:8080/");
    }

    public void rateWith(String rating) {
        visitBwertr();
        getWelcomePage().rateWithOnWelcomePage(rating);
    }

    public void rateWithSomeValidRating() {
        rateWith("Poor");
    }

    public void visitResults() {
        webDriver.get("http://localhost:8080/results");
    }

    public ResultsPage getResultsPage() {
        return resultsPage;
    }
}