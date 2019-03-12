package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Logging.Logging;
import SupportClasses.SetupClass.SetupClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class test_MainPage extends SetupClass{
    private CheckLink check_link = new CheckLink();
        private static final Logger log = Logger.getLogger(test_MainPage.class);

    @Title("Unit tests for Main page: check title")
    @Severity(SeverityLevel.MINOR)
    @Features({"Main page","Title"})
    @Description("Check title on the 'Main' page")
    @Test
    public void MainPage_Title(){
        PagesURL.MainPage();

        if(!SetupClass.GetDriver().getTitle().equals("Token offerings, debt and equity investment platform | seriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Title("Unit tests for Main page: check header and footer 'SeriesOne' logo links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Main page","header and footer 'SeriesOne' logo links"})
    @Description("Check header and footer 'SeriesOne' logo links on the 'Main' page")
    @Test
    public void MainPage_HeaderAndFooterSeriesOneLogoButton(){
        PagesURL.MainPage();

        check_link.CheckLinkURL("navbar SeriesOne Logo",
                "//img[@class='navbar-logo']",
                "https://seriesone.dynamo-ny.com/", log);

        PagesURL.MainPage();

        check_link.CheckLinkURL("footer SeriesOne Logo",
                "//img[@class='footer-logo']",
                "https://seriesone.dynamo-ny.com/", log);
    }

    @Title("Unit tests for Main page: check header button links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Main page","Header Buttons"})
    @Description("Check header button links on the 'Main' page")
    @Test
    public void MainPage_HeaderButtons() throws Exception{
        PagesURL.MainPage();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        HeaderButtonsCheck.HeaderLinks("/html/body/div/div[1]/header/div[2]/div/ul/li[", log, methodName);
    }

    @Title("Unit tests for Main page: 'Sign Up' and 'Login' buttons")
    @Severity(SeverityLevel.MINOR)
    @Features({"Main page","'Sign Up' and 'Login' buttons"})
    @Description("Check 'Sign Up' and 'Login' buttons on the 'Main' page")
    @Test
    public void MainPage_SignUpAndLogin(){
        PagesURL.MainPage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/div/div[1]/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register", log);

        PagesURL.MainPage();

        check_link.CheckLinkURL("Login",
                "/html/body/div/div[1]/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login", log);
    }

    @Title("Unit tests for Main page: main content on the page")
    @Severity(SeverityLevel.MINOR)
    @Features({"Main page","Content"})
    @Description("Check main content on the 'Main' page")
    @Test
    public void MainPage_Body(){
        PagesURL.MainPage();

        check_link.CheckLinkURL("Browse deals",
                "/html/body/div/div[2]/div[2]/section[1]/div/div/div[1]/div[1]/div/div[3]/figure/figcaption/div[2]/a",
                "https://secure-seriesone.dynamo-ny.com/deals", log);

        PagesURL.MainPage();

        check_link.CheckLinkURL("Browse deals",
                "/html/body/div/div[2]/div[2]/div/section[1]/div[2]/div[2]/a",
                "https://secure-seriesone.dynamo-ny.com/deals", log);

        PagesURL.MainPage();

        check_link.CheckLinkURL("Sign Up",
                "/html/body/div/div[2]/div[2]/div/div/div/section[1]/div/div/div[2]/a/button",
                "https://secure-seriesone.dynamo-ny.com/register", log);

        PagesURL.MainPage();

        check_link.CheckLinkURL("Start Raising Funds",
                "/html/body/div/div[2]/div[2]/div/div/div/div/section[2]/div/div/div/div[1]/div/a/button",
                "https://seriesone.dynamo-ny.com/start-raising-funds/", log);

        PagesURL.MainPage();

        check_link.CheckLinkURL("Start Investing",
                "/html/body/div/div[2]/div[2]/div/div/div/div/section[2]/div/div/div/div[3]/div/a/button",
                "https://secure-seriesone.dynamo-ny.com/register", log);
    }

    @Title("Unit tests for Main page: check social media links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Main page","Social Media Icons"})
    @Description("Check social media links on the 'Main' page")
    @Test
    public void MainPage_SocialMediaIcons() throws Exception{
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        SocialMediaIcons.LinksCheck(methodName, log);
    }

    @Title("Unit tests for Main page: check footer links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Main page","Footer links"})
    @Description("Check footer links on the 'Main' page")
    @Test
    public void MainPage_FooterLinks() throws Exception{
        FooterLinks links = new FooterLinks();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        links.LinksCheck(methodName,
                "/html/body/div/div[2]/div[3]/footer/div[1]/div[2]/div[",
                "/html/body/div/div[2]/div[3]/footer/div[2]/div/div/a[", log);
    }
}
