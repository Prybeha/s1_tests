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

import java.util.concurrent.TimeUnit;

public class test_RaiseFundsPage extends SetupClass{
    private CheckLink check_link = new CheckLink();
    private Field field = new Field();
    private static final Logger log = Logger.getLogger(test_MainPage.class);

    @Title("Unit tests for Raise Funds page: check title")
    @Severity(SeverityLevel.MINOR)
    @Features({"Raise Funds page","Title"})
    @Description("Check title on the 'Raise Funds' page")
    @Test
    public void RaiseFundsPage_Title(){
        PagesURL.RaiseFundsPage();

        if(!SetupClass.GetDriver().getTitle().equals("Raise funds, showcase your company to inspire investors | seriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Title("Unit tests for Raise Funds page: check header button links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Raise Funds page","Header Buttons"})
    @Description("Check header button links on the 'Raise Funds' page")
    @Test
    public void RaiseFundsPage_HeaderButtons() throws Exception{
        PagesURL.RaiseFundsPage();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        HeaderButtonsCheck.HeaderLinks("/html/body/div[1]/header/div[2]/div/ul/li[", log, methodName);
    }

    @Title("Unit tests for Raise Funds page: 'Sign Up' and 'Login' buttons")
    @Severity(SeverityLevel.MINOR)
    @Features({"Raise Funds page","'Sign Up' and 'Login' buttons"})
    @Description("Check 'Sign Up' and 'Login' buttons on the 'Raise Funds' page")
    @Test
    public void RaiseFundsPage_SignUpAndLogin(){
        PagesURL.RaiseFundsPage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/div[1]/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register", log);

        PagesURL.RaiseFundsPage();

        check_link.CheckLinkURL("Login",
                "/html/body/div[1]/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login", log);
    }

    @Title("Unit tests for Raise Funds page: main content on the page")
    @Severity(SeverityLevel.MINOR)
    @Features({"Raise Funds page","Content"})
    @Description("Check main content on the 'Raise Funds' page")
    @Test
    public void RaiseFundsPage_Body() throws Exception{
        PagesURL.RaiseFundsPage();

        check_link.CheckLinkURL("Learn more",
                "//a[@data-amp-attribute='[raise][latest][link]']",
                "https://secure-seriesone.dynamo-ny.com/deals", log);

        PagesURL.RaiseFundsPage();

        Thread.sleep(1000);
        check_link.CheckLinkURL("Contact Us to Start",
                "//a[@class='fw-link']",
                "https://seriesone.dynamo-ny.com/start-raising-funds/#start-raise-container", log);

        PagesURL.RaiseFundsPage();

        // count how much faq questions published on the page
        int quantity = 0;
        for (int i = 0; i < 100; i++) {
            if (field.ExistElementOnThePage("//div[@data-amp-html='[raise][faq][" + Integer.toString(i) + "][text]']", 1)) {
                quantity++;
            } else {
                break;
            }
        }

        for (int i = 0; i < quantity; i++) {
            SetupClass.GetDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            if (!SetupClass.GetDriver().findElement(By.xpath("//div[@data-amp-html='[raise][faq][" + Integer.toString(i) + "][text]']")).isDisplayed()) {
                SetupClass.GetDriver().findElement(By.xpath("//a[@data-amp-html='[raise][faq][" + Integer.toString(i) + "][title]']")).click();
                Thread.sleep(500);
                if (!field.ExistElementOnThePage("//div[@data-amp-html='[raise][faq][" + Integer.toString(i) + "][text]']", 1)) {
                    Logging.Log_error(log, "FAQ text does not appears");
                    throw new NewAssertError("FAQ text does not appears");
                }
            }
            Logging.Log_debug(log, "FAQ text works good");
            SetupClass.GetDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        check_link.CheckLinkURL("FAQs",
                "//a[text()='FAQs']",
                "https://seriesone.dynamo-ny.com/faqs-for-business-owners/", log);

        PagesURL.RaiseFundsPage();

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

    @Title("Unit tests for Raise Funds page: check social media links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Raise Funds page","Social Media Icons"})
    @Description("Check social media links on the 'Raise Funds' page")
    @Test
    public void RaiseFundsPage_SocialMediaIcons() throws Exception{
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        SocialMediaIcons.LinksCheck(methodName, log);
    }

    @Title("Unit tests for Raise Funds page: check footer links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Raise Funds page","Footer links"})
    @Description("Check footer links on the 'Raise Funds' page")
    @Test
    public void RaiseFundsPage_FooterLinks() throws Exception{
        FooterLinks links = new FooterLinks();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        links.LinksCheck(methodName,
                "/html/body/div[2]/div[2]/footer/div[1]/div[2]/div[",
                "/html/body/div[2]/div[2]/footer/div[2]/div/div/a[", log);
    }
}
