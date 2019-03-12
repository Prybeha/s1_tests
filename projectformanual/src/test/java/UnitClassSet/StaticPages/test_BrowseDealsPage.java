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

public class test_BrowseDealsPage extends SetupClass {
    private CheckLink check_link = new CheckLink();
    private Field field = new Field();
    private static final Logger log = Logger.getLogger(test_MainPage.class);

    @Title("Unit tests for Browse deals page: check title")
    @Severity(SeverityLevel.MINOR)
    @Features({"Browse deals page","Title"})
    @Description("Check title on the 'Browse deals' page")
    @Test
    public void BrowseDealsPage_Title(){
        PagesURL.BrowseDealsPage();

        if(!SetupClass.GetDriver().getTitle().equals("Investment deals for accredited and non-accredited investors | seriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Title("Unit tests for Browse deals page: check header button links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Browse deals page","Header Buttons"})
    @Description("Check header button links on the 'Browse deals' page")
    @Test
    public void BrowseDealsPage_HeaderButtons() throws Exception{
        PagesURL.BrowseDealsPage();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        HeaderButtonsCheck.HeaderLinks("/html/body/header/div[2]/div/ul/li[", log, methodName);
    }

    @Title("Unit tests for Browse deals page: 'Sign Up' and 'Login' buttons")
    @Severity(SeverityLevel.MINOR)
    @Features({"Browse deals page","'Sign Up' and 'Login' buttons"})
    @Description("Check 'Sign Up' and 'Login' buttons on the 'Browse deals' page")
    @Test
    public void BrowseDealsPage_SignUpAndLogin(){
        PagesURL.BrowseDealsPage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register", log);

        PagesURL.BrowseDealsPage();

        check_link.CheckLinkURL("Login",
                "/html/body/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login", log);
    }

    @Title("Unit tests for Browse deals page: check deals URL for main offerings")
    @Severity(SeverityLevel.MINOR)
    @Features({"Browse deals page"})
    @Description("Check deals URL for main offerings on the 'Browse deals' page")
    @Test
    public void BrowseDealsPage_CheckDealsPage(){
        PagesURL.BrowseDealsPage();
        String[] published_deals = {"PLANET DIGITAL PARTNERS INC.", "KINESIS TEST", "HELENA QA", "IUNO TEST"};
        String[] urls_of_published_deals = {"https://secure-seriesone.dynamo-ny.com/deal/pdp-qa", "https://secure-seriesone.dynamo-ny.com/deal/kinesis-qa",
                "https://secure-seriesone.dynamo-ny.com/deal/helena-qa", "https://secure-seriesone.dynamo-ny.com/deal/iuno"};

        // count how much deals published on the browse deals page
        int quantity = 0;
        for (int i = 0; i < 100; i++) {
            if (field.ExistElementOnThePage("//*[@id=\"deal-container\"]/div[" + Integer.toString(i + 1) + "]/div/div[3]/div[1]/div", 1)) {
                quantity++;
            } else {
                break;
            }
        }

        for (int i = 0; i < published_deals.length; i++) {
            for (int j = 0; j < quantity; j++) {
                if (SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"deal-container\"]/div[" + Integer.toString(j + 1) + "]/div/div[3]/div[1]/div")).getText().equals(published_deals[i])) {
                    check_link.CheckLinkURL(published_deals[i],
                            "//*[@id=\"deal-container\"]/div[" + Integer.toString(j + 1) + "]/div/div[3]/div[3]/p[4]/a",
                            urls_of_published_deals[i], log);

                    PagesURL.BrowseDealsPage();
                    SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://secure-seriesone.dynamo-ny.com/deal/pdp-qa']")));
                }
            }
        }
    }

    @Title("Unit tests for Browse deals page: main content on the page")
    @Severity(SeverityLevel.MINOR)
    @Features({"Browse deals page","Content"})
    @Description("Check main content on the 'Browse deals' page")
    @Test
    public void BrowseDealsPage_Body() throws Exception{
        PagesURL.BrowseDealsPage();
        SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section[2]/div[2]/div/div/div/a[1]")).click();
        String parentWindow = SetupClass.GetDriver().getWindowHandle();

        for(String windowHandle  : SetupClass.GetDriver().getWindowHandles())
        {
            if(!windowHandle.equals(parentWindow))
            {
                SetupClass.GetDriver().switchTo().window(windowHandle);
                Thread.sleep(2000);
                if (!SetupClass.GetDriver().getCurrentUrl().equals("https://seriesone.dynamo-ny.com/faqs-for-investors/#18813")) {
                    Logging.Log_error(log, "'Regulation D' link has been broken");
                    throw new NewAssertError("'Regulation D' link has been broken");
                }
                SetupClass.GetDriver().close(); //closing child window
                SetupClass.GetDriver().switchTo().window(parentWindow); //cntrl to parent window
            }
        }
        Logging.Log_debug(log, "'Regulation D' link works good");

        PagesURL.BrowseDealsPage();
        SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section[2]/div[2]/div/div/div/a[2]")).click();
        parentWindow = SetupClass.GetDriver().getWindowHandle();

        for(String windowHandle  : SetupClass.GetDriver().getWindowHandles())
        {
            if(!windowHandle.equals(parentWindow))
            {
                SetupClass.GetDriver().switchTo().window(windowHandle);
                Thread.sleep(2000);
                if (!SetupClass.GetDriver().getCurrentUrl().equals("https://seriesone.dynamo-ny.com/resources-for-investors/#3960")) {
                    Logging.Log_error(log, "'Regulation CF' link has been broken");
                    throw new NewAssertError("'Regulation CF' link has been broken");
                }
                SetupClass.GetDriver().close(); //closing child window
                SetupClass.GetDriver().switchTo().window(parentWindow); //cntrl to parent window
            }
        }
        Logging.Log_debug(log, "'Regulation CF' link works good");

        PagesURL.BrowseDealsPage();
        SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section[2]/div[4]/div/div/div/a")).click();
        parentWindow = SetupClass.GetDriver().getWindowHandle();

        for(String windowHandle  : SetupClass.GetDriver().getWindowHandles())
        {
            if(!windowHandle.equals(parentWindow))
            {
                SetupClass.GetDriver().switchTo().window(windowHandle);
                Thread.sleep(2000);
                if (!SetupClass.GetDriver().getCurrentUrl().equals("https://seriesone.dynamo-ny.com/learn/")) {
                    Logging.Log_error(log, "'Learn more about investing' link has been broken");
                    throw new NewAssertError("'Learn more about investing' link has been broken");
                }
                SetupClass.GetDriver().close(); //closing child window
                SetupClass.GetDriver().switchTo().window(parentWindow); //cntrl to parent window
            }
        }
        Logging.Log_debug(log, "'Learn more about investing' link works good");
    }

    @Title("Unit tests for Browse deals page: check social media links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Browse deals page","Social Media Icons"})
    @Description("Check social media links on the 'Browse deals' page")
    @Test
    public void BrowseDealsPage_SocialMediaIcons() throws Exception{
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        SocialMediaIcons.LinksCheck(methodName, log);
    }

    @Title("Unit tests for Browse deals page: check footer links")
    @Severity(SeverityLevel.MINOR)
    @Features({"Browse deals page","Footer links"})
    @Description("Check footer links on the 'Browse deals' page")
    @Test
    public void BrowseDealsPage_FooterLinks() throws Exception{
        FooterLinks links = new FooterLinks();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        links.LinksCheck(methodName,
                "/html/body/div/footer/div[1]/div[2]/div[",
                "/html/body/div/footer/div[2]/div/div/a[", log);
    }
}
