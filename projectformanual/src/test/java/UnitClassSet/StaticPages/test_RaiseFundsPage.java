package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Field;
import SupportClasses.SetupClass.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class test_RaiseFundsPage extends SetupClass{
    private CheckLink check_link = new CheckLink();
    private Field field = new Field();

    @Test
    public void RaiseFundsPage_Title(){
        PagesURL.FundsPage();

        if(!SetupClass.GetDriver().getTitle().equals("Raise funds, showcase your company to inspire investors | seriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Test
    public void RaiseFundsPage_HeaderButtons() throws Exception{
        PagesURL.FundsPage();

        String[] expected_urls = {"https://secure-seriesone.dynamo-ny.com/deals", "https://seriesone.dynamo-ny.com/start-raising-funds/",
                "https://seriesone.dynamo-ny.com/brokerone/", "https://seriesone.dynamo-ny.com/learn/", "https://seriesone.dynamo-ny.com/digital-security-offerings/"};

        for(int i = 1;i <= expected_urls.length; i++) {
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/header/div[2]/div/ul/li[" + i + "]/a")));
            SetupClass.GetDriver().findElement(By.xpath("/html/body/div[1]/header/div[2]/div/ul/li[" + i + "]/a")).click();
            Thread.sleep(500);
            if (!SetupClass.GetDriver().getCurrentUrl().equals(expected_urls[i-1])) {
                throw new NewAssertError("Header button goes on the wrong page: " + SetupClass.GetDriver().getCurrentUrl());
            }
            PagesURL.FundsPage();
        }
    }

    @Test
    public void RaiseFundsPage_SignUpAndLogin(){
        PagesURL.FundsPage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/div[1]/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register");

        PagesURL.FundsPage();

        check_link.CheckLinkURL("Login",
                "/html/body/div[1]/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login");
    }

    @Test
    public void RaiseFundsPage_Body() throws Exception{
        PagesURL.FundsPage();

        check_link.CheckLinkURL("Learn more",
                "//a[@data-amp-attribute='[raise][latest][link]']",
                "https://secure-seriesone.dynamo-ny.com/deals");

        PagesURL.FundsPage();

        Thread.sleep(1000);
        check_link.CheckLinkURL("Contact Us to Start",
                "//a[@class='fw-link']",
                "https://seriesone.dynamo-ny.com/start-raising-funds/#start-raise-container");

        PagesURL.FundsPage();

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
                    throw new NewAssertError("FAQ text does not appears");
                }
            }
            SetupClass.GetDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        check_link.CheckLinkURL("FAQs",
                "//a[text()='FAQs']",
                "https://seriesone.dynamo-ny.com/faqs-for-business-owners/");

        PagesURL.FundsPage();

        if(!field.ExistElementOnThePage("//input[@id='start_raise_firstName']",1) ||
                !field.ExistElementOnThePage("//input[@id='start_raise_lastName']",1) ||
                !field.ExistElementOnThePage("//input[@id='start_raise_email']",1) ||
                !field.ExistElementOnThePage("//textarea[@id='start_raise_description']",1) ||
                !field.ExistElementOnThePage("//button[@id='start_raise_submit']",1)){
            throw new NewAssertError("One of the Contact Us fields do not displayed");
        }
    }

    @Test
    public void RaiseFundsPage_SocialMediaIcons() throws Exception{

        PagesURL.FundsPage();
        Thread.sleep(500);
        String parentWindow = SetupClass.GetDriver().getWindowHandle();

        SetupClass.GetDriver().findElement(By.xpath("//img[@src='https://secure-seriesone.dynamo-ny.com/bundles/frontend/images/symbols/social/linkedIn2.svg']")).click();
        Thread.sleep(1000);

        for(String windowHandle  : SetupClass.GetDriver().getWindowHandles())
        {
            if(!windowHandle.equals(parentWindow))
            {
                SetupClass.GetDriver().switchTo().window(windowHandle);
                Thread.sleep(2000);
                if (!SetupClass.GetDriver().getCurrentUrl().equals("https://www.linkedin.com/company/seriesone") &&
                        !SetupClass.GetDriver().getCurrentUrl().contains("https://www.linkedin.com/authwall")) {
                    throw new NewAssertError("'LinkedIn Logo' button has been broken");
                }
                SetupClass.GetDriver().close(); //closing child window
                SetupClass.GetDriver().switchTo().window(parentWindow); //cntrl to parent window
            }
        }

        Thread.sleep(500);
        parentWindow = SetupClass.GetDriver().getWindowHandle();

        SetupClass.GetDriver().findElement(By.xpath("//img[@src='https://secure-seriesone.dynamo-ny.com/bundles/frontend/images/symbols/social/facebook2.svg']")).click();
        Thread.sleep(1000);

        for(String windowHandle  : SetupClass.GetDriver().getWindowHandles())
        {
            if(!windowHandle.equals(parentWindow))
            {
                SetupClass.GetDriver().switchTo().window(windowHandle);
                Thread.sleep(2000);
                if (!SetupClass.GetDriver().getCurrentUrl().equals("https://business.facebook.com/seriesOneFinance/") &&
                        !SetupClass.GetDriver().getCurrentUrl().equals("https://business.facebook.com/SeriesOne-162178631110154")) {
                    throw new NewAssertError("'Facebook Logo' button has been broken");
                }
                SetupClass.GetDriver().close(); //closing child window
                SetupClass.GetDriver().switchTo().window(parentWindow); //cntrl to parent window
            }
        }

        Thread.sleep(500);
        parentWindow = SetupClass.GetDriver().getWindowHandle();

        SetupClass.GetDriver().findElement(By.xpath("//img[@src='https://secure-seriesone.dynamo-ny.com/bundles/frontend/images/symbols/social/twitter2.svg']")).click();
        Thread.sleep(1000);

        for(String windowHandle  : SetupClass.GetDriver().getWindowHandles())
        {
            if(!windowHandle.equals(parentWindow))
            {
                SetupClass.GetDriver().switchTo().window(windowHandle);
                Thread.sleep(2000);
                if (!SetupClass.GetDriver().getCurrentUrl().equals("https://twitter.com/seriesone_info")) {
                    throw new NewAssertError("'Twitter Logo' button has been broken");
                }
                SetupClass.GetDriver().close(); //closing child window
                SetupClass.GetDriver().switchTo().window(parentWindow); //cntrl to parent window
            }
        }
    }

    @Test
    public void RaiseFundsPage_FooterLinks(){
        PagesURL.FundsPage();

        check_link.CheckLinkURL("About Us",
                "/html/body/div[2]/div[2]/footer/div[1]/div[2]/div[1]/ul/li[1]/a",
                "https://secure-seriesone.dynamo-ny.com/about-us");

        PagesURL.FundsPage();

        check_link.CheckLinkURL("Blog",
                "/html/body/div[2]/div[2]/footer/div[1]/div[2]/div[1]/ul/li[2]/a",
                "https://seriesone.dynamo-ny.com/blog/");

        PagesURL.FundsPage();

        check_link.CheckLinkURL("Contact Us",
                "/html/body/div[2]/div[2]/footer/div[1]/div[2]/div[1]/ul/li[3]/a",
                "https://secure-seriesone.dynamo-ny.com/contact-us/");

        PagesURL.FundsPage();

        check_link.CheckLinkURL("Browse deals",
                "/html/body/div[2]/div[2]/footer/div[1]/div[2]/div[2]/ul/li[1]/a",
                "https://secure-seriesone.dynamo-ny.com/deals");

        PagesURL.FundsPage();

        check_link.CheckLinkURL("About Investing",
                "/html/body/div[2]/div[2]/footer/div[1]/div[2]/div[2]/ul/li[2]/a",
                "https://seriesone.dynamo-ny.com/learn/");

        PagesURL.FundsPage();

        check_link.CheckLinkURL("Investor Questions",
                "/html/body/div[2]/div[2]/footer/div[1]/div[2]/div[2]/ul/li[3]/a",
                "https://seriesone.dynamo-ny.com/faqs-for-investors/");

        PagesURL.FundsPage();

        check_link.CheckLinkURL("Raise Funds",
                "/html/body/div[2]/div[2]/footer/div[1]/div[2]/div[4]/ul/li[1]/a",
                "https://seriesone.dynamo-ny.com/start-raising-funds/");

        PagesURL.FundsPage();

        check_link.CheckLinkURL("Business Questions",
                "/html/body/div[2]/div[2]/footer/div[1]/div[2]/div[4]/ul/li[2]/a",
                "https://seriesone.dynamo-ny.com/faqs-for-business-owners/");

        PagesURL.FundsPage();

        check_link.CheckLinkURL("License Platform",
                "/html/body/div[2]/div[2]/footer/div[1]/div[2]/div[5]/ul/li/a",
                "https://seriesone.dynamo-ny.com/brokerone/");

        PagesURL.FundsPage();

        check_link.CheckLinkURL("FAQs & Educations",
                "/html/body/div[2]/div[2]/footer/div[1]/div[2]/div[8]/ul/li[1]/a",
                "https://seriesone.dynamo-ny.com/faqs-for-investors/");

        PagesURL.FundsPage();

        check_link.CheckLinkURL("Important Disclaimer",
                "/html/body/div[2]/div[2]/footer/div[1]/div[2]/div[8]/ul/li[2]/a",
                "https://secure-seriesone.dynamo-ny.com/important-disclaimer");

        PagesURL.FundsPage();

        check_link.CheckLinkURL("Digital Security Offerings",
                "/html/body/div[2]/div[2]/footer/div[1]/div[2]/div[8]/ul/li[3]/a",
                "https://seriesone.dynamo-ny.com/digital-security-offerings/");

        PagesURL.FundsPage();

        check_link.CheckLinkURL("Terms of Use",
                "/html/body/div[2]/div[2]/footer/div[2]/div/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/terms-of-use");

        PagesURL.FundsPage();

        check_link.CheckLinkURL("Privacy Policy",
                "/html/body/div[2]/div[2]/footer/div[2]/div/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/privacy-policy");
    }
}
