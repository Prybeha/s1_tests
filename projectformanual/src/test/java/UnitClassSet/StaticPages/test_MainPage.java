package UnitClassSet.StaticPages;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.SetupClass.SetupClass;
import UnitClassSet.PagesURL;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class test_MainPage extends SetupClass{
    private PagesURL url = new PagesURL();

    @Test
    public void MainPage_Title(){
        url.MainPage();

        if(!SetupClass.GetDriver().getTitle().equals("Token offerings, debt and equity investment platform | seriesOne")){
            throw new NewAssertError("Title on the main page does not match with required");
        }
    }

    @Test
    public void MainPage_UpperSeriesOneLogoButton(){
        url.MainPage();

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//img[@src='https://secure-seriesone.dynamo-ny.com/media/cache/seriesone_logo/bundles/frontend/images/logos/seriesOne-white.png']")));
        SetupClass.GetDriver().findElement(By.xpath("//img[@src='https://secure-seriesone.dynamo-ny.com/media/cache/seriesone_logo/bundles/frontend/images/logos/seriesOne-white.png']")).click();
        if(!SetupClass.GetDriver().getCurrentUrl().equals("https://seriesone.dynamo-ny.com/")){
            throw new NewAssertError("UpperSeriesOneLogoButton goes on the wrong page");
        }
    }

    @Test
    public void MainPage_HeaderButtons() throws Exception{
        url.MainPage();
        String[] expected_urls = {"https://secure-seriesone.dynamo-ny.com/deals", "https://seriesone.dynamo-ny.com/start-raising-funds/",
                "https://seriesone.dynamo-ny.com/brokerone/", "https://seriesone.dynamo-ny.com/learn/", "https://seriesone.dynamo-ny.com/digital-security-offerings/"};

        for(int i = 1;i <= expected_urls.length; i++) {
            System.out.println(i);
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[1]/header/div[2]/div/ul/li[" + i + "]/a")));
            SetupClass.GetDriver().findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/ul/li[" + i + "]/a")).click();
            Thread.sleep(500);
            if (!SetupClass.GetDriver().getCurrentUrl().equals(expected_urls[i-1])) {
                throw new NewAssertError("Header button goes on the wrong page: " + SetupClass.GetDriver().getCurrentUrl());
            }
            url.MainPage();
        }
    }

    @Test
    public void MainPage(){

    }
}
