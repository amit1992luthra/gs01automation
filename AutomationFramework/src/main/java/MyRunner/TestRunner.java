package MyRunner;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import com.core.Hooks;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/java/Features", 
		glue = { "stepDefinitions" ,"com.core"},
		monochrome = true, // display the console output in a proper readable
							// format
		strict = true, // it will check if any step is not defined in step
						// definition file
		dryRun = false, // to check the mapping is proper between feature file
						// and step def file
		tags = {"@current"},
	//	format = { "pretty", "html:test-outout","ru.yandex.qatools.allure.cucumberjvm.AllureReporter" ,"json:json_output"},
		plugin = { "pretty", "html:target/cucumber-html-reports","io.qameta.allure.cucumberjvm.AllureCucumberJvm", 
		        "json:target/cucumber-html-reports/cucumber.json" }
	//"ru.yandex.qatools.allure.cucumberjvm.AllureReporter",	        

)

public class TestRunner extends Hooks {
	


}

