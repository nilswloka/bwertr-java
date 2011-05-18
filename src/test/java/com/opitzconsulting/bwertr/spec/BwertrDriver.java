package com.opitzconsulting.bwertr.spec;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class BwertrDriver {
    private WebDriver webDriver = new HtmlUnitDriver();
    private final WelcomePage welcomePage = new WelcomePage(webDriver);
    private final ThankYouPage thankYouPage = new ThankYouPage(webDriver);
    private ResultsPage resultsPage = new ResultsPage(webDriver);

    public void rateWith(String submittedRating) {
        visitBwertr();
        getWelcomePage().rateWithOnPage(submittedRating);
    }

    public void visitBwertr() {
        webDriver.get("http://localhost:8080/");
    }

    public void visitResults() {
        webDriver.get("http://localhost:8080/results");
    }

    public WelcomePage getWelcomePage() {
        return welcomePage;
    }

    public ThankYouPage getThankYouPage() {
        return thankYouPage;
    }

    public ResultsPage getResultsPage() {
        return resultsPage;
    }
}