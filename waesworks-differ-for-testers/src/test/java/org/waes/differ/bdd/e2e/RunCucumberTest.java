package org.waes.differ.bdd.e2e;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(//tags ={"@FunctionalScenarios"},
        plugin = {"pretty" ,"html:report" ,
                "json:report/cucumber.json" ,
                "junit:report/cucumber.xml",
                "html:report/cucumber-html-report",
                "rerun:report/rerun.txt"
                },
        features = "src\\test\\resources\\org.waes.differ\\features\\",
        glue = {"org.waes.differ.bdd.steps"})
public class RunCucumberTest {

}