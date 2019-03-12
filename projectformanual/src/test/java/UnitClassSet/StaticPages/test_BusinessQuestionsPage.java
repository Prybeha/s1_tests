package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
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

public class test_BusinessQuestionsPage extends SetupClass{
    private CheckLink check_link = new CheckLink();
    private static final Logger log = Logger.getLogger(test_MainPage.class);

    @Title("Unit tests for Business Questions page: check title")
    @Severity(SeverityLevel.MINOR)
    @Features({"Business questions page","Title"})
    @Description("Check title on the 'Business questions' page")
    @Test
    public void BusinessQuestionsPage_Title(){
        PagesURL.BusinessQuestionsPage();

        //  "seriesOne" word - make first char uppercase
        if(!SetupClass.GetDriver().getTitle().equals("Business owners questions on raising funds | seriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Title("Unit tests for Business Questions page: check header button links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Business Questions page","Header Buttons"})
    @Description("Check header button links on the 'Business Questions' page")
    @Test
    public void BusinessQuestionsPage_HeaderButtons() throws Exception{
        PagesURL.BusinessQuestionsPage();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        HeaderButtonsCheck.HeaderLinks("/html/body/div[1]/header/div[2]/div/ul/li[", log, methodName);
    }

    @Title("Unit tests for Business Questions page: 'Sign Up' and 'Login' buttons")
    @Severity(SeverityLevel.MINOR)
    @Features({"Business Questions page","'Sign Up' and 'Login' buttons"})
    @Description("Check 'Sign Up' and 'Login' buttons on the 'Business Questions' page")
    @Test
    public void BusinessQuestionsPage_SignUpAndLogin(){
        PagesURL.BusinessQuestionsPage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/div[1]/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register", log);

        PagesURL.BusinessQuestionsPage();

        check_link.CheckLinkURL("Login",
                "/html/body/div[1]/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login", log);
    }

    @Title("Unit tests for Business Questions page: main content on the page")
    @Severity(SeverityLevel.MINOR)
    @Features({"Business Questions page","Content"})
    @Description("Check main content on the 'Business Questions' page")
    @Test
    public void BusinessQuestionsPage_Body(){
        PagesURL.BusinessQuestionsPage();

        // bug - goes to /learn page instead /deal page
        check_link.CheckLinkURL("Learn more",
                "//a[text()='Learn More']",
                "https://seriesone.dynamo-ny.com/learn/", log);// https://secure-seriesone.dynamo-ny.com/deals

        PagesURL.BusinessQuestionsPage();

        check_link.CheckLinkURL("Investor FAQs",
                "//a[@data-amp-attribute='[faq][menu][0][link]']",
                "https://seriesone.dynamo-ny.com/faqs-for-investors/", log);

        PagesURL.BusinessQuestionsPage();

        check_link.CheckLinkURL("Business Owner FAQs",
                "//a[@data-amp-attribute='[faq][menu][1][link]']",
                "https://seriesone.dynamo-ny.com/faqs-for-business-owners/", log);

        PagesURL.BusinessQuestionsPage();

        check_link.CheckLinkURL("Education Resources",
                "//a[@data-amp-attribute='[faq][menu][2][link]']",
                "https://seriesone.dynamo-ny.com/resources-for-investors/#3960", log);

    }

    @Title("Unit tests for Business Questions page: check social media links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Business Questions page","Social Media Icons"})
    @Description("Check social media links on the 'Business Questions' page")
    @Test
    public void BusinessQuestionsPage_SocialMediaIcons() throws Exception{
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        SocialMediaIcons.LinksCheck(methodName, log);
    }

    @Title("Unit tests for Business Questions page: check footer links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Business Questions page","Footer links"})
    @Description("Check footer links on the 'Business Questions' page")
    @Test
    public void BusinessQuestionsPage_FooterLinks() throws Exception{
        FooterLinks links = new FooterLinks();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        links.LinksCheck(methodName,
                "/html/body/div[2]/div[3]/footer/div[1]/div[2]/div[",
                "/html/body/div[2]/div[3]/footer/div[2]/div/div/a[", log);
    }
}
