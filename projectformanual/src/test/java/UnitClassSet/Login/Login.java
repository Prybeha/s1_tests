package UnitClassSet.Login;

import SupportClasses.SetupClass.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Login {

    public void LoginTest(String username){
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("_username")));
        SetupClass.GetDriver().findElement(By.id("_username")).clear();
        SetupClass.GetDriver().findElement(By.id("_username")).sendKeys(username);
        SetupClass.GetDriver().findElement(By.id("_password")).clear();
        SetupClass.GetDriver().findElement(By.id("_password")).sendKeys("Aq1sw2de3");
        SetupClass.GetDriver().findElement(By.id("submit")).click();
    }
}
