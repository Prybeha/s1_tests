package UnitClassSet;

import SupportClasses.SetupClass.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Field {

    public void EnterValue(String locator, String value) throws Exception{
        Thread.sleep(200);
        SetupClass.GetDriver().findElement(By.xpath(locator)).clear();
        SetupClass.GetDriver().findElement(By.xpath(locator)).click();
        SetupClass.GetDriver().findElement(By.xpath(locator)).sendKeys(value);
    }

    public void EnterValueIfAvailable(String locator, String value) throws Exception{
        if (ExistElementOnThePage(locator, 2)){
            EnterValue(locator, value);
            //System.out.println("Enter " + value + " in the field.");
        }
//        else{
//            System.out.println("Field is disable.");
//        }
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
