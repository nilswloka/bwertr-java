package com.opitzconsulting.bwertr.spec;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class RateAPresentationTest extends AbstractBwertrSpec {

    public String ratingShownAfterRatingWith(String submittedRating) {
        // Add rating
        // * Visit bwertr
        WebDriver webDriver = new HtmlUnitDriver();
        webDriver.get("http://localhost:8080/");
        // * Choose rating and submit
        WebElement option = webDriver.findElement(By.id(submittedRating));
        option.setSelected();
        option.submit();
        // Return rating shown
        return webDriver.findElement(By.id("submittedRating")).getText();
    }

}
