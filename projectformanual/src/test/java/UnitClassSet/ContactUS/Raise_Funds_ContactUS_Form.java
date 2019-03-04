package UnitClassSet.ContactUS;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.Exceptions.NewException;
import SupportClasses.SetupClass.SetupClass;
import SupportClasses.Field;
import UnitClassSet.StaticPages.PagesURL;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Raise_Funds_ContactUS_Form {
    private Field field = new Field();
    private int seconds_for_wait;
    private String name;
    private String last_name;

    public void SeriesOne_FillInContactUSForm(int page_starts) throws Exception{
        if (page_starts == 1){PagesURL.FundsPage();}
        else if(page_starts == 2){PagesURL.BrokeronePage();}
        else if(page_starts == 3){PagesURL.ContactUSPage();}

//      fill in fields for creating SF lead
        Scanner reader = new Scanner(new File("src/test/java/UnitClassSet/ContactUS/lead_id.txt"));
        int id = reader.nextInt(); // id > unique number for email, that reads from the file and writes bigger on one.
        name = "AutoTest_Name" + Integer.toString(id);
        last_name = "AutoTest_LastName" + Integer.toString(id);

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("start_raise_firstName")));
        field.EnterValue("//input[@id='start_raise_firstName']",name);
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("start_raise_lastName")));
        field.EnterValue("//input[@id='start_raise_lastName']",last_name);
        String email_value = "prybehavtests+lead" + Integer.toString(id) + "@gmail.com";

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("start_raise_email")));
        field.EnterValue("//input[@id='start_raise_email']",email_value);
        id++;
        String str = Integer.toString(id);
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/java/UnitClassSet/ContactUS/lead_id.txt"));
        writer.write(str);
        writer.close();
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("start_raise_description")));
        field.EnterValue("//textarea[@id='start_raise_description']","test description");

        SetupClass.GetDriver().findElement(By.id("start_raise_submit")).click();

        SetupClass.GetDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("flash-messages-success")));
        Thread.sleep(300);

        SetupClass.GetDriver().findElement(By.xpath("//button[@data-dismiss='modal'][text()='OK']")).click();

        SalesForce_CheckingCreatedLead(email_value, name, last_name, page_starts);

        if(page_starts == 1){
            Offering_Creating(name,last_name, id-1);
        }
    }

    private void SalesForce_CheckingCreatedLead(String email_value, String name, String last_name, int lead_type) throws Exception{
        SetupClass.GetDriver().get("https://test.salesforce.com/?un=support@seriesone.com.qa&pw=Dynamo@2018");
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='146:0;p']")));
        Thread.sleep(1000);
        field.EnterValue("//input[@id='146:0;p']",email_value);
        SetupClass.GetDriver().findElement(By.xpath(("//input[@id='146:0;p']"))).sendKeys(Keys.ENTER);

        seconds_for_wait = 15; // *4 for every try to find created lead, have one minute time for expect creating.
        for (int second = 0; second < seconds_for_wait ; second++) {
            try {
                System.out.println(Integer.toString((second+1)*4) + " seconds passed for trying to find created lead");
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
        Thread.sleep(800);
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

    private void Offering_Creating(String name, String last_name, int id) throws Exception {
        SetupClass.GetDriver().findElement(By.xpath("//button[@title='Edit Dropbox Folder Url']")).click();
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class=' input'][@maxlength='255'][@type='url']")));
        field.EnterValue("//input[@class=' input'][@maxlength='255'][@type='url']","test_dropbox_url");

        Thread.sleep(1000);
        SetupClass.GetDriver().findElement(By.xpath("//button[@data-aura-class='uiButton forceActionButton'][@title='Save']")).click();
        SetupClass.GetDriver().findElement(By.xpath("//button[@data-aura-class='uiButton forceActionButton'][@title='Save']")).click();

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='slds-is-current slds-is-active slds-path__item runtime_sales_pathassistantPathAssistantTab'][@data-name='Contacted']")));
        SetupClass.GetDriver().findElement(By.xpath("//a[@title='Accept']")).click();

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=' label bBody'][text()='Next']")));
        Thread.sleep(2000);
        SetupClass.GetDriver().findElement(By.xpath("//span[@class=' label bBody'][text()='Next']")).click();

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=' label bBody'][text()='Finish']")));
        Thread.sleep(2000);
        SetupClass.GetDriver().findElement(By.xpath("//span[@class=' label bBody'][text()='Finish']")).click();

        SetupClass.GetDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-selected='true'][@title='Qualified']")));
        Thread.sleep(2000);

        // Comment calls error relates to js, don't know reason why this happen
        //SetupClass.GetDriver().findElement(By.xpath("//span[@class='slds-truncate'][text()='Accounts']")).click();
        JavascriptExecutor js = (JavascriptExecutor)SetupClass.GetDriver();
        WebElement element = SetupClass.GetDriver().findElement(By.xpath("//span[@class='slds-truncate'][text()='Accounts']"));
        js.executeScript("arguments[0].click();", element);

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='"+ name + " " + last_name + "']")));
        SetupClass.GetDriver().findElement(By.xpath("//a[@title='"+ name + " " + last_name + "']")).click();
        System.out.println("Account " + name + " " + last_name + " successfully created");
        String SF_Account_URL = SetupClass.GetDriver().getCurrentUrl();
        Thread.sleep(2000);

        // Fund America part of code ///////////////////////////////////////////////////////////////////////////////////

        SetupClass.GetDriver().get("https://my-sandbox.fundamerica.com");

        if (SetupClass.GetDriver().getCurrentUrl().equals("https://my-sandbox.fundamerica.com/#/login")){
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("login")));
            field.EnterValue("//input[@id='login']","avasylenko@dynamo-ny.com");
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("pass")));
            field.EnterValue("//input[@id='pass']","Aq1sw2de3");

            SetupClass.GetDriver().findElement(By.xpath("//button[text()='Login']")).click();
        }

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='topnav-offerings']")));
        SetupClass.GetDriver().findElement(By.xpath("//a[@class='topnav-offerings']")).click();

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Add New Offering']")));
        SetupClass.GetDriver().findElement(By.xpath("//button[@title='Add New Offering']")).click();

        if (!field.ExistElementOnThePage("//div[@class='modal-content']",3)){
            SetupClass.GetDriver().findElement(By.xpath("//button[@title='Add New Offering']")).click();
        }
        String offering_name = "test_OfferingName" + id;

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='fa-form-name']")));
        field.EnterValue("//input[@id='fa-form-name']",offering_name);
        SetupClass.GetDriver().findElement(By.xpath("//button[@ng-if='showNext()']")).click();
        Thread.sleep(1000);

        // FA fill in fields for offering //////////////////////////////////////////////////////////////////////////////
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("fa-form-issuer-name")));
        Thread.sleep(400);
        field.EnterValue("//input[@id='fa-form-issuer-name']",name + " " + last_name);

        int i = (int)(Math.random() * 6 + 1);
        SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"fa-form-issuer-company_type\"]/holder/label[" + i + "]")).click();

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("fa-form-issuer-tax_id_number")));
        field.EnterValue("//input[@id='fa-form-issuer-tax_id_number']","000000001");
        Thread.sleep(500);

        Select dropdown1 = new Select(SetupClass.GetDriver().findElement(By.id("fa-form-issuer-region_formed_in")));
        dropdown1.selectByVisibleText("Florida");
        Thread.sleep(500);

        Select dropdown2 = new Select(SetupClass.GetDriver().findElement(By.id("fa-form-issuer-year_founded")));
        dropdown2.selectByVisibleText("1997");
        Thread.sleep(500);

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("fa-form-issuer-contact_name")));
        field.EnterValue("//input[@id='fa-form-issuer-contact_name']",name + " " + last_name);

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("fa-form-issuer-email")));
        field.EnterValue("//input[@id='fa-form-issuer-email']","prybehavtests+fa_offering" + id + "@gmail.com");

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("fa-form-issuer-phone")));
        field.EnterValue("//input[@id='fa-form-issuer-phone']","000000000");

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("fa-form-issuer-street_address_1")));
        field.EnterValue("//input[@id='fa-form-issuer-street_address_1']","test_Street_Address");

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("fa-form-issuer-city")));
        field.EnterValue("//input[@id='fa-form-issuer-city']","test_City");
        Thread.sleep(500);

        Select dropdown3 = new Select(SetupClass.GetDriver().findElement(By.id("fa-form-issuer-region")));
        dropdown3.selectByVisibleText("Florida");
        Thread.sleep(500);

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("fa-form-issuer-postal_code")));
        field.EnterValue("//input[@id='fa-form-issuer-postal_code']","test_PostalCode");
        Thread.sleep(500);

        SetupClass.GetDriver().findElement(By.xpath("//button[@ng-if='showNext()']")).click();

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("fa-form-security-email")));
        SetupClass.GetDriver().findElement(By.xpath("//button[@ng-click='cancel()']")).click();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        SetupClass.GetDriver().get(SF_Account_URL);

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='CreateOffering']")));
        SetupClass.GetDriver().findElement(By.xpath("//button[@name='CreateOffering']")).click();

        seconds_for_wait = 5; // *4 for every try to find created lead, have one minute time for expect creating.
        for (int second = 0; second < seconds_for_wait ; second++) {
            try {
                Select dropdown4 = new Select(SetupClass.GetDriver().findElement(By.xpath("//select[@name='offeringname']")));
                dropdown4.selectByVisibleText(offering_name);
                break;
            }
            catch (Exception e) {
                Thread.sleep(3000);
                //SetupClass.GetDriver().findElement(By.xpath("//")).click();
            }
        }
        Thread.sleep(1000);
        SetupClass.GetDriver().findElement(By.xpath("//button[@class='slds-button slds-button_brand']")).click();

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Coming Soon']")));
        Thread.sleep(400);
        SetupClass.GetDriver().findElement(By.xpath("//button[text()='Coming Soon']")).click();

        SetupClass.GetDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Confirm']")));
        Thread.sleep(1000);
        SetupClass.GetDriver().findElement(By.xpath("//button[text()='Confirm']")).click();
        Thread.sleep(1000);

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Activate Investments']")));
        Thread.sleep(400);
        SetupClass.GetDriver().findElement(By.xpath("//button[text()='Activate Investments']")).click();
        Thread.sleep(1000);

        SetupClass.GetDriver().findElement(By.name("Active")).click();
        Thread.sleep(1000);

        SetupClass.GetDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='slds-modal slds-fade-in-open']")));
        SetupClass.GetDriver().findElement(By.xpath("//button[text()='Confirm']")).click();
        Thread.sleep(1000);

        SetupClass.GetDriver().get("https://secure-seriesone.dynamo-ny.com/deals");
        Thread.sleep(1000);

        if (!field.ExistElementOnThePage("//div[@class='deal-item-company-name f-family-Lato bold'][text()='"+ name + " " + last_name + "']", 4)){
            throw new NewAssertError("Offering does not creating on deal page!");
        }

        SetupClass.GetDriver().get(SF_Account_URL);
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.name("Unfunded")));
        Thread.sleep(1000);
        SetupClass.GetDriver().findElement(By.name("Unfunded")).click();
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Confirm']")));
        Thread.sleep(1000);
        SetupClass.GetDriver().findElement(By.xpath("//button[text()='Confirm']")).click();

        SetupClass.GetDriver().get("https://secure-seriesone.dynamo-ny.com/deals");
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='footer-logo']")));
        Thread.sleep(10000);
        SetupClass.GetDriver().navigate().refresh();

        if (field.ExistElementOnThePage("//div[@class='deal-item-company-name f-family-Lato bold'][text()='"+ name + " " + last_name + "']", 4)){
            throw new NewAssertError("Offering does not disappearing from deal page!");
        }
    }
}
