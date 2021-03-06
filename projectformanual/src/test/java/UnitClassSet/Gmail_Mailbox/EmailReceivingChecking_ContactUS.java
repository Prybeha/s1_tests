package UnitClassSet.Gmail_Mailbox;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Field;
import SupportClasses.SetupClass.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class EmailReceivingChecking_ContactUS {
    private Field field = new Field();
    private Gmail message = new Gmail();

    private void Gmail_CheckContent_for_LeadCreation(int request_page) throws Exception{
        Scanner reader = new Scanner(new File("src/test/java/UnitClassSet/ContactUS/lead_id.txt"));
        int id = reader.nextInt();
        String email = "prybehavtests+lead" + Integer.toString(id - 1) + "@gmail.com";
        String name = "AutoTest_Name" + Integer.toString(id - 1);

        Thread.sleep(5000);
        SetupClass.GetDriver().navigate().refresh();

        List<WebElement> quantity = SetupClass.GetDriver().findElements(By.xpath("//*[@class='bog']/span"));
        System.out.println("Quantity of received emails: " + quantity.size());

        for (int i = 0; i < quantity.size(); i++){
            System.out.println(Integer.toString(i + 1) + " : " + quantity.get(i).getText());

            if (quantity.get(i).getText().equals("Sandbox: SeriesOne.com: Contact Received") ||
                    quantity.get(i).getText().equals("Sandbox: SeriesOne.com: Contact received") ){
                quantity.get(i).click();
                Thread.sleep(1000);

                if (!SetupClass.GetDriver().findElement(By.xpath("//span[@dir='ltr'][@class='g2']")).getText().equals(email)){
                    //System.out.println(SetupClass.GetDriver().findElement(By.xpath("//span[@dir='ltr'][@class='g2']")).getText());
                    System.out.println("Message was removed");
                    message.Gmail_DeleteEmailMessage();
                }
                else {
                    if (!field.ExistElementOnThePage("//img[@alt='seriesOne logo']", 2)) {
                        throw new NewAssertError("SeriesOne logo is absent in email");
                    }

                    if (!SetupClass.GetDriver().findElement(By.xpath("//h2[@style='color:#42454e;font-size:20px;line-height:24px']")).getText().equals("Contact received")) {
                        throw new NewAssertError("Description does not match with required");
                    }

                    CheckDifferentContentInEmail(name, request_page);

                    if (!SetupClass.GetDriver().findElement(By.xpath("//h2[@style='font-size:22px;line-height:26px;margin-bottom:0;padding-bottom:0;color:#878787;color:#019cfe']"))
                            .getText().equals("About seriesOne")){
                        throw new NewAssertError("Footer description does not match with required");
                    }

                    if (!SetupClass.GetDriver().findElements(By.xpath("//p[@style=\"font-size:14px;line-height:20px;color:#878787;font-family:'Roboto',Arial,sans-serif;font-weight:200;padding-top:0\"]")).get(0).getText().
                            equals("seriesOne is a leading blockchain based FinTech company formed by industry veterans with decades of expertise across technology, investment banking, venture capital, and financial compliance. " +
                                    "The firm provides strategic counsel, regulatory compliance, fundraising infrastructure and access to a proprietary network of investors worldwide.")){
                        throw new NewAssertError("Wrong text1 under confirm button");
                    }

                    if (!SetupClass.GetDriver().findElements(By.xpath("//p[@style=\"font-size:14px;line-height:20px;color:#878787;font-family:'Roboto',Arial,sans-serif;font-weight:200;padding-top:0\"]")).get(1).getText().
                            equals("All seriesOne services are conducted via US Securities and Exchange Commission (\"SEC\") exemptions such as Regulation D, Regulation Crowdfunding, " +
                                    "and Regulation A+, enabling companies to register, market, and escrow fundraising initiatives.")){
                        throw new NewAssertError("Wrong text2 under confirm button");
                    }

                    System.out.println("Message was removed");
                    message.Gmail_DeleteEmailMessage();
                }
                // click return button
                Thread.sleep(1000);
                i--;
                quantity = SetupClass.GetDriver().findElements(By.xpath("//*[@class='bog']/span"));
            }
        }
    }

    private void CheckDifferentContentInEmail(String name, int request_page){
        if (request_page == 1) {
            if (!SetupClass.GetDriver().findElement(By.xpath("//*[@style=\"font-family:'Roboto',Arial,sans-serif;font-size:14px;color:#6c757d;font-weight:300;line-height:20px\"]/p"))
                    .getText().equals("Hi " + name + ",\n" +
                            "\n" +
                            "Thank you for contacting us to explore raising funds for your company through seriesOne. An Account Manager will reach out to you shortly to get the process started." +
                            "If you have any questions during your journey please email us at support@seriesone.com or call (844) 737-4371 Ext #1.")) {
                throw new NewAssertError("Content in message does not match with required");
            }

            if (!field.ExistElementOnThePage("//a[@style=\"font-family:'Roboto',Arial,sans-serif;color:#ffffff;text-decoration:none;letter-spacing:1px\"][text()='Confirm']",2)){
                throw new NewAssertError("Confirm button is absent");
            }
        }

        else if (request_page == 2) {
            if (!SetupClass.GetDriver().findElement(By.xpath("//*[@style=\"font-family:'Roboto',Arial,sans-serif;font-size:14px;color:#6c757d;font-weight:300;line-height:20px\"]/p"))
                    .getText().equals("Hi " + name + ",\n" +
                            "\n" +
                            "Thank you for contacting us to explore licensing seriesOne to market your financing deals. An Account Manager will reach out to you shortly to discuss next steps." +
                            "If you have any questions during your journey please email us at support@seriesone.com or call (844) 737-4371 Ext #1.")) {
                throw new NewAssertError("Content in message does not match with required");
            }
        }

        else if (request_page == 3) {
            if (!SetupClass.GetDriver().findElement(By.xpath("//*[@style=\"font-family:'Roboto',Arial,sans-serif;font-size:14px;color:#6c757d;font-weight:300;line-height:20px\"]/p"))
                    .getText().equals("Hi " + name + ",\n" +
                            "\n" +
                            "Thanks for getting in touch. We will review your message and respond shortly." +
                            "If you have any questions during your journey please email us at support@seriesone.com or call (844) 737-4371 Ext #1.")) {
                throw new NewAssertError("Content in message does not match with required");
            }
        }
    }

    public void Gmail_TestMailForLeadCreation(int request_page) throws Exception{
        Gmail account = new Gmail();
        account.Gmail_Login("prybehavtests@gmail.com","Aq1sw2de3!");
        Gmail_CheckContent_for_LeadCreation(request_page);
    }
}
