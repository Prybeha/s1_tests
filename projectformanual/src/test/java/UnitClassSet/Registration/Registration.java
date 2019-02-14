package UnitClassSet.Registration;

import DB.db_registration_first_step;
import DB.db_registration_second_step;
import DB.db_registration_third_step_E;
import DB.db_registration_third_step_general;
import SupportClasses.AllureFunc.LogUtil;
import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.SetupClass.SetupClass;
import UnitClassSet.CheckAppearingError.AppearingError;
import UnitClassSet.Field;
import UnitClassSet.Login.Login;
import UnitClassSet.Maintenance.Maintenance;
import UnitClassSet.PagesURL;
import UnitClassSet.Switchers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.*;
import java.util.Scanner;

public class Registration {
    private Field field = new Field();
    private Switchers switcher = new Switchers();
    private AppearingError error_appears = new AppearingError();
    public void Register(int user_type) throws Exception{
        SetupClass.GetDriver().get("https://seriesone.dynamo-ny.com/");

        Maintenance maintenance = new Maintenance();
        if (maintenance.MaintenancePageCheck()){
            LogUtil.log("Maintenance mode is on!");
            return;
        }

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://secure-seriesone.dynamo-ny.com/register']")));
        Thread.sleep(500);
        SetupClass.GetDriver().findElement(By.xpath("//a[@href='https://secure-seriesone.dynamo-ny.com/register']")).click();

        int current_step = 0;
        LogUtil.log("Registration starts");
        while (current_step != 4) {
            if(current_step != 3){
                SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='footer-logo']")));
            }
            Thread.sleep(1000);

            if(CheckRegistrationStep(current_step) == 1) {
                current_step = 1;

                System.out.println("First step");
                LogUtil.log("First step");
                FirstStep();
            }
            else if(CheckRegistrationStep(current_step) == 2){
                current_step = 2;

                System.out.println("Second step");
                LogUtil.log("Second step");
                SecondStep(user_type);
            }
            else if(CheckRegistrationStep(current_step) == 3){
                current_step = 3;

                System.out.println("Third step");
                LogUtil.log("Third step");
                SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='footer-logo']")));
                ThirdStep(user_type);
            }
            else if(CheckRegistrationStep(current_step) == 4){
                current_step = 4;// after success third step it will load home page. 4 means check if we on the home page.
                System.out.println("Registration finished");
                LogUtil.log("Registration finished");

                SetupClass.GetDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("flash-messages-success")));
                SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));

                SetupClass.GetDriver().findElement(By.xpath("//button[text()='OK']")).click();
            }
            else{
                System.out.println("Something goes wrong. You are go away from registration steps");
                return;
            }
        }
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://secure-seriesone.dynamo-ny.com/logout']")));
        Thread.sleep(300);
        SetupClass.GetDriver().findElement(By.xpath("//a[@href='https://secure-seriesone.dynamo-ny.com/logout']")).click();

        Check_Info_In_MyAccount(user_type);
    }

    private int CheckRegistrationStep(int prev_step) throws Exception{
        int wait_time = 5;

        if(prev_step != 3){
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='footer-logo']")));
        }
        else{
            Thread.sleep(1000);
        }

        Thread.sleep(1000);
        if(prev_step == 0) {
            if (field.ExistElementOnThePage("//li[@class='active current' and @data-step='investor_information']", wait_time)) { return 1; }
            else { return 0; }
        }
        else if(prev_step == 1) {
            if (field.ExistElementOnThePage("//li[@class='active current' and @data-step='investment_amount']", wait_time)) { return 2; }
            else if (field.ExistElementOnThePage("//li[@class='active current' and @data-step='investor_information']", wait_time)) { return 1; }
            else { return 0; }
        }
        else if (prev_step == 2) {
            if (field.ExistElementOnThePage("//li[@class='active current' and @data-step='agreement']", wait_time)) { return 3; }
            else if (field.ExistElementOnThePage("//li[@class='active current' and @data-step='investment_amount']", wait_time)) { return 2; }
            else if (field.ExistElementOnThePage("//li[@class='active current' and @data-step='investor_information']", wait_time)) { return 1; }
            else { return 0; }
        }
        else if(prev_step == 3) {
            if (field.ExistElementOnThePage("//div[@id='flash-messages-success']",7)){ return 4; }
            else if (field.ExistElementOnThePage("//li[@class='active current' and @data-step='agreement']", wait_time)) { return 3; }
            else if (field.ExistElementOnThePage("//li[@class='active current' and @data-step='investment_amount']", wait_time)) { return 2; }
            else { return 0; }
        }
        // Loop doesn't start if step become fourth, because there is no choice where go.
        return 0;
    }

    private int CheckEmailExist(int id) throws Exception{
        JavascriptExecutor jse = (JavascriptExecutor)SetupClass.GetDriver();
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("registration_investor_email")));
        field.EnterValue("//input[@id='registration_investor_email']","prybehav+autotest" + id + "@gmail.com");
        SetupClass.GetDriver().findElement(By.id("next")).click();
        Thread.sleep(500);

        jse.executeScript("window.scrollTo(5000,0)");
        while(field.ExistElementOnThePage("//*[@id=\"ajaxContent\"]/div[3]/div[2]/div/form/div[1]/div/div/ul/li",3)){
            id++;

            field.EnterValue("//input[@id='registration_investor_email']","prybehav+autotest" + id + "@gmail.com");
            SetupClass.GetDriver().findElement(By.id("next")).click();
            Thread.sleep(500);
            jse.executeScript("window.scrollTo(5000,0)");
        }
        return id;
    }

    private void FirstStep() throws Exception{
        Scanner reader = new Scanner(new File("src/test/java/UnitClassSet/Registration/id.txt"));
        int id = reader.nextInt(); // id > unique number for email, that reads from the file and writes bigger on one.

        int id_unique = CheckEmailExist(id);

        error_appears.EmailErrorCheck(db_registration_first_step.FirstStep_Email_Negative(),true);
        error_appears.EmailErrorCheck(db_registration_first_step.FirstStep_Email_Positive("prybehav+autotest" + id_unique + "@gmail.com"),false);
        id_unique++;
        String str = Integer.toString(id_unique);
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/java/UnitClassSet/Registration/id.txt"));
        writer.write(str);
        writer.close();

        error_appears.CallErrorCheck(db_registration_first_step.FirstStep_UserName_Negative(), db_registration_first_step.FirstStep_UserName_Positive(),
                "//input[@id='registration_investor_firstName']",
                "//*[@id=\"ajaxContent\"]/div[3]/div[2]/div/form/div[2]/div[1]/div/ul/li"); // for positive TC expected result = false => we expect that error does not appears

        error_appears.CallErrorCheck(db_registration_first_step.FirstStep_UserLastName_Negative(),db_registration_first_step.FirstStep_UserLastName_Positive(),
                "//input[@id='registration_investor_lastName']",
                "//*[@id=\"ajaxContent\"]/div[3]/div[2]/div/form/div[2]/div[2]/div/ul/li");

        SetupClass.GetDriver().findElement(By.xpath("//input[@id='registration_investor_firstName']")).clear();

        Thread.sleep(100);
        error_appears.CallErrorCheck(db_registration_first_step.FirstStep_Pass_Negative(),db_registration_first_step.FirstStep_Pass_Positive(),
                "//input[@id='registration_investor_password']",
                "//*[@id=\"ajaxContent\"]/div[3]/div[2]/div/form/div[4]/div/div/ul/li");

        Thread.sleep(200);
        field.EnterValue("//input[@id='registration_investor_firstName']",
                db_registration_first_step.FirstStep_UserName_Positive()[db_registration_first_step.FirstStep_UserName_Positive().length-1]);
        field.EnterValue("//input[@id='registration_investor_password']",
                db_registration_first_step.FirstStep_Pass_Positive()[db_registration_first_step.FirstStep_Pass_Positive().length-1]);// foreach field will be match last in db file

        SetupClass.GetDriver().findElement(By.id("next")).click();
    }

    private void SecondStep_ChooseAccountType(int user_type) throws Exception{
        Thread.sleep(1000);

        //SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"ajaxContent\"]/div[3]/div[1]/div/form/div[2]/div[2]/div/img[2]")).click();
        if (user_type == 1 || user_type == 2 || user_type == 5) {
            if (field.ExistElementOnThePage("//div[@class='col-xs-6 center account-type-icon active-icon' and @data-account-type='entity']",1)) {
                SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ajaxContent\"]/div[3]/div[1]/div/form/div[2]/div[1]/div/img[2]")));
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"ajaxContent\"]/div[3]/div[1]/div/form/div[2]/div[1]/div/img[2]")).click();
                Thread.sleep(200);
            }
            if (user_type == 1 || user_type == 5){
                switcher.SwitcherManage("registration_second_step_fwUSresident",
                        "//*[@id=\"ajaxContent\"]/div[3]/div[1]/div/form/div[3]/div/div/div/label/div[1]/div/label[2]",true);
                if (user_type == 1){
                    switcher.SwitcherManage("registration_second_step_fwAccreditedInvestor",
                            "//*[@id=\"calculator\"]/div/div/div/label/div[1]/div/label[2]",true);
                }
                else{
                    switcher.SwitcherManage("registration_second_step_fwAccreditedInvestor",
                            "//*[@id=\"calculator\"]/div/div/div/label/div[1]/div/label[1]",false);
                }
            }
            else{
                switcher.SwitcherManage("registration_second_step_fwUSresident",
                        "//*[@id=\"ajaxContent\"]/div[3]/div[1]/div/form/div[3]/div/div/div/label/div[1]/div/label[1]",false);
            }
        }
        else if(user_type == 3 || user_type == 4){
            if (field.ExistElementOnThePage("//div[@class='col-xs-6 center account-type-icon active-icon' and @data-account-type='individual']",1)) {
                SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ajaxContent\"]/div[3]/div[1]/div/form/div[2]/div[2]/div/img[2]")));
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"ajaxContent\"]/div[3]/div[1]/div/form/div[2]/div[2]/div/img[2]")).click();
            }
            if (user_type == 3){
                switcher.SwitcherManage("registration_second_step_fwUSresident",
                        "//*[@id=\"ajaxContent\"]/div[3]/div[1]/div/form/div[3]/div/div/div/label/div[1]/div/label[2]",true);
            }
            else {
                switcher.SwitcherManage("registration_second_step_fwUSresident",
                        "//*[@id=\"ajaxContent\"]/div[3]/div[1]/div/form/div[3]/div/div/div/label/div[1]/div/label[1]",false);
            }
        }
    }

    private void SecondStep(int user_type) throws Exception{
        SecondStep_ChooseAccountType(user_type);

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='footer-logo']")));
        //Thread.sleep(1000);
        if (user_type == 1){
            switcher.SwitcherManage("registration_second_step_fwIsQualifiedNetWorth",
                    "//label[@for='registration_second_step_fwIsQualifiedNetWorth']",true);// enter true or false value that you want
            switcher.SwitcherManage("registration_second_step_fwIsQualifiedIncome",
                    "//label[@for='registration_second_step_fwIsQualifiedIncome']",true);// enter true or false value that you want
        }
        else if (user_type == 2){
            // no asked data on this type of users
        }
        else if (user_type == 3){
            int i = (int)(Math.random() * 4 + 0);
            SetupClass.GetDriver().findElement(By.xpath("//label[@for='registration_second_step_account_fwReasonForAccreditedStatus_" + i + "']")).click();
        }
        else if (user_type == 4){
            // no asked data on this type of users
        }
        else if (user_type == 5){
            String[] values_for_error_appearing = {"","0"};
            error_appears.AppearingErrorCheck(values_for_error_appearing,"//input[@id='registration_second_step_fwWhatNetWorth']",
                    "//*[@id=\"ajaxContent\"]/div[3]/div[1]/div/form/div[5]/div/div[2]/div[1]/div[2]/ul/li",true);
            error_appears.AppearingErrorCheck(values_for_error_appearing,"//input[@id='registration_second_step_fwWhatAnnualIncome']",
                    "//*[@id=\"ajaxContent\"]/div[3]/div[1]/div/form/div[6]/div/div[2]/div[1]/div[2]/ul/li",true);


            error_appears.EnterAndCheckValueForMoney(db_registration_second_step.SecondStep_NetWorth_Negative(),"//input[@id='registration_second_step_fwWhatNetWorth']",
                    "//input[@name='registration_second_step[fwWhatNetWorth]']",false);
            error_appears.EnterAndCheckValueForMoney(db_registration_second_step.SecondStep_NetWorth_Positive(),"//input[@id='registration_second_step_fwWhatNetWorth']",
                    "//input[@name='registration_second_step[fwWhatNetWorth]']",true);

            error_appears.EnterAndCheckValueForMoney(db_registration_second_step.SecondStep_Income_Negative(),"//input[@id='registration_second_step_fwWhatAnnualIncome']",
                    "//input[@name='registration_second_step[fwWhatAnnualIncome]']",false);
            error_appears.EnterAndCheckValueForMoney(db_registration_second_step.SecondStep_Income_Positive(),"//input[@id='registration_second_step_fwWhatAnnualIncome']",
                    "//input[@name='registration_second_step[fwWhatAnnualIncome]']",true);
        }

        SetupClass.GetDriver().findElement(By.id("next")).click();
    }

    private void ThirdStep(int user_type) throws Exception{

        if (user_type == 1 || user_type == 2 || user_type == 5 ){
            Third_Step_For_Individual(user_type);
        }
        else if(user_type == 3 || user_type == 4){
            Third_Step_For_Entity(user_type);
        }
        SetupClass.GetDriver().findElement(By.id("registration_second_step_mailingCity")).clear();

        Third_Step_Switchers(user_type);

        field.EnterValue("//input[@id='registration_second_step_mailingCity']",
                db_registration_third_step_general.ThirdStep_City_Positive()[db_registration_third_step_general.ThirdStep_City_Positive().length-1]);

        SetupClass.GetDriver().findElement(By.id("next")).click();
    }

    private void Third_Step_For_Individual(int user_type) throws Exception{
        String[] empty_value = {""};
        JavascriptExecutor jse = (JavascriptExecutor)SetupClass.GetDriver();

        SetupClass.GetDriver().findElement(By.id("registration_second_step_fwIsLegalNameThesame")).click();
        if (!SetupClass.GetDriver().findElement(By.id("registration_second_step_fwIsLegalNameThesame")).isSelected()){
            error_appears.CallErrorCheck(db_registration_third_step_general.ThirdStep_UserName_Negative(), db_registration_third_step_general.ThirdStep_UserName_Positive(),
                    "//input[@id='registration_second_step_fwLegalFirstName']",
                    "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[3]/div[1]/div/ul/li");

            error_appears.CallErrorCheck(db_registration_third_step_general.ThirdStep_UserLastName_Negative(),db_registration_third_step_general.ThirdStep_UserLastName_Positive(),
                    "//input[@id='registration_second_step_fwLegalLastName']",
                    "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[3]/div[2]/div/ul/li");
        }
        error_appears.CallErrorCheck(db_registration_third_step_general.ThirdStep_Address_Negative(),db_registration_third_step_general.ThirdStep_Address_Positive(),
                "//input[@id='registration_second_step_mailingStreet']",
                "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[5]/div/div/ul/li");

        error_appears.CallErrorCheck(db_registration_third_step_general.ThirdStep_City_Negative(),db_registration_third_step_general.ThirdStep_City_Positive(),
                "//input[@id='registration_second_step_mailingCity']",
                "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[7]/div[1]/div/ul/li");

        if(user_type == 1 || user_type == 5) {
            error_appears.EnterAndCheckValuePhone(db_registration_third_step_general.ThirdStep_Phone_Negative(),
                    "//input[@id='registration_second_step_phone']",false);
            error_appears.EnterAndCheckValuePhone(db_registration_third_step_general.ThirdStep_Phone_Positive(),
                    "//input[@id='registration_second_step_phone']",true);

            Thread.sleep(200);
            Select dropdown = new Select(SetupClass.GetDriver().findElement(By.id("registration_second_step_mailingStateCode")));
            dropdown.selectByVisibleText("Connecticut");
            error_appears.CallErrorCheck(db_registration_third_step_general.ThirdStep_ZipCode_Negative(), db_registration_third_step_general.ThirdStep_ZipCode_Positive(),
                    "//input[@id='registration_second_step_mailingPostalCode']",
                    "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[7]/div[3]/div/ul/li");
        }
        else{

            Select dropdown = new Select(SetupClass.GetDriver().findElement(By.id("registration_second_step_mailingCountryCode")));
            dropdown.selectByVisibleText("Ukraine");

            // comment because returns error, add checking on empty values
            error_appears.AppearingErrorCheck(empty_value,"//input[@id='registration_second_step_phone']",
                    "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[8]/div/div/ul/li",true);

            error_appears.CallErrorCheck(empty_value,db_registration_third_step_general.ThirdStep_ZipCode_Positive(),
                    "//input[@id='registration_second_step_mailingPostalCode']",
                    "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[7]/div[3]/div/ul/li");

            jse.executeScript("window.scrollTo(5000,0)");
            field.EnterValue("//input[@id='registration_second_step_phone']","1234567890");
            field.EnterValue("//input[@id='registration_second_step_fwAddress2']","test_OwnerStreet2"); // not required data, so don't make any tests for it
        }
    }

    private void Third_Step_For_Entity(int user_type) throws Exception{
        String[] empty_value = {""};
        JavascriptExecutor jse = (JavascriptExecutor)SetupClass.GetDriver();
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("registration_second_step_account_fwLegalEntityName")));

        error_appears.CallErrorCheck(db_registration_third_step_E.ThirdStep_EntityName_Negative(), db_registration_third_step_E.ThirdStep_EntityName_Positive(),
                "//input[@id='registration_second_step_account_fwLegalEntityName']",
                "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[3]/div/div[1]/div/div/ul/li");
        error_appears.CallErrorCheck(db_registration_third_step_E.ThirdStep_EntityAddress_Negative(), db_registration_third_step_E.ThirdStep_EntityAddress_Positive(),
                "//input[@id='registration_second_step_account_billingStreet']",
                "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[3]/div/div[3]/div/div/ul/li");
        error_appears.CallErrorCheck(db_registration_third_step_E.ThirdStep_EntityCity_Negative(), db_registration_third_step_E.ThirdStep_EntityCity_Positive(),
                "//input[@id='registration_second_step_account_billingCity']",
                "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[3]/div/div[5]/div[1]/div/ul/li");

        if(!SetupClass.GetDriver().findElement(By.id("registration_second_step_account_fwEntityEmailThesame")).isSelected()){
            error_appears.CallErrorCheck(db_registration_third_step_E.ThirdStep_EntityEmail_Negative(),
                    db_registration_third_step_E.ThirdStep_EntityEmail_Positive(),"//input[@id='registration_second_step_account_fwEntityEmail']",
                    "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[3]/div/div[6]/div/div[2]/ul/li");
        }

        error_appears.CallErrorCheck(db_registration_third_step_E.ThirdStep_JobTitle_Negative(),
                db_registration_third_step_E.ThirdStep_JobTitle_Positive(),"//input[@id='registration_second_step_account_fwJobTitle']",
                "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[7]/div/div/ul/li");

        SetupClass.GetDriver().findElement(By.id("registration_second_step_fwIsLegalNameThesame")).click();
        if (!SetupClass.GetDriver().findElement(By.id("registration_second_step_fwIsLegalNameThesame")).isSelected()){
            error_appears.CallErrorCheck(db_registration_third_step_general.ThirdStep_UserName_Negative(), db_registration_third_step_general.ThirdStep_UserName_Positive(),
                    "//input[@id='registration_second_step_fwLegalFirstName']",
                    "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[6]/div[1]/div/ul/li");

            error_appears.CallErrorCheck(db_registration_third_step_general.ThirdStep_UserLastName_Negative(),db_registration_third_step_general.ThirdStep_UserLastName_Positive(),
                    "//input[@id='registration_second_step_fwLegalLastName']",
                    "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[6]/div[2]/div/ul/li");
        }
        error_appears.CallErrorCheck(db_registration_third_step_general.ThirdStep_Address_Negative(),db_registration_third_step_general.ThirdStep_Address_Positive(),
                "//input[@id='registration_second_step_mailingStreet']",
                "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[9]/div/div/ul/li");

        error_appears.CallErrorCheck(db_registration_third_step_general.ThirdStep_City_Negative(),db_registration_third_step_general.ThirdStep_City_Positive(),
                "//input[@id='registration_second_step_mailingCity']",
                "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[11]/div[1]/div/ul/li");

        error_appears.CallErrorCheck(empty_value,db_registration_third_step_general.ThirdStep_ZipCode_Positive(),
                "//input[@id='registration_second_step_mailingPostalCode']",
                "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[11]/div[3]/div/ul/li");

        if(user_type == 3) {
            error_appears.EnterAndCheckValuePhone(db_registration_third_step_general.ThirdStep_Phone_Negative(),
                    "//input[@id='registration_second_step_phone']",false);
            error_appears.EnterAndCheckValuePhone(db_registration_third_step_general.ThirdStep_Phone_Positive(),
                    "//input[@id='registration_second_step_phone']",true);
            error_appears.EnterAndCheckValuePhone(db_registration_third_step_general.ThirdStep_Phone_Negative(),
                    "//input[@id='registration_second_step_account_phone']",false);
            error_appears.EnterAndCheckValuePhone(db_registration_third_step_general.ThirdStep_Phone_Positive(),
                    "//input[@id='registration_second_step_account_phone']",true);

            error_appears.CallErrorCheck(db_registration_third_step_general.ThirdStep_ZipCode_Negative(),db_registration_third_step_general.ThirdStep_ZipCode_Positive(),
                    "//input[@id='registration_second_step_account_billingPostalCode']",
                    "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[3]/div/div[5]/div[3]/div/ul/li");

            jse.executeScript("window.scrollTo(5000,0)");
            Select dropdown1 = new Select(SetupClass.GetDriver().findElement(By.id("registration_second_step_account_billingStateCode")));
            dropdown1.selectByVisibleText("Connecticut");
            jse.executeScript("window.scrollTo(5000,0)");
            Select dropdown2 = new Select(SetupClass.GetDriver().findElement(By.id("registration_second_step_mailingStateCode")));
            dropdown2.selectByVisibleText("Connecticut");
        }
        else {
            error_appears.CallErrorCheck(empty_value,db_registration_third_step_E.ThirdStep_Phone_Positive(),
                    "//input[@id='registration_second_step_account_phone']",
                    "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[3]/div/div[7]/div/div/ul/li");

            error_appears.CallErrorCheck(empty_value,db_registration_third_step_general.ThirdStep_Phone_Positive(),
                    "//input[@id='registration_second_step_phone']",
                    "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[12]/div/div/ul/li");

            error_appears.CallErrorCheck(empty_value,db_registration_third_step_E.ThirdStep_ZipCode_Positive(),
                    "//input[@id='registration_second_step_account_billingPostalCode']",
                    "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[3]/div/div[5]/div[3]/div/ul/li");

            Select dropdown1 = new Select(SetupClass.GetDriver().findElement(By.id("registration_second_step_account_billingCountryCode")));
            dropdown1.selectByVisibleText("Ukraine");
            jse.executeScript("window.scrollTo(5000,0)");
            field.EnterValue("//input[@id='registration_second_step_account_fwAddress2']","test_EntityStreet2"); // not required

            jse.executeScript("window.scrollTo(5000,0)");
            field.EnterValue("//input[@id='registration_second_step_fwAddress2']","test_OwnerStreet2"); // not required
            jse.executeScript("window.scrollTo(5000,0)");
            Select dropdown2 = new Select(SetupClass.GetDriver().findElement(By.id("registration_second_step_mailingCountryCode")));
            dropdown2.selectByVisibleText("Ukraine");
        }
    }

    private void Third_Step_Switchers(int user_type) throws Exception{
        if (user_type == 1 || user_type == 2 || user_type == 5){
            Third_Step_Switchers_Check(10);
        }
        else if (user_type == 3 || user_type == 4){
            Third_Step_Switchers_Check(14);
        }
    }

    private void Third_Step_Switchers_Check(int add_on) throws Exception{
        String[] locators = {"registration_second_step_fwUnderstandRisks","registration_second_step_fwUnderstandSecurities",
                "registration_second_step_fwUnderstandConfluence","registration_second_step_fwAgreeToTerm"};

        for (int i = 0; i < db_registration_third_step_general.ThirdStep_Switchers().length; i++) {
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='footer-logo']")));
            Thread.sleep(1000);
            char[] cs = db_registration_third_step_general.ThirdStep_Switchers()[i].toCharArray();
            for (int j = 0; j < 4; j++) {
                String index = Integer.toString(add_on+j);
                if (cs[j] == '1') {
                    switcher.SwitcherManage(locators[j],
                            "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[" + index + "]/div/div/div/label/div/div/label[2]", true);
                }
                else {
                    switcher.SwitcherManage(locators[j],
                            "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[" + index + "]/div/div/div/label/div/div/label[1]", false);
                }
            }
            SetupClass.GetDriver().findElement(By.xpath("//button[@id='next']")).click();

            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='footer-logo']")));
            Thread.sleep(1500);
            for (int j = 0; j < 4; j++) {
                String index = Integer.toString(j+add_on);
//                    System.out.println(index);
                if(j != 3) {
                    if (cs[j] == '1') {
                        if (field.ExistElementOnThePage("//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[" + index + "]/div/div/div/ul/li", 1)) {
                            throw new NewAssertError("Switcher in "+ db_registration_third_step_general.ThirdStep_Switchers()[i] +" for switcher number " + j + " works wrong!");
                        }
                    } else if (cs[j] == '0') {
                        if (!field.ExistElementOnThePage("//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[" + index + "]/div/div/div/ul/li", 1)) {
                            throw new NewAssertError("Switcher in "+ db_registration_third_step_general.ThirdStep_Switchers()[i] +" for switcher number " + j + " works wrong!");
                        }
                    }
                }
                else{
                    if (cs[j] == '1') {
                        if (field.ExistElementOnThePage("//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[" + index + "]/div/div/div/div/ul/li", 1)) {
                            throw new NewAssertError("Switcher in "+ db_registration_third_step_general.ThirdStep_Switchers()[i] +" for switcher number " + j + " works wrong!");
                        }
                    }
                    else if (cs[j] == '0') {
                        if (!field.ExistElementOnThePage("//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[" + index + "]/div/div/div/div/ul/li", 1)) {
                            throw new NewAssertError("Switcher in "+ db_registration_third_step_general.ThirdStep_Switchers()[i] +" for switcher number " + j + " works wrong!");
                        }
                    }
                }
            }
        }
    }

    private void Check_Info_In_MyAccount(int user_type) throws Exception{
        PagesURL url = new PagesURL();
        url.LoginPage();

        Login l = new Login();
        Scanner reader = new Scanner(new File("src/test/java/UnitClassSet/Registration/id.txt"));
        int id = reader.nextInt();
        String email_field = "prybehav+autotest" + Integer.toString(id-1) + "@gmail.com";
        l.LoginTest(email_field);
        LogUtil.log("Login");

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='login-block-authorized-container pull-right']")));
        Thread.sleep(300);
        SetupClass.GetDriver().findElement(By.xpath("//div[@class='login-block-authorized-container pull-right']")).click();
        Thread.sleep(500);
        if(!SetupClass.GetDriver().findElement(By.xpath("//a[@href='https://secure-seriesone.dynamo-ny.com/profile/edit']")).isDisplayed()) {
            SetupClass.GetDriver().findElement(By.xpath("//div[@class='login-block-authorized-container pull-right']")).click();
        }
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://secure-seriesone.dynamo-ny.com/profile/edit']")));
        Thread.sleep(300);
        SetupClass.GetDriver().findElement(By.xpath("//a[@href='https://secure-seriesone.dynamo-ny.com/profile/edit']")).click();

        SetupClass.GetDriverWait().until(ExpectedConditions.titleIs("Edit profile | seriesOne"));

        String name_field = db_registration_first_step.FirstStep_UserName_Positive()[db_registration_first_step.FirstStep_UserName_Positive().length-1]
                + " " + db_registration_first_step.FirstStep_UserLastName_Positive()[db_registration_first_step.FirstStep_UserLastName_Positive().length-1];
        String address_for_US = db_registration_third_step_general.ThirdStep_Address_Positive()[db_registration_third_step_general.ThirdStep_Address_Positive().length-1] +",\n"
                + db_registration_third_step_general.ThirdStep_City_Positive()[db_registration_third_step_general.ThirdStep_City_Positive().length-1]
                + ", " + "Connecticut" + ", "
                + db_registration_third_step_general.ThirdStep_ZipCode_Positive()[db_registration_third_step_general.ThirdStep_ZipCode_Positive().length-1];
        String address_for_noUS = db_registration_third_step_general.ThirdStep_Address_Positive()[db_registration_third_step_general.ThirdStep_Address_Positive().length-1] +",\n"
                + "test_OwnerStreet2" + ", " + db_registration_third_step_general.ThirdStep_City_Positive()[db_registration_third_step_general.ThirdStep_City_Positive().length-1]
                +  ", " + db_registration_third_step_general.ThirdStep_ZipCode_Positive()[db_registration_third_step_general.ThirdStep_ZipCode_Positive().length-1];
        String phone_field = db_registration_third_step_general.ThirdStep_Phone_Positive()[db_registration_third_step_general.ThirdStep_Phone_Positive().length-1];
        String networth = db_registration_second_step.SecondStep_NetWorth_Positive()[db_registration_second_step.SecondStep_NetWorth_Positive().length-1];
        String income = db_registration_second_step.SecondStep_Income_Positive()[db_registration_second_step.SecondStep_Income_Positive().length-1];
        String entity_name = db_registration_third_step_E.ThirdStep_EntityName_Positive()[db_registration_third_step_E.ThirdStep_EntityName_Positive().length-1];
        String entity_address_for_US = db_registration_third_step_E.ThirdStep_EntityAddress_Positive()[db_registration_third_step_E.ThirdStep_EntityAddress_Positive().length-1] +",\n"
                + db_registration_third_step_E.ThirdStep_EntityCity_Positive()[db_registration_third_step_E.ThirdStep_EntityCity_Positive().length-1]
                + ", " + "Connecticut" + ", "
                + db_registration_third_step_E.ThirdStep_ZipCode_Positive()[db_registration_third_step_E.ThirdStep_ZipCode_Positive().length-1];
        String entity_phone = db_registration_third_step_E.ThirdStep_Phone_Positive()[db_registration_third_step_E.ThirdStep_Phone_Positive().length-1];
        String entity_address_for_noUS = db_registration_third_step_E.ThirdStep_EntityAddress_Positive()[db_registration_third_step_E.ThirdStep_EntityAddress_Positive().length-1] +",\n"
                + "test_EntityStreet2" + ", " + db_registration_third_step_E.ThirdStep_EntityCity_Positive()[db_registration_third_step_E.ThirdStep_EntityCity_Positive().length-1]
                + ", "
                + db_registration_third_step_E.ThirdStep_ZipCode_Positive()[db_registration_third_step_E.ThirdStep_ZipCode_Positive().length-1];

        String[] account_details_individual_US_Accredited = {"Individual","United States Citizen","Accredited"};
        String[] account_details_individual_noUS = {"Individual","Non-US","Accredited"};
        String[] account_details_individual_US_noAccredited = {"Individual","United States Citizen","Non-Accredited"};
        String[] account_details_entity_US_ = {"Entity","United States Citizen","Accredited"};
        String[] account_details_entity_noUS = {"Entity","Non-US","Accredited"};

        String[] personal_info_for_US = {name_field, "United States", address_for_US, phone_field, email_field};
        String[] personal_info_for_noUS = {name_field, "Ukraine", address_for_noUS, phone_field, email_field};

        String[] contact_details_for_entity_US = {entity_name, "United States", entity_address_for_US, entity_phone};
        String[] contact_details_for_entity_noUS = {entity_name, "Ukraine", entity_address_for_noUS, entity_phone};

        String[] additional_info_networth_and_income = {networth, income};
        if(user_type == 1){
            Match_Value_For_Diff_Types(account_details_individual_US_Accredited,2);
            Match_Value_For_Diff_Types(personal_info_for_US,7);
        }
        else if(user_type == 2){
            Match_Value_For_Diff_Types(account_details_individual_noUS,2);
            Match_Value_For_Diff_Types(personal_info_for_noUS,7);
        }
        else if(user_type == 3){
            Match_Value_For_Diff_Types(account_details_entity_US_,2);
            Match_Value_For_Diff_Types(contact_details_for_entity_US,7);
            Match_Value_For_Diff_Types(personal_info_for_US,14);
        }
        else if(user_type == 4){
            Match_Value_For_Diff_Types(account_details_entity_noUS,2);
            Match_Value_For_Diff_Types(contact_details_for_entity_noUS,7);
            Match_Value_For_Diff_Types(personal_info_for_noUS,14);
        }
        else if(user_type == 5){
            Match_Value_For_Diff_Types(account_details_individual_US_noAccredited,2);
            Match_Value_For_Diff_Types(additional_info_networth_and_income,6);
            Match_Value_For_Diff_Types(personal_info_for_US,9);
        }
    }

    private void Match_Value_For_Diff_Types(String[] values_array, int index){
        for(int i = 0; i < values_array.length; i++){
            String value_in_the_field = SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div["+ Integer.toString(i + index) +"]/div[2]")).getText();
            if(!value_in_the_field.startsWith("+")){
                if(!values_array[i].equals(value_in_the_field)){
                    throw new NewAssertError("Error in match value " + values_array[i] + " with "
                            + SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div["+ Integer.toString(i + index) +"]/div[2]")).getText());
                }
            }
            else{
                String value_for_cleared = value_in_the_field;
                while(value_for_cleared.contains("(")){
                    value_for_cleared = value_for_cleared.replace("(","");
                }
                if(value_for_cleared.contains(")")){
                    value_for_cleared = value_for_cleared.replace(")","");
                }
                if(value_for_cleared.contains("-")){
                    value_for_cleared = value_for_cleared.replace("-","");
                }
                if(value_for_cleared.contains(" ")){
                    value_for_cleared = value_for_cleared.replace(" ","");
                }
                if (!value_for_cleared.contains(values_array[i])) {
                    throw new NewAssertError("Phone value " + value_in_the_field + " doesn't (" + value_for_cleared + ") match with " + values_array[i]);
                }
            }
        }
    }
}
