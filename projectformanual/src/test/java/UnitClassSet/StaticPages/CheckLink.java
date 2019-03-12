package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Logging.Logging;
import SupportClasses.SetupClass.SetupClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class CheckLink {
    public void CheckLinkURL(String button_name , String locator, String expected_url, Logger log){
        SetupClass.GetDriver().findElement(By.xpath(locator)).click();
        if (!SetupClass.GetDriver().getCurrentUrl().equals(expected_url)){
            Logging.Log_error(log, "Button " + button_name + " works incorrectly");
            throw new NewAssertError("Button " + button_name + " works incorrectly");
        }
        Logging.Log_debug(log, "Button " + button_name + " works correctly");
    }
}
