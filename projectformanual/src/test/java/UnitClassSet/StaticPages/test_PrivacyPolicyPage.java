package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.SetupClass.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class test_PrivacyPolicyPage extends SetupClass {
    private CheckLink check_link = new CheckLink();

    @Test
    public void PrivacyPolicyPage_Title(){
        PagesURL.PrivacyPolicyPage();

        //  "seriesOne" word - make first char uppercase
        if(!SetupClass.GetDriver().getTitle().equals("Privacy Policy | seriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Test
    public void PrivacyPolicyPage_HeaderButtons() throws Exception{
        PagesURL.PrivacyPolicyPage();

        String[] expected_urls = {"https://secure-seriesone.dynamo-ny.com/deals", "https://seriesone.dynamo-ny.com/start-raising-funds/",
                "https://seriesone.dynamo-ny.com/brokerone/", "https://seriesone.dynamo-ny.com/learn/", "https://seriesone.dynamo-ny.com/digital-security-offerings/"};

        for(int i = 1;i <= expected_urls.length; i++) {
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div[2]/div/ul/li[" + i + "]/a")));
            SetupClass.GetDriver().findElement(By.xpath("/html/body/header/div[2]/div/ul/li[" + i + "]/a")).click();
            Thread.sleep(500);
            if (!SetupClass.GetDriver().getCurrentUrl().equals(expected_urls[i-1])) {
                throw new NewAssertError("Header button goes on the wrong page: " + SetupClass.GetDriver().getCurrentUrl());
            }
            PagesURL.PrivacyPolicyPage();
        }
    }

    @Test
    public void PrivacyPolicyPage_SignUpAndLogin() throws Exception {
        PagesURL.PrivacyPolicyPage();

        check_link.CheckLinkURL("SignUp",
                "/html/body/header/div[2]/div/div[2]/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/register");

        PagesURL.PrivacyPolicyPage();

        check_link.CheckLinkURL("Login",
                "/html/body/header/div[2]/div/div[2]/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/login");

        PagesURL.PrivacyPolicyPage();
        Thread.sleep(1000);

        check_link.CheckLinkURL("Learn more",
                "//a[text()='Learn More']",
                "https://secure-seriesone.dynamo-ny.com/deals");
    }

    @Test
    public void PrivacyPolicyPage_SocialMediaIcons() throws Exception{

        PagesURL.PrivacyPolicyPage();
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
                Thread.sleep(3000);
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
    public void PrivacyPolicyPage_FooterLinks() throws Exception{
        PagesURL.PrivacyPolicyPage();
        Thread.sleep(500);

        check_link.CheckLinkURL("About Us",
                "/html/body/div/footer/div[1]/div[2]/div[1]/ul/li[1]/a",
                "https://secure-seriesone.dynamo-ny.com/about-us");

        PagesURL.PrivacyPolicyPage();
        Thread.sleep(500);

        check_link.CheckLinkURL("Blog",
                "/html/body/div/footer/div[1]/div[2]/div[1]/ul/li[2]/a",
                "https://seriesone.dynamo-ny.com/blog/");

        PagesURL.PrivacyPolicyPage();
        Thread.sleep(500);

        check_link.CheckLinkURL("Contact Us",
                "/html/body/div/footer/div[1]/div[2]/div[1]/ul/li[3]/a",
                "https://secure-seriesone.dynamo-ny.com/contact-us/");

        PagesURL.PrivacyPolicyPage();
        Thread.sleep(500);

        check_link.CheckLinkURL("Browse deals",
                "/html/body/div/footer/div[1]/div[2]/div[2]/ul/li[1]/a",
                "https://secure-seriesone.dynamo-ny.com/deals");

        PagesURL.PrivacyPolicyPage();
        Thread.sleep(500);

        check_link.CheckLinkURL("About Investing",
                "/html/body/div/footer/div[1]/div[2]/div[2]/ul/li[2]/a",
                "https://seriesone.dynamo-ny.com/learn/");

        PagesURL.PrivacyPolicyPage();
        Thread.sleep(500);

        check_link.CheckLinkURL("Investor Questions",
                "/html/body/div/footer/div[1]/div[2]/div[2]/ul/li[3]/a",
                "https://seriesone.dynamo-ny.com/faqs-for-investors/");

        PagesURL.PrivacyPolicyPage();
        Thread.sleep(500);

        check_link.CheckLinkURL("Raise Funds",
                "/html/body/div/footer/div[1]/div[2]/div[4]/ul/li[1]/a",
                "https://seriesone.dynamo-ny.com/start-raising-funds/");

        PagesURL.PrivacyPolicyPage();
        Thread.sleep(500);

        check_link.CheckLinkURL("Business Questions",
                "/html/body/div/footer/div[1]/div[2]/div[4]/ul/li[2]/a",
                "https://seriesone.dynamo-ny.com/faqs-for-business-owners/");

        PagesURL.PrivacyPolicyPage();
        Thread.sleep(500);

        check_link.CheckLinkURL("License Platform",
                "/html/body/div/footer/div[1]/div[2]/div[5]/ul/li/a",
                "https://seriesone.dynamo-ny.com/brokerone/");

        PagesURL.PrivacyPolicyPage();
        Thread.sleep(500);

        check_link.CheckLinkURL("FAQs & Educations",
                "/html/body/div/footer/div[1]/div[2]/div[8]/ul/li[1]/a",
                "https://seriesone.dynamo-ny.com/faqs-for-investors/");

        PagesURL.PrivacyPolicyPage();
        Thread.sleep(500);

        check_link.CheckLinkURL("Important Disclaimer",
                "/html/body/div/footer/div[1]/div[2]/div[8]/ul/li[2]/a",
                "https://secure-seriesone.dynamo-ny.com/important-disclaimer");

        PagesURL.PrivacyPolicyPage();
        Thread.sleep(500);

        check_link.CheckLinkURL("Digital Security Offerings",
                "/html/body/div/footer/div[1]/div[2]/div[8]/ul/li[3]/a",
                "https://seriesone.dynamo-ny.com/digital-security-offerings/");

        PagesURL.PrivacyPolicyPage();
        Thread.sleep(500);

        check_link.CheckLinkURL("Terms of Use",
                "/html/body/div/footer/div[2]/div/div/a[1]",
                "https://secure-seriesone.dynamo-ny.com/terms-of-use");

        PagesURL.PrivacyPolicyPage();
        Thread.sleep(500);

        check_link.CheckLinkURL("Privacy Policy",
                "/html/body/div/footer/div[2]/div/div/a[2]",
                "https://secure-seriesone.dynamo-ny.com/privacy-policy");
    }
}
