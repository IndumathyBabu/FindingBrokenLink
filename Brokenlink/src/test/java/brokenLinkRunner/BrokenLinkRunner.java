package brokenLinkRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/brokenLinkFeature",glue= {"brokenLink"})

public class BrokenLinkRunner {

}
