package com.opitzconsulting.bwertr.spec;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;

public class ShowNumberOfRatingsTest extends AbstractSpringBwertrSpec {

    @Autowired
    private SimpleJdbcTemplate simpleJdbcTemplate;

    public String numberOfRatingsShownWhenThereAre(int numberOfRatings) {
        // Ensure number of ratings exist
        // * Reset application
        SimpleJdbcTestUtils.deleteFromTables(simpleJdbcTemplate, "RATINGS");
        // * Create number of ratings
        for (int count = 0; count < numberOfRatings; count++) {
            simpleJdbcTemplate.update("INSERT INTO RATINGS (RATING) VALUES (?)", 0);
        }
        // Visit application
        WebDriver webDriver = new HtmlUnitDriver();
        webDriver.get("http://localhost:8080/");
        // return number of ratings shown
        return webDriver.findElement(By.id("numberOfRatings")).getText();
    }

}
