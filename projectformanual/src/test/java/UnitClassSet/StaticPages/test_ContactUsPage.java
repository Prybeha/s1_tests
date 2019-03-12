package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Field;
import SupportClasses.Logging.Logging;
import SupportClasses.SetupClass.SetupClass;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class test_ContactUsPage extends SetupClass{
    private CheckLink check_link = new CheckLink();
    private Field field = new Field();
    private static final Logger log = Logger.getLogger(test_MainPage.class);

    @Title("Unit tests for Contact Us page: check title")
    @Severity(SeverityLevel.MINOR)
    @Features({"Contact Us page","Title"})
    @Description("Check title on the 'Contact Us' page")
    @Test
    public void ContactUsPage_Title(){
        PagesURL.ContactUsPage();

        if(!SetupClass.GetDriver().getTitle().equals("Contact Us | seriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Title("Unit tests for Contact Us page: check header button links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Contact Us page","Header Buttons"})
    @Description("Check header button links on the 'Contact Us' page")
    @Test
    public void ContactUsPage_HeaderButtons() throws Exception{
        PagesURL.ContactUsPage();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        HeaderButtonsCheck.HeaderLinks("/html/body/header/div[2]/div/ul/li[", log, methodName);
    }

    @Title("Unit tests for Contact Us page: 'Sign Up' and 'Login' buttons")
    @Severity(SeverityLevel.MINOR)
    @Features({"Contact Us page","'Sign Up' and 'Login' buttons"})
    @Description("Check 'Sign Up' and 'Login' buttons on the 'Contact Us' page")
    @Test
    public void ContactUsPage_SignUpAndLogin(){
        PagesURL.ContactUsPage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register", log);

        PagesURL.ContactUsPage();

        check_link.CheckLinkURL("Login",
                "/html/body/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login", log);
    }

    @Title("Unit tests for Contact Us  page: main content on the page")
    @Severity(SeverityLevel.MINOR)
    @Features({"Contact Us  page","Content"})
    @Description("Check main content on the 'Contact Us ' page")
    @Test
    public void ContactUsPage_Body() throws Exception{
        PagesURL.ContactUsPage();

        if(!field.ExistElementOnThePage("//input[@id='start_raise_firstName']",1) ||
                !field.ExistElementOnThePage("//input[@id='start_raise_lastName']",1) ||
                !field.ExistElementOnThePage("//input[@id='start_raise_email']",1) ||
                !field.ExistElementOnThePage("//textarea[@id='start_raise_description']",1) ||
                !field.ExistElementOnThePage("//button[@id='start_raise_submit']",1)){
            Logging.Log_error(log, "One of the Contact Us fields does not displayed");
            throw new NewAssertError("One of the Contact Us fields does not displayed");
        }
        Logging.Log_debug(log, "All Contact Us fields displays");
    }

    @Title("Unit tests for Contact Us page: check social media links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Contact Us page","Social Media Icons"})
    @Description("Check social media links on the 'Contact Us' page")
    @Test
    public void ContactUsPage_SocialMediaIcons() throws Exception{
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        SocialMediaIcons.LinksCheck(methodName, log);
    }

    @Title("Unit tests for Contact Us page: check footer links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Contact Us page","Footer links"})
    @Description("Check footer links on the 'Contact Us' page")
    @Test
    public void ContactUsPage_FooterLinks() throws Exception{
        FooterLinks links = new FooterLinks();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        links.LinksCheck(methodName,
                "/html/body/div/footer/div[1]/div[2]/div[",
                "/html/body/div/footer/div[2]/div/div/a[", log);
    }
}
