package UnitClassSet;

import SupportClasses.SetupClass.SetupClass;

public class PagesURL {

    public void MainPage(){ SetupClass.GetDriver().get("https://seriesone.dynamo-ny.com/"); }
    public void DealPage(){
        SetupClass.GetDriver().get("https://secure-seriesone.dynamo-ny.com/deals");
    }
    public void FundsPage(){ SetupClass.GetDriver().get("https://seriesone.dynamo-ny.com/start-raising-funds/"); }
    public void BrokeronePage(){
        SetupClass.GetDriver().get("https://seriesone.dynamo-ny.com/brokerone/");
    }
    public void AboutInvestingPage(){
        SetupClass.GetDriver().get("https://seriesone.dynamo-ny.com/learn/");
    }
    public void TokenOfferingsPage(){ SetupClass.GetDriver().get("https://seriesone.dynamo-ny.com/token-offering/"); }
    public void RegisterPage(){ SetupClass.GetDriver().get("https://secure-seriesone.dynamo-ny.com/register"); }
    public void LoginPage(){
        SetupClass.GetDriver().get("https://secure-seriesone.dynamo-ny.com/login");
    }
}
