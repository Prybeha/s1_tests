package UnitClassSet.ContactUS;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Exceptions.NewException;
import SupportClasses.SetupClass.SetupClass;
import UnitClassSet.Field;
import UnitClassSet.PagesURL;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Raise_Funds_ContactUS_Form {
    private Field field = new Field();
    private PagesURL url = new PagesURL();
    private int seconds_for_wait;
    private String name = "AutoTest_Name";
    private String last_name = "AutoTest_LastName";
    private String email;
    public void SeriesOne_FillInContactUSForm(int page_starts) throws Exception{
        if (page_starts == 1){url.FundsPage();}
        else if(page_starts == 2){url.BrokeronePage();}
        else if(page_starts == 3){url.ContactUSPage();}

        // fill in fields for creating SF lead
        field.EnterValue("//input[@id='start_raise_firstName']",name);
        field.EnterValue("//input[@id='start_raise_lastName']",last_name);
        Scanner reader = new Scanner(new File("src/test/java/UnitClassSet/ContactUS/lead_id.txt"));
        int id = reader.nextInt(); // id > unique number for email, that reads from the file and writes bigger on one.
        String email_value = "prybehav+lead_raise_funds_" + id + "@gmail.com";

        field.EnterValue("//input[@id='start_raise_email']",email_value);
        id++;
        String str = Integer.toString(id);
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/java/UnitClassSet/ContactUS/lead_id.txt"));
        writer.write(str);
        writer.close();
        field.EnterValue("//textarea[@id='start_raise_description']","test description");

        SetupClass.GetDriver().findElement(By.id("start_raise_submit")).click();

        SetupClass.GetDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("flash-messages-success")));
        Thread.sleep(300);

        SetupClass.GetDriver().findElement(By.xpath("//button[@data-dismiss='modal'][text()='OK']")).click();

        SalesForce_CheckingCreatedLead(email_value, name, last_name, page_starts);
    }

    private void SalesForce_CheckingCreatedLead(String email_value, String name, String last_name, int lead_type) throws Exception{
        SetupClass.GetDriver().get("https://test.salesforce.com/?un=support@seriesone.com.qa&pw=Dynamo@2018");
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='146:0;p']")));
        field.EnterValue("//input[@id='146:0;p']",email_value);
        SetupClass.GetDriver().findElement(By.xpath(("//input[@id='146:0;p']"))).sendKeys(Keys.ENTER);

        seconds_for_wait = 15; // *4 for every try to find created lead, have one minute time for expect creating.
        for (int second = 0; second < seconds_for_wait ; second++) {
            try {
                System.out.println(Integer.toString((second+1)*3) + " seconds passed for trying to find created lead");
                if (field.ExistElementOnThePage("//a[@class='slds-page-header__title slds-text-color--default slds-show--inline-block uiOutputURL'][text()='Leads']",3)){
                    if(SetupClass.GetDriver().findElement(By.xpath("//a[@class='slds-truncate outputLookupLink slds-truncate forceOutputLookup']")).getAttribute("title").equals(name + " " + last_name)) {
                        break;
                    }
                }
                throw new NewException("Not find");
            }
            catch (Exception e) {
                Thread.sleep(1000);
                SetupClass.GetDriver().findElement(By.xpath("//input[@id='146:0;p']")).click();
                SetupClass.GetDriver().findElement(By.xpath("//input[@id='146:0;p']")).sendKeys(Keys.ENTER);
            }
        }

        SetupClass.GetDriver().findElement(By.xpath("//a[@class='slds-truncate outputLookupLink slds-truncate forceOutputLookup']")).click();
        System.out.println("Lead creation was successful");

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='title'][text()='Details']")));
        Thread.sleep(400);
        SetupClass.GetDriver().findElement(By.xpath("//span[@class='title'][text()='Details']")).click();

        String id_section_content = SetupClass.GetDriver().findElement(By.xpath("//div[@class='full forcePageBlock forceRecordLayout']")).getAttribute("data-aura-rendered-by");

        id_section_content = id_section_content.substring(id_section_content.indexOf(":"),id_section_content.indexOf(";"));
        //System.out.println(id_section_content);

        WebElement lead_type_field = SetupClass.GetDriver().findElement(By.xpath("//span[@data-aura-rendered-by='172"+ id_section_content +";a']"));
        if(lead_type == 1){
            if(!lead_type_field.getText().equals("Issuer")){
                throw new NewAssertError("Lead type (" + lead_type_field.getText() + ") don't match with required Issuer");
            }
        }
        else if(lead_type == 2){
            if(!lead_type_field.getText().equals("Broker")){
                throw new NewAssertError("Lead type (" + lead_type_field.getText() + ") don't match with required Broker");
            }
        }
        else if(lead_type == 3){
            if(!lead_type_field.getText().equals("ContactUs")){
                throw new NewAssertError("Lead type (" + lead_type_field.getText() + ") don't match with required ContactUs");
            }
        }
        else{
            throw new NewAssertError("Error value in the lead type field: " + lead_type_field);
        }
    }
}
