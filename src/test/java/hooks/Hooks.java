package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import stepDefinitions.SearchSteps;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("Test Started");
    }
    
//    @After
//    public void tearDown() {
//        System.out.println("Test Finished");
//    }
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) SearchSteps.getDriver()).getScreenshotAs(OutputType.BYTES);
            saveScreenshotToAllure(screenshot);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }
        if (SearchSteps.getDriver() != null) {
            SearchSteps.getDriver().quit();
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshotToAllure(byte[] screenShot) {
        return screenShot;
    }
}
