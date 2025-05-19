package stepDefinitions;

import io.cucumber.java.en.*;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.assertTrue;

public class SearchSteps {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    String keyword;

    public static WebDriver getDriver() {
        return driver.get();
    }

    @Given("I open DuckDuckGo search engine")
    public void openDuckDuckGo() {
        driver.set(new ChromeDriver());
        getDriver().get("https://duckduckgo.com");
    }

    @Step("Searching for: {keyword}")
    @When("I search for {string}")
    public void searchForKeyword(String keyword) {
        this.keyword = keyword;
        WebElement searchBox = getDriver().findElement(By.name("q"));
        searchBox.sendKeys(keyword);
        searchBox.submit();
    }

    @Step("Verifying results contain: {keyword}")
    @Then("I should see results related to {string}")
    public void verifyResults(String keyword) {
        String pageText = getDriver().getPageSource().toLowerCase();
        savePageSource(pageText);
        assertTrue(pageText.contains(keyword.toLowerCase()), "Keyword not found in search results!");
        getDriver().quit();
    }

    @Attachment(value = "Page Source", type = "text/html")
    public String savePageSource(String html) {
        return html;
    }
}
