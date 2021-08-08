import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class SaveTwoArticle {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\mgris\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void saveTwoArticleAndDeleteOneOfThem()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search 'Search Wikipedia' input",
                5
            );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
            );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find topic search by Java",
                5
            );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article with title 'Object-oriented programming language'",
                15
            );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button 'More options'",
                5
            );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find button 'Add to reading list'",
                5
            );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button 'Got it'",
                5
            );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find text input",
                1
            );

        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into article input",
                1
            );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button",
                5
            );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
            );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Programming language']"),
                "Cannot find topic search by Java",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title 'Programming language'",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button 'More options'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find button 'Add to reading list'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find list 'Learning programming'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to 'My lists'",
                5
            );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find 'Learning programming' list",
                5
            );

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article 'Java (programming language)'"
            );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article 'Java (programming language)'",
                5
            );

        waitForElementPresent(
                By.id("//*[@text='JavaScript']"),
                "Cannot find article title 'JavaScript'",
                15
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 1);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 1);
        element.clear();
        return element;
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
}
