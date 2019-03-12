package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Logging.Logging;
import SupportClasses.SetupClass.SetupClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class test_AboutUsPage extends SetupClass {
    private CheckLink check_link = new CheckLink();
    private static final Logger log = Logger.getLogger(test_MainPage.class);

    @Title("Unit tests for About Us page: check title")
    @Severity(SeverityLevel.MINOR)
    @Features({"About Us page","Title"})
    @Description("Check title on the 'About Us' page")
    @Test
    public void AboutUsPage_Title(){
        PagesURL.AboutUsPage();

        if(!SetupClass.GetDriver().getTitle().equals("Crowdfunding portal - Financing Unchained | seriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Title("Unit tests for About Us page: check header button links")
    @Severity(SeverityLevel.MINOR)
    @Features({"About Us page","Header Buttons"})
    @Description("Check header button links on the 'About Us' page")
    @Test
    public void AboutUsPage_HeaderButtons() throws Exception{
        PagesURL.AboutUsPage();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        HeaderButtonsCheck.HeaderLinks("/html/body/header/div[2]/div/ul/li[", log, methodName);
    }

    @Title("Unit tests for About Us page: 'Sign Up' and 'Login' buttons")
    @Severity(SeverityLevel.MINOR)
    @Features({"About Us page","'Sign Up' and 'Login' buttons"})
    @Description("Check 'Sign Up' and 'Login' buttons on the 'About Us' page")
    @Test
    public void AboutUsPage_SignUpAndLogin() throws Exception{
        PagesURL.AboutUsPage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register", log);

        PagesURL.AboutUsPage();

        check_link.CheckLinkURL("Login",
                "/html/body/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login", log);

        PagesURL.AboutUsPage();

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Learn More']")));
        Thread.sleep(1000);
        check_link.CheckLinkURL("Learn More",
                "//a[text()='Learn More']",
                "https://secure-seriesone.dynamo-ny.com/deals", log);
    }

    @Title("Unit tests for About Us page: check social media links")
    @Severity(SeverityLevel.MINOR)
    @Features({"About Us page","Social Media Icons"})
    @Description("Check social media links on the 'About Us' page")
    @Test
    public void AboutUsPage_SocialMediaIcons() throws Exception{
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        SocialMediaIcons.LinksCheck(methodName, log);
    }

    @Title("Unit tests for About Us page: check footer links")
    @Severity(SeverityLevel.MINOR)
    @Features({"About Us page","Footer links"})
    @Description("Check footer links on the 'About Us' page")
    @Test
    public void AboutUsPage_FooterLinks() throws Exception{
        FooterLinks links = new FooterLinks();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        links.LinksCheck(methodName,
                "/html/body/div/footer/div[1]/div[2]/div[",
                "/html/body/div/footer/div[2]/div/div/a[", log);
    }
}
