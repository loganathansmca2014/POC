package Runner;

import Util.ReportGeneration;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterSuite;

@CucumberOptions(
        features = "@target/failedrerun.txt",
        glue = {"Stepdef"},
        tags = {"@TC03"},
        plugin = { "json:target/cucumber.json",
                "html:target/cucumber-reports/html",
                },
        monochrome = true)
public class FailedRunner extends AbstractTestNGCucumberTests {


  @AfterSuite
  public void TearDownReportGeneration() throws Exception {
//GlobalFunction.driver.quit();
    ReportGeneration.generateJVMReport();

  }


}