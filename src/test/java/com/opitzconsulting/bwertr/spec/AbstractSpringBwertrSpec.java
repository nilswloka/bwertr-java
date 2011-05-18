package com.opitzconsulting.bwertr.spec;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml")
public abstract class AbstractSpringBwertrSpec extends AbstractBwertrSpec {
    @Autowired
    private SimpleJdbcTemplate simpleJdbcTemplate;

    protected void resetBwertr() {
        SimpleJdbcTestUtils.deleteFromTables(simpleJdbcTemplate, "RATINGS");
    }
}
