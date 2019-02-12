package UnitClassSet;

import SupportClasses.SetupClass.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Field {

    public void EnterValue(String locator, String value) throws Exception{
        JavascriptExecutor jse = (JavascriptExecutor)SetupClass.GetDriver();
        Thread.sleep(200);

        jse.executeScript("window.scrollTo(5000,0)");
        SetupClass.GetDriver().findElement(By.xpath(locator)).clear();
        Thread.sleep(100);
        SetupClass.GetDriver().findElement(By.xpath(locator)).click();
        Thread.sleep(100);
        SetupClass.GetDriver().findElement(By.xpath(locator)).sendKeys(value);
    }

    public void EnterValueIfAvailable(String locator, String value) throws Exception{
        if (ExistElementOnThePage(locator, 2)){
            EnterValue(locator, value);
        }
    }

    public boolean ExistElementOnThePage(String locator, int wait_time){
        SetupClass.GetDriver().manage().timeouts().implicitlyWait(wait_time, TimeUnit.SECONDS);
        try {
            WebElement element = SetupClass.GetDriver().findElement(By.xpath(locator));
            SetupClass.GetDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return true;
        }
        catch(NoSuchElementException ex) {
            SetupClass.GetDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return false;
        }
    }
}
