package SupportClasses;

import SupportClasses.SetupClass.SetupClass;
import org.openqa.selenium.By;

import static org.testng.Assert.fail;

public class WaitingForElement {

    // Using in rarely situations
    public void WaitingForElementForTenSeconds() throws Exception{
        for (int second = 0; ; second++) {
            if (second >= 10) {
                fail("timeout");
            }
            try {
                if (SetupClass.GetDriver().findElement(By.id("name")).isDisplayed() ) break;
            }
            catch (Exception e) {}
            Thread.sleep(500);
        }
    }
}
