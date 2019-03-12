package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Logging.Logging;
import SupportClasses.SetupClass.SetupClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.lang.reflect.Method;

public class HeaderButtonsCheck {
    public static void HeaderLinks(String xpath_without_ending, Logger log, String calling_method) throws Exception{
        String[] expected_urls = {"https://secure-seriesone.dynamo-ny.com/deals", "https://seriesone.dynamo-ny.com/start-raising-funds/",
                "https://seriesone.dynamo-ny.com/brokerone/", "https://seriesone.dynamo-ny.com/learn/", "https://seriesone.dynamo-ny.com/digital-security-offerings/"};

        for(int i = 1;i <= expected_urls.length; i++) {
            WebElement button = SetupClass.GetDriver().findElement(By.xpath(xpath_without_ending + i + "]/a"));
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(button));
            button.click();
            Thread.sleep(1000);
            if (!SetupClass.GetDriver().getCurrentUrl().equals(expected_urls[i-1])) {
                Logging.Log_error(log, "Button with expected url (" + expected_urls[i-1] + ") works incorrectly");
                throw new NewAssertError("Header button with expected url (" + expected_urls[i-1] + ") goes on the wrong page: " + SetupClass.GetDriver().getCurrentUrl());
            }
            Logging.Log_debug(log, "Button with expected url (" + expected_urls[i-1] + ") works correctly");
            calling_method = calling_method.replace("_HeaderButtons","");

            Method method = PagesURL.class.getMethod(calling_method);
            method.invoke(null);
        }
    }
}
