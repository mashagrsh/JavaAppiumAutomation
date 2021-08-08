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

public class SaveTwoArticles {

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
        String search_input_on_main_page = "Search Wikipedia";
        String search_text_input = "Search…";
        String search_line = "Java";
        String name_of_folder = "Learning programming";
        String article_with_java = "Java (programming language)";
        String article_with_javascript = "JavaScript";

        String more_options_locator = "//android.widget.ImageView[@content-desc='More options']";
        String add_to_list_locator = "//*[@text='Add to reading list']";
        String x_link_locator = "//android.widget.ImageButton[@content-desc='Navigate up']";

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'"+ search_input_on_main_page +"')]"),
                "Cannot find input with " + search_input_on_main_page,
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'"+ search_text_input +"')]"),
                search_line,
                "Cannot find input with " + search_text_input,
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find topic search " + search_line,
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article with title " + article_with_java,
                15
        );

        waitForElementAndClick(
                By.xpath(more_options_locator),
                "Cannot find button 'More options'",
                5
        );

        waitForElementAndClick(
                By.xpath(add_to_list_locator),
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
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into article input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button",
                5
        );

        waitForElementAndClick(
                By.xpath(x_link_locator),
                "Cannot close article, cannot find X link",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'"+ search_input_on_main_page +"')]"),
                "Cannot find input with " + search_input_on_main_page,
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'"+ search_text_input +"')]"),
                search_line,
                "Cannot find input with " + search_text_input,
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Programming language']"),
                "Cannot find topic search " + search_line,
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title " + article_with_javascript,
                15
        );

        waitForElementAndClick(
                By.xpath(more_options_locator),
                "Cannot find button 'More options'",
                5
        );

        waitForElementAndClick(
                By.xpath(add_to_list_locator),
                "Cannot find button 'Add to reading list'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='"+ name_of_folder +"']"),
                "Cannot find list " + name_of_folder,
                5
        );

        waitForElementAndClick(
                By.xpath(x_link_locator),
                "Cannot close article, cannot find X link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to 'My lists'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='"+ name_of_folder +"']"),
                "Cannot find " + name_of_folder + " list",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text='"+ article_with_java +"']"),
                "Cannot find saved article " + article_with_java
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='"+ article_with_java +"']"),
                "Cannot delete saved article " + article_with_java,
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='"+ article_with_javascript +"']"),
                "Cannot find article title " + article_with_javascript,
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']//*[@text='"+ article_with_javascript +"']"),
                "The title of article is not " + article_with_javascript,
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
        WebElement element = waitForElementPresent(by, error_message, 5);
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
        WebElement element = waitForElementPresent(by, error_message, 5);
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