package com.opitzconsulting.bwertr.spec;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WelcomePage {
    private final WebDriver webDriver;

    public WelcomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void rateWithOnPage(String submittedRating) {
        WebElement option = webDriver.findElement(By.id(submittedRating));
        option.setSelected();
        option.submit();
    }

    public String numberOfRatingsShown() {
        return webDriver.findElement(By.id("numberOfRatings")).getText();
    }
}