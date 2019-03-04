package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Field;
import SupportClasses.SetupClass.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class test_BrowseDealsPage extends SetupClass {
    private CheckLink check_link = new CheckLink();
    private Field field = new Field();

    @Test
    public void BrowseDealsPage_Title(){
        PagesURL.DealPage();

        if(!SetupClass.GetDriver().getTitle().equals("Investment deals for accredited and non-accredited investors | seriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Test
    public void BrowseDealsPage_HeaderButtons() throws Exception{
        PagesURL.DealPage();

        String[] expected_urls = {"https://secure-seriesone.dynamo-ny.com/deals", "https://seriesone.dynamo-ny.com/start-raising-funds/",
                "https://seriesone.dynamo-ny.com/brokerone/", "https://seriesone.dynamo-ny.com/learn/", "https://seriesone.dynamo-ny.com/digital-security-offerings/"};

        for(int i = 1;i <= expected_urls.length; i++) {
            //System.out.println(i);
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[2]/div/ul/li[" + i + "]/a")));
            SetupClass.GetDriver().findElement(By.xpath("/html/body/header/div[2]/div/ul/li[" + i + "]/a")).click();
            Thread.sleep(500);
            if (!SetupClass.GetDriver().getCurrentUrl().equals(expected_urls[i-1])) {
                throw new NewAssertError("Header button goes on the wrong page: " + SetupClass.GetDriver().getCurrentUrl());
            }
            PagesURL.DealPage();
        }
    }

    @Test
    public void BrowseDealsPage_SignUpAndLogin(){
        PagesURL.DealPage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register");

        PagesURL.DealPage();

        check_link.CheckLinkURL("Login",
                "/html/body/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login");
    }

    @Test
    public void BrowseDealsPage_CheckDealsPage(){
        PagesURL.DealPage();
        String[] published_deals = {"PLANET DIGITAL PARTNERS INC.", "KINESIS TEST", "HELENA QA", "IUNO TEST"};
        String[] urls_of_published_deals = {"https://secure-seriesone.dynamo-ny.com/deal/pdp-qa", "https://secure-seriesone.dynamo-ny.com/deal/kinesis-qa",
                "https://secure-seriesone.dynamo-ny.com/deal/helena-qa", "https://secure-seriesone.dynamo-ny.com/deal/iuno"};

        // count how much deals published on the browse deals page
        int quantity = 0;
        for (int i = 0; i < 100; i++) {
            if (field.ExistElementOnThePage("//*[@id=\"deal-container\"]/div[" + Integer.toString(i + 1) + "]/div/div[3]/div[1]/div", 1)) {
                quantity++;
                //System.out.println(quantity);
            } else {
                break;
            }
        }

        for (int i = 0; i < published_deals.length; i++) {
            for (int j = 0; j < quantity; j++) {
                if (SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"deal-container\"]/div[" + Integer.toString(j + 1) + "]/div/div[3]/div[1]/div")).getText().equals(published_deals[i])) {
                    check_link.CheckLinkURL(published_deals[i],
                            "//*[@id=\"deal-container\"]/div[" + Integer.toString(j + 1) + "]/div/div[3]/div[3]/p[4]/a",
                            urls_of_published_deals[i]);

                    PagesURL.DealPage();
                }
            }
        }
    }

    @Test
    public void BrowseDealsPage_BodyLinks() throws Exception{
        PagesURL.DealPage();
        SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section[2]/div[2]/div/div/div/a[1]")).click();
        String parentWindow = SetupClass.GetDriver().getWindowHandle();

        for(String windowHandle  : SetupClass.GetDriver().getWindowHandles())
        {
            if(!windowHandle.equals(parentWindow))
            {
                SetupClass.GetDriver().switchTo().window(windowHandle);
                Thread.sleep(2000);
                if (!SetupClass.GetDriver().getCurrentUrl().equals("https://seriesone.dynamo-ny.com/faqs-for-investors/#18813")) {
                    throw new NewAssertError("'Regulation D' link has been broken");
                }
                SetupClass.GetDriver().close(); //closing child window
                SetupClass.GetDriver().switchTo().window(parentWindow); //cntrl to parent window
            }
        }

        PagesURL.DealPage();
        SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section[2]/div[2]/div/div/div/a[2]")).click();
        parentWindow = SetupClass.GetDriver().getWindowHandle();

        for(String windowHandle  : SetupClass.GetDriver().getWindowHandles())
        {
            if(!windowHandle.equals(parentWindow))
            {
                SetupClass.GetDriver().switchTo().window(windowHandle);
                Thread.sleep(2000);
                if (!SetupClass.GetDriver().getCurrentUrl().equals("https://seriesone.dynamo-ny.com/resources-for-investors/#3960")) {
                    throw new NewAssertError("'Regulation CF' link has been broken");
                }
                SetupClass.GetDriver().close(); //closing child window
                SetupClass.GetDriver().switchTo().window(parentWindow); //cntrl to parent window
            }
        }

        PagesURL.DealPage();
        SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section[2]/div[4]/div/div/div/a")).click();
        parentWindow = SetupClass.GetDriver().getWindowHandle();

        for(String windowHandle  : SetupClass.GetDriver().getWindowHandles())
        {
            if(!windowHandle.equals(parentWindow))
            {
                SetupClass.GetDriver().switchTo().window(windowHandle);
                Thread.sleep(2000);
                if (!SetupClass.GetDriver().getCurrentUrl().equals("https://seriesone.dynamo-ny.com/learn/")) {
                    throw new NewAssertError("'Learn more about investing' link has been broken");
                }
                SetupClass.GetDriver().close(); //closing child window
                SetupClass.GetDriver().switchTo().window(parentWindow); //cntrl to parent window
            }
        }
    }

    @Test
    public void BrowseDealsPage_SocialMediaIcons() throws Exception{

        PagesURL.MainPage();
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
    public void BrowseDealsPage_FooterLinks(){
        PagesURL.DealPage();

        check_link.CheckLinkURL("About Us",
                "/html/body/div/footer/div[1]/div[2]/div[1]/ul/li[1]/a",
                "https://secure-seriesone.dynamo-ny.com/about-us");

        PagesURL.DealPage();

        check_link.CheckLinkURL("Blog",
                "/html/body/div/footer/div[1]/div[2]/div[1]/ul/li[2]/a",
                "https://seriesone.dynamo-ny.com/blog/");

        PagesURL.DealPage();

        check_link.CheckLinkURL("Contact Us",
                "/html/body/div/footer/div[1]/div[2]/div[1]/ul/li[3]/a",
                "https://secure-seriesone.dynamo-ny.com/contact-us/");

        PagesURL.DealPage();

        check_link.CheckLinkURL("Browse deals",
                "/html/body/div/footer/div[1]/div[2]/div[2]/ul/li[1]/a",
                "https://secure-seriesone.dynamo-ny.com/deals");

        PagesURL.DealPage();

        check_link.CheckLinkURL("About Investing",
                "/html/body/div/footer/div[1]/div[2]/div[2]/ul/li[2]/a",
                "https://seriesone.dynamo-ny.com/learn/");

        PagesURL.DealPage();

        check_link.CheckLinkURL("Investor Questions",
                "/html/body/div/footer/div[1]/div[2]/div[2]/ul/li[3]/a",
                "https://seriesone.dynamo-ny.com/faqs-for-investors/");

        PagesURL.DealPage();

        check_link.CheckLinkURL("Raise Funds",
                "/html/body/div/footer/div[1]/div[2]/div[4]/ul/li[1]/a",
                "https://seriesone.dynamo-ny.com/start-raising-funds/");

        PagesURL.DealPage();

        check_link.CheckLinkURL("Business Questions",
                "/html/body/div/footer/div[1]/div[2]/div[4]/ul/li[2]/a",
                "https://seriesone.dynamo-ny.com/faqs-for-business-owners/");

        PagesURL.DealPage();

        check_link.CheckLinkURL("License Platform",
                "/html/body/div/footer/div[1]/div[2]/div[5]/ul/li/a",
                "https://seriesone.dynamo-ny.com/brokerone/");

        PagesURL.DealPage();

        check_link.CheckLinkURL("FAQs & Educations",
                "/html/body/div/footer/div[1]/div[2]/div[8]/ul/li[1]/a",
                "https://seriesone.dynamo-ny.com/faqs-for-investors/");

        PagesURL.DealPage();

        check_link.CheckLinkURL("Important Disclaimer",
                "/html/body/div/footer/div[1]/div[2]/div[8]/ul/li[2]/a",
                "https://secure-seriesone.dynamo-ny.com/important-disclaimer");

        PagesURL.DealPage();

        check_link.CheckLinkURL("Digital Security Offerings",
                "/html/body/div/footer/div[1]/div[2]/div[8]/ul/li[3]/a",
                "https://seriesone.dynamo-ny.com/digital-security-offerings/");

        PagesURL.DealPage();

        check_link.CheckLinkURL("Terms of Use",
                "/html/body/div/footer/div[2]/div/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/terms-of-use");

        PagesURL.DealPage();

        check_link.CheckLinkURL("Privacy Policy",
                "/html/body/div/footer/div[2]/div/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/privacy-policy");
    }
}
