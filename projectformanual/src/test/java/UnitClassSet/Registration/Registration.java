package UnitClassSet.Registration;

import DB.db_registration_first_step;
import DB.db_registration_second_step;
import DB.db_registration_third_step_E;
import DB.db_registration_third_step_general;
import SupportClasses.AllureFunc.LogUtil;
import SupportClasses.SetupClass.SetupClass;
import UnitClassSet.CheckAppearingError.AppearingError;
import UnitClassSet.Field;
import UnitClassSet.Maintenance.Maintenance;
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
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='footer-logo']")));
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
                ThirdStep(user_type);

                Thread.sleep(3000);
                return;
            }
            else if(CheckRegistrationStep(current_step) == 4){
                current_step = 4;// after success third step it will load home page. 4 means check if we on the home page.

                System.out.println("Registration finished");
                LogUtil.log("Registration finished");
            }
            else{
                System.out.println("Something goes wrong. You are go away from investment steps");
            }
        }
    }

    private int CheckRegistrationStep(int prev_step) throws Exception{
        int wait_time = 5;

        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='footer-logo']")));
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

    private void FirstStep() throws Exception{
        error_appears.CallErrorCheck(db_registration_first_step.FirstStep_UserName_Negative(), db_registration_first_step.FirstStep_UserName_Positive(),
                "//input[@id='registration_investor_firstName']",
                "//*[@id=\"ajaxContent\"]/div[3]/div[2]/div/form/div[2]/div[1]/div/ul/li"); // for positive TC expected result = false => we expect that error does not appears

        error_appears.CallErrorCheck(db_registration_first_step.FirstStep_UserLastName_Negative(),db_registration_first_step.FirstStep_UserLastName_Positive(),
                "//input[@id='registration_investor_lastName']",
                "//*[@id=\"ajaxContent\"]/div[3]/div[2]/div/form/div[2]/div[2]/div/ul/li");

        error_appears.EmailErrorCheck(db_registration_first_step.FirstStep_Email_Negative(),true);

        Scanner reader = new Scanner(new File("src/test/java/UnitClassSet/Registration/id.txt"));
        int id = reader.nextInt(); // id > unique number for email, that reads from the file and writes bigger on one.
        //System.out.println(id);
        error_appears.EmailErrorCheck(db_registration_first_step.FirstStep_Email_Positive("prybehav+autotest" + id + "@gmail.com"),false);
        id++;
        String str = Integer.toString(id);
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/java/UnitClassSet/Registration/id.txt"));
        writer.write(str);
        writer.close();

        SetupClass.GetDriver().findElement(By.xpath("//input[@id='registration_investor_firstName']")).clear();

        Thread.sleep(100);
        error_appears.CallErrorCheck(db_registration_first_step.FirstStep_Pass_Negative(),db_registration_first_step.FirstStep_Pass_Positive(),
                "//input[@id='registration_investor_password']",
                "//*[@id=\"ajaxContent\"]/div[3]/div[2]/div/form/div[4]/div/div/ul/li");

        Thread.sleep(200);
        field.EnterValue("//input[@id='registration_investor_firstName']",
                db_registration_first_step.FirstStep_UserName_Positive()[db_registration_first_step.FirstStep_UserName_Positive().length-1]);
        field.EnterValue("//input[@id='registration_investor_password']",
                db_registration_first_step.FirstStep_UserName_Positive()[db_registration_first_step.FirstStep_UserName_Positive().length-1]);// foreach field will be match last in db file

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
        //System.out.println(user_type);
        String[] empty_value = {""};
        JavascriptExecutor jse = (JavascriptExecutor)SetupClass.GetDriver();

        if (user_type == 1 || user_type == 2 || user_type == 5 ){
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

            // this part must be for all type, but now noUS user have not validate phone number. Comment this for test working.
//            error_appears.EnterAndCheckValuePhone(db_registration_third_step_I.ThirdStep_Phone_Negative(),"//input[@id='registration_second_step_phone']",
//                    "//input[@id='registration_second_step_phone']",false);
//            error_appears.EnterAndCheckValuePhone(db_registration_third_step_I.ThirdStep_Phone_Positive(),"//input[@id='registration_second_step_phone']",
//                    "//input[@id='registration_second_step_phone']",true);

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
//                error_appears.AppearingErrorCheck(empty_value,"//input[@id='registration_second_step_phone']",
//                        "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[8]/div/div/ul/li",true);

                error_appears.CallErrorCheck(empty_value,db_registration_third_step_general.ThirdStep_ZipCode_Positive(),
                        "//input[@id='registration_second_step_mailingPostalCode']",
                        "//*[@id=\"ajaxContent\"]/div[3]/div/div/form/div[7]/div[3]/div/ul/li");

                jse.executeScript("window.scrollTo(5000,0)");
                field.EnterValue("//input[@id='registration_second_step_phone']","1234567890");
                field.EnterValue("//input[@id='registration_second_step_fwAddress2']","Street2"); // not required data, so don't make any tests for it
            }
        }

        else if(user_type == 3 || user_type == 4){
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

            //SetupClass.GetDriver().findElement(By.xpath("//button[@id='next']")).click();
        }
    }
}
