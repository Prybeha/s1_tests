package UnitClassSet.Login;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Field;
import SupportClasses.SetupClass.SetupClass;
import UnitClassSet.Maintenance.Maintenance;
import UnitClassSet.StaticPages.PagesURL;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class test_Login extends SetupClass {
    private Field field = new Field();

    @Title("Test Login process")
    @Severity(SeverityLevel.NORMAL)
    @Description("Go to main page > click Login button > enter email and pass > check if user Login")
    @Test
    public void Login() throws Exception{
        PagesURL.LoginPage();

        Maintenance maintenance = new Maintenance();
        maintenance.MaintenancePageCheck();

        Login l = new Login();
        l.LoginTest("prybehav+155@gmail.com");

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='navbar-logo']")));
        Thread.sleep(500);
        if (!field.ExistElementOnThePage("//a[@href='https://secure-seriesone.dynamo-ny.com/logout'][text()='Logout']",2)){
            throw new NewAssertError("Logout button does not appears after login");
        }
    }
}
