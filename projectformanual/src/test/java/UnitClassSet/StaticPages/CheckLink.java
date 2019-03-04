package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.SetupClass.SetupClass;
import org.openqa.selenium.By;

public class CheckLink {
    public void CheckLinkURL(String button_name ,String locator, String expected_url){
        SetupClass.GetDriver().findElement(By.xpath(locator)).click();
        if (!SetupClass.GetDriver().getCurrentUrl().equals(expected_url)){
            throw new NewAssertError("'" + button_name + "' button has been broken");
        }
    }
}
