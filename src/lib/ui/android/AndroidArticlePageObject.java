package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "//*[@text='View page in browser']";
        OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']";
        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button";
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "//*[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";
        TITLE_WITH_NAME_TPL = "//*[@text='(TITLE)']";
        MY_LIST_NAME = "//*[@text='Learning programming']";
        HEADER = "org.wikipedia:id/page_header_view";
    }

    public AndroidArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

}
