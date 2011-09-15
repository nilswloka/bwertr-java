package com.opitzconsulting.bwertr.spec;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ShowNumberOfRatingsTest extends AbstractSpringBwertrSpec {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String numberOfRatingsShownWhenThereAre(int numberOfRatings) {
        // Ensure that there are number of ratings
        // * Reset application
        jdbcTemplate.update("DELETE FROM RATINGS");
        // * Create number of ratings
        for (int count = 0; count < numberOfRatings; count++) {
            jdbcTemplate.update("INSERT INTO RATINGS (RATING) VALUES (?)", 0);
        }
        // Visit application
        WebDriver webDriver = new HtmlUnitDriver();
        webDriver.get("http://localhost:8080/");
        // Return number of ratings shown
        return webDriver.findElement(By.id("numberOfRatings")).getText();
    }

}