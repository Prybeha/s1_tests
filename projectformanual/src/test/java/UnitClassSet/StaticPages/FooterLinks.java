package UnitClassSet.StaticPages;

import org.apache.log4j.Logger;

import java.lang.reflect.Method;

public class FooterLinks {
    private CheckLink check_link = new CheckLink();

    public void LinksCheck(String calling_method, String xpath_locator, String xpath_terms, Logger log) throws Exception{
        calling_method = calling_method.replace("_FooterLinks","");

        Method method = PagesURL.class.getMethod(calling_method);
        method.invoke(null);

        check_link.CheckLinkURL("About Us",
                xpath_locator +"1]/ul/li[1]/a",
                "https://secure-seriesone.dynamo-ny.com/about-us", log);

        method.invoke(null);

        check_link.CheckLinkURL("Blog",
                xpath_locator +"1]/ul/li[2]/a",
                "https://seriesone.dynamo-ny.com/blog/", log);

        method.invoke(null);

        check_link.CheckLinkURL("Contact Us",
                xpath_locator +"1]/ul/li[3]/a",
                "https://secure-seriesone.dynamo-ny.com/contact-us/", log);

        method.invoke(null);

        check_link.CheckLinkURL("Browse deals",
                xpath_locator +"2]/ul/li[1]/a",
                "https://secure-seriesone.dynamo-ny.com/deals", log);

        method.invoke(null);

        check_link.CheckLinkURL("About Investing",
                xpath_locator +"2]/ul/li[2]/a",
                "https://seriesone.dynamo-ny.com/learn/", log);

        method.invoke(null);

        check_link.CheckLinkURL("Investor Questions",
                xpath_locator +"2]/ul/li[3]/a",
                "https://seriesone.dynamo-ny.com/faqs-for-investors/", log);

        method.invoke(null);

        check_link.CheckLinkURL("Raise Funds",
                xpath_locator +"4]/ul/li[1]/a",
                "https://seriesone.dynamo-ny.com/start-raising-funds/", log);

        method.invoke(null);

        check_link.CheckLinkURL("Business Questions",
                xpath_locator +"4]/ul/li[2]/a",
                "https://seriesone.dynamo-ny.com/faqs-for-business-owners/", log);

        method.invoke(null);

        check_link.CheckLinkURL("License Platform",
                xpath_locator +"5]/ul/li/a",
                "https://seriesone.dynamo-ny.com/brokerone/", log);

        method.invoke(null);

        check_link.CheckLinkURL("FAQs & Educations",
                xpath_locator +"8]/ul/li[1]/a",
                "https://seriesone.dynamo-ny.com/faqs-for-investors/", log);

        method.invoke(null);

        check_link.CheckLinkURL("Important Disclaimer",
                xpath_locator +"8]/ul/li[2]/a",
                "https://secure-seriesone.dynamo-ny.com/important-disclaimer", log);

        method.invoke(null);

        check_link.CheckLinkURL("Digital Security Offerings",
                xpath_locator +"8]/ul/li[3]/a",
                "https://seriesone.dynamo-ny.com/digital-security-offerings/", log);

        method.invoke(null);

        check_link.CheckLinkURL("Terms of Use",
                xpath_terms + "1]",
                "https://secure-seriesone.dynamo-ny.com/terms-of-use", log);

        method.invoke(null);

        check_link.CheckLinkURL("Privacy Policy",
                xpath_terms + "2]",
                "https://secure-seriesone.dynamo-ny.com/privacy-policy", log);
    }
}
