package UnitClassSet.Login;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Field;
import SupportClasses.SetupClass.SetupClass;
import UnitClassSet.StaticPages.PagesURL;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class test_Login extends SetupClass {
    private Field field = new Field();

    @Test
    public void Login() throws Exception{
        PagesURL.LoginPage();

        Login l = new Login();
        l.LoginTest("prybehav+155@gmail.com");

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='navbar-logo']")));
        Thread.sleep(500);
        if (!field.ExistElementOnThePage("//a[@href='https://secure-seriesone.dynamo-ny.com/logout'][text()='Logout']",2)){
            throw new NewAssertError("Logout button does not appears after login");
        }
    }
}
