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

public class test_TermsOfUsePage extends SetupClass {
    private CheckLink check_link = new CheckLink();
    private static final Logger log = Logger.getLogger(test_MainPage.class);

    @Title("Unit tests for Terms Of Use page: check title")
    @Severity(SeverityLevel.MINOR)
    @Features({"Terms Of Use page","Title"})
    @Description("Check title on the 'Terms Of Use' page")
    @Test
    public void TermsOfUsePage_Title(){
        PagesURL.TermsOfUsePage();

        //  "seriesOne" word - make first char uppercase
        if(!SetupClass.GetDriver().getTitle().equals("Terms of Use | seriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Title("Unit tests for Terms Of Use page: check header button links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Terms Of Use page","Header Buttons"})
    @Description("Check header button links on the 'Terms Of Use' page")
    @Test
    public void TermsOfUsePage_HeaderButtons() throws Exception{
        PagesURL.TermsOfUsePage();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        HeaderButtonsCheck.HeaderLinks("/html/body/header/div[2]/div/ul/li[", log, methodName);
    }

    @Title("Unit tests for Terms Of Use page: 'Sign Up' and 'Login' buttons")
    @Severity(SeverityLevel.MINOR)
    @Features({"Terms Of Use page","'Sign Up' and 'Login' buttons"})
    @Description("Check 'Sign Up' and 'Login' buttons on the 'Terms Of Use' page")
    @Test
    public void TermsOfUsePage_SignUpAndLogin() throws Exception {
        PagesURL.TermsOfUsePage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register", log);

        PagesURL.TermsOfUsePage();

        check_link.CheckLinkURL("Login",
                "/html/body/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login", log);

        PagesURL.TermsOfUsePage();
        Thread.sleep(1000);

        check_link.CheckLinkURL("Learn more",
                "//a[text()='Learn More']",
                "https://secure-seriesone.dynamo-ny.com/deals", log);
    }

    @Title("Unit tests for Terms Of Use page: check social media links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Terms Of Use page","Social Media Icons"})
    @Description("Check social media links on the 'Terms Of Use' page")
    @Test
    public void TermsOfUsePage_SocialMediaIcons() throws Exception{
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        SocialMediaIcons.LinksCheck(methodName, log);
    }

    @Title("Unit tests for Terms Of Use page: check footer links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Terms Of Use page","Footer links"})
    @Description("Check footer links on the 'Terms Of Use' page")
    @Test
    public void TermsOfUsePage_FooterLinks() throws Exception{
        FooterLinks links = new FooterLinks();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        links.LinksCheck(methodName,
                "/html/body/div/footer/div[1]/div[2]/div[",
                "/html/body/div/footer/div[2]/div/div/a[", log);
    }
}
