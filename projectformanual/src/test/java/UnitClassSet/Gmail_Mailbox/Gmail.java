package UnitClassSet.Gmail_Mailbox;

import SupportClasses.Field;
import SupportClasses.SetupClass.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Gmail {
    private Field field = new Field();

    public void Gmail_Login(String email, String password) throws Exception{
        // email for test 'prybehavtests@gmail.com'
        SetupClass.GetDriver().get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");

//        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-g-label='Sign in']")));
//        SetupClass.GetDriver().findElement(By.xpath("//a[@data-g-label='Sign in']")).click();

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='email']")));
        Thread.sleep(500);
        field.EnterValue("//input[@type='email']",email);

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='RveJvd snByac']")));
        SetupClass.GetDriver().findElement(By.xpath("//span[@class='RveJvd snByac']")).click();

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']")));
        Thread.sleep(500);
        field.EnterValue("//input[@type='password']",password);

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='RveJvd snByac']")));
        SetupClass.GetDriver().findElement(By.xpath("//span[@class='RveJvd snByac']")).click();

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='https://ssl.gstatic.com/ui/v1/icons/mail/rfr/logo_gmail_lockup_default_1x.png']")));
    }

    public void Gmail_DeleteEmailMessage() throws Exception{
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div/div[2]/div[3]")));
        Thread.sleep(1000);
        SetupClass.GetDriver().findElement(By.xpath("//*[@id=\":4\"]/div[2]/div[1]/div/div[2]/div[3]")).click();
        Thread.sleep(1000);

        // Check is it message really removed
        SetupClass.GetDriverWait().until(ExpectedConditions.urlMatches("https://mail.google.com/mail/u/0/#inbox"));
//        if(field.ExistElementOnThePage("//span[@email='account@seriesone.com']",2)){
//            System.out.println("Still exist emails from account@seriesone.com");
//        }
    }
}
