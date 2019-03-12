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

public class test_DigitalSecurityOfferingsPage extends SetupClass {
    private CheckLink check_link = new CheckLink();
    private Field field = new Field();
    private static final Logger log = Logger.getLogger(test_MainPage.class);

    @Title("Unit tests for Digital Security Offerings page: check title")
    @Severity(SeverityLevel.MINOR)
    @Features({"Digital Security Offerings page","Title"})
    @Description("Check title on the 'Digital Security Offerings' page")
    @Test
    public void DigitalSecurityOfferingsPage_Title(){
        PagesURL.DigitalSecurityOfferingsPage();

        // maybe title must be with "|" instead "-"
        if(!SetupClass.GetDriver().getTitle().equals("Digital Security Offerings - seriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Title("Unit tests for Digital Security Offerings page: check header button links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Digital Security Offerings page","Header Buttons"})
    @Description("Check header button links on the 'Digital Security Offerings' page")
    @Test
    public void DigitalSecurityOfferingsPage_HeaderButtons() throws Exception{
        PagesURL.DigitalSecurityOfferingsPage();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        HeaderButtonsCheck.HeaderLinks("/html/body/div[1]/header/div[2]/div/ul/li[", log, methodName);
    }

    @Title("Unit tests for Digital Security Offerings page: 'Sign Up' and 'Login' buttons")
    @Severity(SeverityLevel.MINOR)
    @Features({"Digital Security Offerings page","'Sign Up' and 'Login' buttons"})
    @Description("Check 'Sign Up' and 'Login' buttons on the 'Digital Security Offerings' page")
    @Test
    public void DigitalSecurityOfferingsPage_SignUpAndLogin(){
        PagesURL.DigitalSecurityOfferingsPage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/div[1]/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register", log);

        PagesURL.DigitalSecurityOfferingsPage();

        check_link.CheckLinkURL("Login",
                "/html/body/div[1]/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login", log);
    }

    @Title("Unit tests for Digital Security Offerings page: main content on the page")
    @Severity(SeverityLevel.MINOR)
    @Features({"Digital Security Offerings page","Content"})
    @Description("Check main content on the 'Digital Security Offerings' page")
    @Test
    public void DigitalSecurityOfferingsPage_Body() throws Exception{
        PagesURL.DigitalSecurityOfferingsPage();

        check_link.CheckLinkURL("Learn more",
                "//a[@data-amp-attribute='[token][latest][link]']",
                "https://secure-seriesone.dynamo-ny.com/deals", log);

        PagesURL.DigitalSecurityOfferingsPage();

        check_link.CheckLinkURL("Browse Deals",
                "//a[@class='fw-link']",
                "https://secure-seriesone.dynamo-ny.com/deals", log);

        PagesURL.DigitalSecurityOfferingsPage();

        // count how much faq questions published on the page
        int quantity = 0;
        for (int i = 0; i < 100; i++) {
            if (field.ExistElementOnThePage("//div[@data-amp-html='[token][faq][" + Integer.toString(i) + "][text]']", 1)) {
                quantity++;
            } else {
                break;
            }
        }

        for (int i = 0; i < quantity; i++) {
            SetupClass.GetDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            if (!SetupClass.GetDriver().findElement(By.xpath("//div[@data-amp-html='[token][faq][" + Integer.toString(i) + "][text]']")).isDisplayed()) {
                SetupClass.GetDriver().findElement(By.xpath("//a[@data-amp-html='[token][faq][" + Integer.toString(i) + "][title]']")).click();
                Thread.sleep(500);
                if (!field.ExistElementOnThePage("//div[@data-amp-html='[token][faq][" + Integer.toString(i) + "][text]']", 1)) {
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
    }

    @Title("Unit tests for Digital Security Offerings page: check social media links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Digital Security Offerings page","Social Media Icons"})
    @Description("Check social media links on the 'Digital Security Offerings' page")
    @Test
    public void DigitalSecurityOfferingsPage_SocialMediaIcons() throws Exception{
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        SocialMediaIcons.LinksCheck(methodName, log);
    }

    @Title("Unit tests for Digital Security Offerings page: check footer links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Digital Security Offerings page","Footer links"})
    @Description("Check footer links on the 'Digital Security Offerings' page")
    @Test
    public void DigitalSecurityOfferingsPage_FooterLinks() throws Exception{
        FooterLinks links = new FooterLinks();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        links.LinksCheck(methodName,
                "/html/body/div[2]/div[3]/footer/div[1]/div[2]/div[",
                "/html/body/div[2]/div[3]/footer/div[2]/div/div/a[", log);
    }
}
