package SupportClasses;

import SupportClasses.Exceptions.NewException;
import SupportClasses.SetupClass.SetupClass;
import org.openqa.selenium.By;

import static org.testng.Assert.fail;

public class WaitingForElement {

    // Using in rarely situations
    public void WaitingForElementForSeconds(int seconds_for_wait, String error_message) throws Exception{
        for (int second = 0; second < seconds_for_wait ; second++) {
            try {
                if (SetupClass.GetDriver().findElement(By.id("name")).isDisplayed()) break;
            }
            catch (Exception e) {}
            Thread.sleep(1000);
        }
    }
}
