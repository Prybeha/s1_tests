package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Field;
import SupportClasses.Logging.Logging;
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

public class test_LicensePlatformPage extends SetupClass {
    private CheckLink check_link = new CheckLink();
    private Field field = new Field();
    private static final Logger log = Logger.getLogger(test_MainPage.class);

    @Title("Unit tests for License Platform page: check title")
    @Severity(SeverityLevel.MINOR)
    @Features({"License Platform page","Title"})
    @Description("Check title on the 'License Platform' page")
    @Test
    public void LicensePlatformPage_Title(){
        PagesURL.LicensePlatformPage();

        if(!SetupClass.GetDriver().getTitle().equals("BrokerOne | SeriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Title("Unit tests for License Platform page: check header button links")
    @Severity(SeverityLevel.MINOR)
    @Features({"License Platform page","Header Buttons"})
    @Description("Check header button links on the 'License Platform' page")
    @Test
    public void LicensePlatformPage_HeaderButtons() throws Exception{
        PagesURL.LicensePlatformPage();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        HeaderButtonsCheck.HeaderLinks("/html/body/div[1]/header/div[2]/div/ul/li[", log, methodName);
    }

    @Title("Unit tests for License Platform page: 'Sign Up' and 'Login' buttons")
    @Severity(SeverityLevel.MINOR)
    @Features({"License Platform page","'Sign Up' and 'Login' buttons"})
    @Description("Check 'Sign Up' and 'Login' buttons on the 'License Platform' page")
    @Test
    public void LicensePlatformPage_SignUpAndLogin(){
        PagesURL.LicensePlatformPage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/div[1]/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register", log);

        PagesURL.LicensePlatformPage();

        check_link.CheckLinkURL("Login",
                "/html/body/div[1]/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login", log);
    }

    @Title("Unit tests for License Platform page: main content on the page")
    @Severity(SeverityLevel.MINOR)
    @Features({"License Platform page","Content"})
    @Description("Check main content on the 'License Platform' page")
    @Test
    public void LicensePlatformPage_Body() throws Exception{
        PagesURL.LicensePlatformPage();

        Thread.sleep(1000);
        check_link.CheckLinkURL("Learn more",
                "//a[@data-amp-attribute='[license][latest][link]']",
                "https://secure-seriesone.dynamo-ny.com/deals", log);

        PagesURL.LicensePlatformPage();

        if(!field.ExistElementOnThePage("//input[@id='start_raise_firstName']",1) ||
                !field.ExistElementOnThePage("//input[@id='start_raise_lastName']",1) ||
                !field.ExistElementOnThePage("//input[@id='start_raise_email']",1) ||
                !field.ExistElementOnThePage("//textarea[@id='start_raise_description']",1) ||
                !field.ExistElementOnThePage("//button[@id='start_raise_submit']",1)){
            Logging.Log_error(log, "One of the Contact Us fields do not displayed");
            throw new NewAssertError("One of the Contact Us fields do not displayed");
        }
        Logging.Log_debug(log, "All Contact Us fields displays");
    }

    @Title("Unit tests for License Platform page: check social media links")
    @Severity(SeverityLevel.MINOR)
    @Features({"License Platform page","Social Media Icons"})
    @Description("Check social media links on the 'License Platform' page")
    @Test
    public void LicensePlatformPage_SocialMediaIcons() throws Exception{
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        SocialMediaIcons.LinksCheck(methodName, log);
    }

    @Title("Unit tests for License Platform page: check footer links")
    @Severity(SeverityLevel.MINOR)
    @Features({"License Platform page","Footer links"})
    @Description("Check footer links on the 'License Platform' page")
    @Test
    public void LicensePlatformPage_FooterLinks() throws Exception{
        FooterLinks links = new FooterLinks();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        links.LinksCheck(methodName,
                "/html/body/div[2]/div[3]/footer/div[1]/div[2]/div[",
                "/html/body/div[2]/div[3]/footer/div[2]/div/div/a[", log);
    }
}
