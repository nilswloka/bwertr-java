package com.opitzconsulting.bwertr.spec;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class BwertrDriver {

    private WebDriver webDriver = new HtmlUnitDriver();
    private final WelcomePage welcomePage = new WelcomePage(webDriver);
    private final ThankYouPage thankYouPage = new ThankYouPage(webDriver);

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
        getWelcomePage().rateWith(rating);
    }

}