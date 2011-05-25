package com.opitzconsulting.bwertr.spec;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ThankYouPage {

    private WebDriver webDriver;

    public ThankYouPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String submittedRatingShown() {
        return webDriver.findElement(By.id("submittedRating")).getText();
    }
}