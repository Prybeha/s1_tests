package UnitClassSet.Gmail_Mailbox;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Field;
import SupportClasses.SetupClass.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class EmailReceivingChecking_Registration {
    private Field field = new Field();
    private Gmail message = new Gmail();

    private void Gmail_CheckContent_for_Registration() throws Exception{
        Scanner reader = new Scanner(new File("src/test/java/UnitClassSet/Registration/id.txt"));
        int id = reader.nextInt();
        String email = "prybehavtests+autotest" + Integer.toString(id-1) + "@gmail.com";
        String name = "test_UserName";

        Thread.sleep(5000);
        SetupClass.GetDriver().navigate().refresh();

        List<WebElement> quantity = SetupClass.GetDriver().findElements(By.xpath("//*[@class='bog']/span"));
        System.out.println("Quantity of received emails: " + quantity.size());

        for (int i = 0; i < quantity.size(); i++){
            System.out.println(Integer.toString(i + 1) + " : " + quantity.get(i).getText());
            if (quantity.get(i).getText().equals("Sandbox: Welcome, please confirm your email")){
                SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(quantity.get(i)));
                Thread.sleep(500);
                quantity.get(i).click();
                Thread.sleep(1000);

                if (!SetupClass.GetDriver().findElement(By.xpath("//span[@dir='ltr'][@class='g2']")).getText().equals(email)){
                    System.out.println("Message with wrong email was removed " + SetupClass.GetDriver().findElement(By.xpath("//span[@dir='ltr'][@class='g2']")).getText());
                    message.Gmail_DeleteEmailMessage();
                }
                else {
                    if (!field.ExistElementOnThePage("//img[@alt='seriesOne logo']", 2)) {
                        throw new NewAssertError("logo is absent in email");
                    }

                    if (!SetupClass.GetDriver().findElement(By.xpath("//h2[@style='color:#42454e;font-size:20px;line-height:24px']")).getText().equals("Welcome, please confirm your email")) {
                        throw new NewAssertError("Description does not match with required");
                    }

                    if (!SetupClass.GetDriver().findElement(By.xpath("//td[@style=\"font-family:'Roboto',Arial,sans-serif;font-size:14px;color:#6c757d;font-weight:300;line-height:20px\"]")).getText().
                            contains("Thank you for registering, please confirm your email by clicking the button below. After confirming, you will be able to view investment opportunities and make investments." +
                                    "If you have any questions during your journey please email us at support@seriesone.com or call (844) 737-4371 Ext #1.")){
                        throw new NewAssertError("wrong text above confirm button");
                    }

                    if (!field.ExistElementOnThePage("//a[@style=\"font-family:'Roboto',Arial,sans-serif;color:#ffffff;text-decoration:none;letter-spacing:1px\"]",1)){
                        throw new NewAssertError("button does not appears");
                    }

                    if (!SetupClass.GetDriver().findElement(By.xpath("//h2[@style='font-size:22px;line-height:26px;margin-bottom:0;padding-bottom:0;color:#878787;color:#019cfe']")).getText().equals("About seriesOne")){
                        throw new NewAssertError("footer description does not match");
                    }

                    if (!SetupClass.GetDriver().findElements(By.xpath("//p[@style=\"font-size:14px;line-height:20px;color:#878787;font-family:'Roboto',Arial,sans-serif;font-weight:200;padding-top:0\"]")).get(0).getText().
                            equals("seriesOne is a leading blockchain based FinTech company formed by industry veterans with decades of expertise across technology, investment banking, venture capital, and financial compliance. " +
                                    "The firm provides strategic counsel, regulatory compliance, fundraising infrastructure and access to a proprietary network of investors worldwide.")){
                        throw new NewAssertError("wrong text1 under confirm button");
                    }

                    if (!SetupClass.GetDriver().findElements(By.xpath("//p[@style=\"font-size:14px;line-height:20px;color:#878787;font-family:'Roboto',Arial,sans-serif;font-weight:200;padding-top:0\"]")).get(1).getText().
                            equals("All seriesOne services are conducted via US Securities and Exchange Commission (\"SEC\") exemptions such as Regulation D, Regulation Crowdfunding, " +
                                    "and Regulation A+, enabling companies to register, market, and escrow fundraising initiatives.")){
                        throw new NewAssertError("wrong text2 under confirm button");
                    }

                    System.out.println("Received message was removed!");
                    message.Gmail_DeleteEmailMessage();
                }
                // click return button
                Thread.sleep(1000);
                i--;
                quantity = SetupClass.GetDriver().findElements(By.xpath("//*[@class='bog']/span"));
            }
        }
    }

    public void Gmail_TestMailForRegistration() throws Exception{
        Gmail account = new Gmail();
        account.Gmail_Login("prybehavtests@gmail.com","Aq1sw2de3!");
        Gmail_CheckContent_for_Registration();
    }
}
