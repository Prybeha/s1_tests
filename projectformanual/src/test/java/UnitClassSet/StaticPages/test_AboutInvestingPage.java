package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Field;
import SupportClasses.Logging.Logging;
import SupportClasses.SetupClass.SetupClass;
import UnitClassSet.Maintenance.Maintenance;
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

import java.util.concurrent.TimeUnit;

public class test_AboutInvestingPage extends SetupClass {
    private Field field = new Field();
    private CheckLink check_link = new CheckLink();
    private static final Logger log = Logger.getLogger(test_AboutInvestingPage.class);

    @Title("Unit tests for About Investing page: check title")
    @Severity(SeverityLevel.MINOR)
    @Features({"About Investing page","Title"})
    @Description("Check title on the 'About investing' page")
    @Test
    public void AboutInvestingPage_Title(){
        PagesURL.AboutInvestingPage();

        if(!SetupClass.GetDriver().getTitle().equals("Investment questions and investor learning resources | seriesOne")){
           throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Title("Unit tests for About Investing page: check header button links")
    @Severity(SeverityLevel.MINOR)
    @Features({"About Investing page","Header Buttons"})
    @Description("Check header button links on the 'About investing' page")
    @Test
    public void AboutInvestingPage_HeaderButtons() throws Exception{
        PagesURL.AboutInvestingPage();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        HeaderButtonsCheck.HeaderLinks("/html/body/div[1]/header/div[2]/div/ul/li[", log, methodName);
    }

    @Title("Unit tests for About Investing page: 'Sign Up' and 'Login' buttons")
    @Severity(SeverityLevel.MINOR)
    @Features({"About Investing page","'Sign Up' and 'Login' buttons"})
    @Description("Check 'Sign Up' and 'Login' buttons on the 'About investing' page")
    @Test
    public void AboutInvestingPage_SignUpAndLogin(){
        PagesURL.AboutInvestingPage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/div[1]/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register", log);

        PagesURL.AboutInvestingPage();

        check_link.CheckLinkURL("Login",
                "/html/body/div[1]/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login", log);
    }

    @Title("Unit tests for About Investing page: main content on the page")
    @Severity(SeverityLevel.MINOR)
    @Features({"About Investing page","Content"})
    @Description("Check main content on the 'About Investing' page")
    @Test
    public void AboutInvestingPage_Body() throws Exception{
        PagesURL.AboutInvestingPage();

        check_link.CheckLinkURL("Learn more",
                "//a[@data-amp-attribute='[learn][latest][link]']",
                "https://secure-seriesone.dynamo-ny.com/deals", log);

        PagesURL.AboutInvestingPage();

        check_link.CheckLinkURL("Browse All Deals",
                "//a[@class='fw-link']",
                "https://secure-seriesone.dynamo-ny.com/deals", log);

        PagesURL.AboutInvestingPage();

        // count how much faq questions published on the page
        int quantity = 0;
        for (int i = 0; i < 100; i++) {
            if (field.ExistElementOnThePage("//div[@data-amp-html='[learn][faq][" + Integer.toString(i) + "][text]']", 1)) {
                quantity++;
            } else {
                break;
            }
        }

        for (int i = 0; i < quantity; i++) {
            SetupClass.GetDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            if (!SetupClass.GetDriver().findElement(By.xpath("//div[@data-amp-html='[learn][faq][" + Integer.toString(i) + "][text]']")).isDisplayed()) {
                SetupClass.GetDriver().findElement(By.xpath("//a[@data-amp-html='[learn][faq][" + Integer.toString(i) + "][title]']")).click();
                Thread.sleep(500);
                if (!field.ExistElementOnThePage("//div[@data-amp-html='[learn][faq][" + Integer.toString(i) + "][text]']", 1)) {
                    Logging.Log_error(log, "FAQ text does not appears");
                    throw new NewAssertError("FAQ text does not appears");
                }
            }
            Logging.Log_debug(log, "FAQ text works good");
            SetupClass.GetDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        check_link.CheckLinkURL("FAQs",
                "//a[text()='FAQs']",
                "https://seriesone.dynamo-ny.com/faqs-for-investors/", log);

        check_link.CheckLinkURL("Education Resources",
                "//a[text()='Education Resources']",
                "https://seriesone.dynamo-ny.com/resources-for-investors/#3960", log);
    }

    @Title("Unit tests for About Investing page: check social media links")
    @Severity(SeverityLevel.MINOR)
    @Features({"About Investing page","Social Media Icons"})
    @Description("Check social media links on the 'About Investing' page")
    @Test
    public void AboutInvestingPage_SocialMediaIcons() throws Exception{
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        SocialMediaIcons.LinksCheck(methodName, log);
    }

    @Title("Unit tests for About Investing page: check footer links")
    @Severity(SeverityLevel.MINOR)
    @Features({"About Investing page","Footer links"})
    @Description("Check footer links on the 'About Investing' page")
    @Test
    public void AboutInvestingPage_FooterLinks() throws Exception{
        FooterLinks links = new FooterLinks();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        links.LinksCheck(methodName,
                "/html/body/div[2]/div[3]/footer/div[1]/div[2]/div[",
                "/html/body/div[2]/div[3]/footer/div[2]/div/div/a[", log);
    }
}
