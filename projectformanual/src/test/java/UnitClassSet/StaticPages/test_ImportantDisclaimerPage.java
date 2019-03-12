package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.SetupClass.SetupClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class test_ImportantDisclaimerPage extends SetupClass{
    private CheckLink check_link = new CheckLink();
    private static final Logger log = Logger.getLogger(test_MainPage.class);

    @Title("Unit tests for Important Disclaimer page: check title")
    @Severity(SeverityLevel.MINOR)
    @Features({"Important Disclaimer page","Title"})
    @Description("Check title on the 'Important Disclaimer' page")
    @Test
    public void ImportantDisclaimerPage_Title(){
        PagesURL.ImportantDisclaimerPage();

        //  "seriesOne" word - make first char uppercase
        if(!SetupClass.GetDriver().getTitle().equals("Important Disclaimer | seriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Title("Unit tests for Important Disclaimer page: check header button links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Important Disclaimer page","Header Buttons"})
    @Description("Check header button links on the 'Important Disclaimer' page")
    @Test
    public void ImportantDisclaimerPage_HeaderButtons() throws Exception{
        PagesURL.ImportantDisclaimerPage();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        HeaderButtonsCheck.HeaderLinks("/html/body/header/div[2]/div/ul/li[", log, methodName);
    }

    @Title("Unit tests for Important Disclaimer page: 'Sign Up' and 'Login' buttons")
    @Severity(SeverityLevel.MINOR)
    @Features({"Important Disclaimer page","'Sign Up' and 'Login' buttons"})
    @Description("Check 'Sign Up' and 'Login' buttons on the 'Important Disclaimer' page")
    @Test
    public void ImportantDisclaimerPage_SignUpAndLogin(){
        PagesURL.ImportantDisclaimerPage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register", log);

        PagesURL.ImportantDisclaimerPage();

        check_link.CheckLinkURL("Login",
                "/html/body/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login", log);
    }

    @Title("Unit tests for Important Disclaimer page: check social media links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Important Disclaimer page","Social Media Icons"})
    @Description("Check social media links on the 'Important Disclaimer' page")
    @Test
    public void ImportantDisclaimerPage_SocialMediaIcons() throws Exception{
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        SocialMediaIcons.LinksCheck(methodName, log);
    }

    @Title("Unit tests for Important Disclaimer page: check footer links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Important Disclaimer page","Footer links"})
    @Description("Check footer links on the 'Important Disclaimer' page")
    @Test
    public void ImportantDisclaimerPage_FooterLinks() throws Exception{
        FooterLinks links = new FooterLinks();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        links.LinksCheck(methodName,
                "/html/body/div/footer/div[1]/div[2]/div[",
                "/html/body/div/footer/div[2]/div/div/a[", log);
    }
}
