package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Logging.Logging;
import SupportClasses.SetupClass.SetupClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.lang.reflect.Method;

public class SocialMediaIcons {
    public static void LinksCheck(String calling_method, Logger log) throws Exception{
        calling_method = calling_method.replace("_SocialMediaIcons","");

        Method method = PagesURL.class.getMethod(calling_method);
        method.invoke(null);
        Thread.sleep(500);

        String parentWindow = SetupClass.GetDriver().getWindowHandle();

        SetupClass.GetDriver().findElement(By.xpath("//img[@src='https://secure-seriesone.dynamo-ny.com/bundles/frontend/images/symbols/social/linkedIn2.svg']")).click();
        Thread.sleep(2000);


        for(String windowHandle : SetupClass.GetDriver().getWindowHandles())
            {
                Thread.sleep(500);
                if(!windowHandle.equals(parentWindow))
                {
                SetupClass.GetDriver().switchTo().window(windowHandle);
                SetupClass.GetDriverWait().until(ExpectedConditions.urlContains("https://www.linkedin.com/"));

                Thread.sleep(2000);
                if (!SetupClass.GetDriver().getCurrentUrl().equals("https://www.linkedin.com/company/seriesone") &&
                        !SetupClass.GetDriver().getCurrentUrl().contains("https://www.linkedin.com/authwall")) {
                    Logging.Log_error(log, "'LinkedIn Logo' button has been broken");
                    throw new NewAssertError("'LinkedIn Logo' button has been broken");
                }
                SetupClass.GetDriver().close(); //closing child window
                SetupClass.GetDriver().switchTo().window(parentWindow); //cntrl to parent window
                Logging.Log_debug(log, "'LinkedIn Logo' button works fine");
            }
        }

        Thread.sleep(500);
        parentWindow = SetupClass.GetDriver().getWindowHandle();

        SetupClass.GetDriver().findElement(By.xpath("//img[@src='https://secure-seriesone.dynamo-ny.com/bundles/frontend/images/symbols/social/facebook2.svg']")).click();
        Thread.sleep(2000);

        for(String windowHandle : SetupClass.GetDriver().getWindowHandles())
        {
            Thread.sleep(500);
            if(!windowHandle.equals(parentWindow))
            {
                SetupClass.GetDriver().switchTo().window(windowHandle);
                SetupClass.GetDriverWait().until(ExpectedConditions.urlContains("https://business.facebook.com/"));

                Thread.sleep(2000);
                if (!SetupClass.GetDriver().getCurrentUrl().equals("https://business.facebook.com/seriesOneFinance/") &&
                        !SetupClass.GetDriver().getCurrentUrl().equals("https://business.facebook.com/SeriesOne-162178631110154")) {
                    Logging.Log_error(log, "'Facebook Logo' button has been broken");
                    throw new NewAssertError("'Facebook Logo' button has been broken");
                }
                SetupClass.GetDriver().close(); //closing child window
                SetupClass.GetDriver().switchTo().window(parentWindow); //cntrl to parent window
                Logging.Log_debug(log, "'Facebook Logo' button works fine");
            }
        }

        Thread.sleep(500);
        parentWindow = SetupClass.GetDriver().getWindowHandle();

        SetupClass.GetDriver().findElement(By.xpath("//img[@src='https://secure-seriesone.dynamo-ny.com/bundles/frontend/images/symbols/social/twitter2.svg']")).click();
        Thread.sleep(2000);

        for(String windowHandle : SetupClass.GetDriver().getWindowHandles())
        {
            Thread.sleep(500);
            if(!windowHandle.equals(parentWindow))
            {
                SetupClass.GetDriver().switchTo().window(windowHandle);
                SetupClass.GetDriverWait().until(ExpectedConditions.urlContains("https://twitter.com/"));

                Thread.sleep(2000);
                if (!SetupClass.GetDriver().getCurrentUrl().equals("https://twitter.com/seriesone_info")) {
                    Logging.Log_error(log, "'Twitter Logo' button has been broken");
                    throw new NewAssertError("'Twitter Logo' button has been broken");
                }
                SetupClass.GetDriver().close(); //closing child window
                SetupClass.GetDriver().switchTo().window(parentWindow); //cntrl to parent window
                Logging.Log_debug(log, "'Twitter Logo' button works fine");
            }
        }
    }
}
