package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject
{
        static {
        ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";
    }

    public IOSMyListsPageObject(AppiumDriver driver)
        {
            super(driver);
        }
}
