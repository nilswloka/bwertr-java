package com.opitzconsulting.bwertr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class BwertrController {

    @Autowired
    private SimpleJdbcTemplate simpleJdbcTemplate;

    @ModelAttribute("possibleRatings")
    public List<String> possibleRatings() {
        return Arrays.asList("Poor", "Average", "Excellent");
    }

    @SuppressWarnings({"unchecked"})
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Map model) {
        model.put("numberOfRatings", numberOfRatings());
        return "welcome";
    }

    @SuppressWarnings({"unchecked"})
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String rate(@RequestParam String rating, Map model) {
        addRating(rating);
        model.put("submittedRating", rating);
        return "thankYou";
    }

    private int numberOfRatings() {
        return simpleJdbcTemplate.queryForInt("SELECT COUNT(*) FROM RATINGS");
    }

    private void addRating(String rating) {
        simpleJdbcTemplate.update("INSERT INTO RATINGS (RATING) VALUES (?)", valueOf(rating));
    }

    private int valueOf(String rating) {
        return possibleRatings().indexOf(rating);
    }

}
