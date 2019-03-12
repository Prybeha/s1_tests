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

public class test_PrivacyPolicyPage extends SetupClass {
    private CheckLink check_link = new CheckLink();
    private static final Logger log = Logger.getLogger(test_MainPage.class);

    @Title("Unit tests for Privacy Policy page: check title")
    @Severity(SeverityLevel.MINOR)
    @Features({"Privacy Policy page","Title"})
    @Description("Check title on the 'Privacy Policy' page")
    @Test
    public void PrivacyPolicyPage_Title(){
        PagesURL.PrivacyPolicyPage();

        //  "seriesOne" word - make first char uppercase
        if(!SetupClass.GetDriver().getTitle().equals("Privacy Policy | seriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Title("Unit tests for Privacy Policy page: check header button links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Privacy Policy page","Header Buttons"})
    @Description("Check header button links on the 'Privacy Policy' page")
    @Test
    public void PrivacyPolicyPage_HeaderButtons() throws Exception{
        PagesURL.PrivacyPolicyPage();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        HeaderButtonsCheck.HeaderLinks("/html/body/header/div[2]/div/ul/li[", log, methodName);
    }

    @Title("Unit tests for Privacy Policy page: 'Sign Up' and 'Login' buttons")
    @Severity(SeverityLevel.MINOR)
    @Features({"Privacy Policy page","'Sign Up' and 'Login' buttons"})
    @Description("Check 'Sign Up' and 'Login' buttons on the 'Privacy Policy' page")
    @Test
    public void PrivacyPolicyPage_SignUpAndLogin() throws Exception {
        PagesURL.PrivacyPolicyPage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register", log);

        PagesURL.PrivacyPolicyPage();

        check_link.CheckLinkURL("Login",
                "/html/body/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login", log);

        PagesURL.PrivacyPolicyPage();
        Thread.sleep(1000);

        check_link.CheckLinkURL("Learn more",
                "//a[text()='Learn More']",
                "https://secure-seriesone.dynamo-ny.com/deals", log);
    }

    @Title("Unit tests for Privacy Policy page: check social media links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Privacy Policy page","Social Media Icons"})
    @Description("Check social media links on the 'Privacy Policy' page")
    @Test
    public void PrivacyPolicyPage_SocialMediaIcons() throws Exception{
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        SocialMediaIcons.LinksCheck(methodName, log);
    }

    @Title("Unit tests for Privacy Policy page: check footer links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Privacy Policy page","Footer links"})
    @Description("Check footer links on the 'Privacy Policy' page")
    @Test
    public void PrivacyPolicyPage_FooterLinks() throws Exception{
        FooterLinks links = new FooterLinks();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        links.LinksCheck(methodName,
                "/html/body/div/footer/div[1]/div[2]/div[",
                "/html/body/div/footer/div[2]/div/div/a[", log);
    }
}
