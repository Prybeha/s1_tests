package UnitClassSet.StaticPages;

import SupportClasses.SetupClass.SetupClass;

public class PagesURL {

    public static void MainPage(){ SetupClass.GetDriver().get("https://seriesone.dynamo-ny.com/"); }
    public static void BrowseDealsPage(){
        SetupClass.GetDriver().get("https://secure-seriesone.dynamo-ny.com/deals");
    }
    public static void RaiseFundsPage(){ SetupClass.GetDriver().get("https://seriesone.dynamo-ny.com/start-raising-funds/"); }
    public static void LicensePlatformPage(){ SetupClass.GetDriver().get("https://seriesone.dynamo-ny.com/brokerone/"); }
    public static void AboutInvestingPage(){
        SetupClass.GetDriver().get("https://seriesone.dynamo-ny.com/learn/");
    }
    public static void DigitalSecurityOfferingsPage(){ SetupClass.GetDriver().get("https://seriesone.dynamo-ny.com/digital-security-offerings/"); }
    public static void RegisterPage(){ SetupClass.GetDriver().get("https://secure-seriesone.dynamo-ny.com/register"); }
    public static void LoginPage(){
        SetupClass.GetDriver().get("https://secure-seriesone.dynamo-ny.com/login");
    }
    public static void ContactUsPage(){ SetupClass.GetDriver().get("https://secure-seriesone.dynamo-ny.com/contact-us/"); }
    public static void AboutUsPage(){ SetupClass.GetDriver().get("https://secure-seriesone.dynamo-ny.com/about-us"); }
    public static void BlogPage(){ SetupClass.GetDriver().get("https://seriesone.dynamo-ny.com/blog/"); }
    public static void InvestorsQuestionsPage(){ SetupClass.GetDriver().get("https://seriesone.dynamo-ny.com/faqs-for-investors/"); }
    public static void BusinessQuestionsPage(){ SetupClass.GetDriver().get("https://seriesone.dynamo-ny.com/faqs-for-business-owners/"); }
    public static void EducationResourcesPage(){ SetupClass.GetDriver().get("https://seriesone.dynamo-ny.com/resources-for-investors/#3960"); }
    public static void ImportantDisclaimerPage(){ SetupClass.GetDriver().get("https://secure-seriesone.dynamo-ny.com/important-disclaimer"); }
    public static void TermsOfUsePage(){ SetupClass.GetDriver().get("https://secure-seriesone.dynamo-ny.com/terms-of-use"); }
    public static void PrivacyPolicyPage(){ SetupClass.GetDriver().get("https://secure-seriesone.dynamo-ny.com/privacy-policy"); }
}
