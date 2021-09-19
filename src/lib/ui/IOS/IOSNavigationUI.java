package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUI extends NavigationUI
{
    static {
        MY_LISTS_LINK = "//android.widget.FrameLayout[@content-desc='My lists']";
    }

    public IOSNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
}
