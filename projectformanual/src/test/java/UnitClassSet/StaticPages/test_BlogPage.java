package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Field;
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

public class test_BlogPage extends SetupClass{
    private Field field = new Field();
    private CheckLink check_link = new CheckLink();
    private static final Logger log = Logger.getLogger(test_MainPage.class);

    @Title("Unit tests for Blog page: check title")
    @Severity(SeverityLevel.MINOR)
    @Features({"Blog page","Title"})
    @Description("Check title on the 'Blog' page")
    @Test
    public void BlogPage_Title(){
        PagesURL.BlogPage();

        if(!SetupClass.GetDriver().getTitle().equals("Blog | seriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Title("Unit tests for Blog page: check header button links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Blog page","Header Buttons"})
    @Description("Check header button links on the 'Blog' page")
    @Test
    public void BlogPage_HeaderButtons() throws Exception{
        PagesURL.BlogPage();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        HeaderButtonsCheck.HeaderLinks("/html/body/div/div[1]/header/div[2]/div/ul/li[", log, methodName);
    }

    @Title("Unit tests for Blog page: 'Sign Up' and 'Login' buttons")
    @Severity(SeverityLevel.MINOR)
    @Features({"Blog page","'Sign Up' and 'Login' buttons"})
    @Description("Check 'Sign Up' and 'Login' buttons on the 'Blog' page")
    @Test
    public void BlogPage_SignUpAndLogin(){
        PagesURL.BlogPage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/div/div[1]/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register", log);

        PagesURL.BlogPage();

        check_link.CheckLinkURL("Login",
                "/html/body/div/div[1]/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login", log);
    }

    @Title("Unit tests for Blog page: main content on the page")
    @Severity(SeverityLevel.MINOR)
    @Features({"Blog page","Content"})
    @Description("Check main content on the 'Blog' page")
    @Test
    public void BlogPage_Body() throws Exception{
        PagesURL.BlogPage();
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[1]/header/div[2]/div/div[1]/a/img")));
        Thread.sleep(1000);

        if (!field.ExistElementOnThePage("//input[@name='blog_search']",2)){
            Logging.Log_error(log, "Search blog filter does not displayed");
            throw new NewAssertError("Search blog filter does not displayed");
        }
        if (!field.ExistElementOnThePage("//select[@name='blog_sort']",5)){
            Logging.Log_error(log, "Sort blog does not displayed");
            throw new NewAssertError("Sort blog does not displayed");
        }

        check_link.CheckLinkURL("Learn more (First blog post)",
                "//*[@id=\"blog-item-container\"]/div[1]/div/div[2]/div[4]/div/a",
                "https://seriesone.dynamo-ny.com/2018/05/29/the-democratization-of-finance/", log);

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Back to blog']")));
        Thread.sleep(400);
        SetupClass.GetDriver().findElement(By.xpath("//a[text()='Back to blog']")).click();

        check_link.CheckLinkURL("Learn more (First blog post)",
                "//*[@id=\"blog-item-container\"]/div[2]/div/div[2]/div[4]/div/a",
                "https://seriesone.dynamo-ny.com/2018/05/21/seriesone-launches-to-instill-much-needed-maturity-in-the-crowdfunding-ico-and-crypto-wildwest/", log);

        SetupClass.GetDriver().findElement(By.xpath("//a[text()='Back to blog']")).click();

        check_link.CheckLinkURL("Learn more (First blog post)",
                "//*[@id=\"blog-item-container\"]/div[3]/div/div[2]/div[4]/div/a",
                "https://seriesone.dynamo-ny.com/2018/05/08/bitcoin-sees-wall-street-warm-to-trading-virtual-currency-via-the-new-york-times/", log);

        SetupClass.GetDriver().findElement(By.xpath("//a[text()='Back to blog']")).click();
    }

    @Title("Unit tests for Blog page: check social media links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Blog page","Social Media Icons"})
    @Description("Check social media links on the 'Blog' page")
    @Test
    public void BlogPage_SocialMediaIcons() throws Exception{
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        SocialMediaIcons.LinksCheck(methodName, log);
    }

    @Title("Unit tests for Blog page: check footer links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Blog page","Footer links"})
    @Description("Check footer links on the 'Blog' page")
    @Test
    public void BlogPage_FooterLinks() throws Exception{
        FooterLinks links = new FooterLinks();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        links.LinksCheck(methodName,
                "/html/body/div/div[2]/div[3]/footer/div[1]/div[2]/div[",
                "/html/body/div/div[2]/div[3]/footer/div[2]/div/div/a[", log);
    }
}
