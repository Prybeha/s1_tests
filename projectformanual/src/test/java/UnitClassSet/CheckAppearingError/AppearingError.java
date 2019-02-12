package UnitClassSet.CheckAppearingError;

import SupportClasses.Exceptions.NewAssertError;
import SupportClasses.SetupClass.SetupClass;
import UnitClassSet.Field;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AppearingError {
    private Field field = new Field();
    public void AppearingErrorCheck(String[] db_value_for_check, String field_locator, String error_locator, boolean expected_result) throws Exception{
        for (int i = 0; i < db_value_for_check.length; i++) {
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(field_locator)));
            JavascriptExecutor jse = (JavascriptExecutor)SetupClass.GetDriver();

            //jse.executeScript("window.scrollTo(0,0);");
            Thread.sleep(200);
            jse.executeScript("window.scrollTo(5000,0)");
            Thread.sleep(200);
            field.EnterValue(field_locator,db_value_for_check[i]);
            SetupClass.GetDriver().findElement(By.id("next")).click();

            //Thread.sleep(200);
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(field_locator)));
            Thread.sleep(1000);
            jse.executeScript("window.scrollTo(5000,0)");
            Thread.sleep(500);
            if(expected_result) { // must appears errors => negative test cases
                if (!field.ExistElementOnThePage(error_locator, 2)) {
                    throw new NewAssertError("For locator '" + field_locator + "' value '" + db_value_for_check[i] + "' works wrong!");
                }
            }
            else{ // does not appears error => positive test cases
                if (field.ExistElementOnThePage(error_locator, 1)) {
                    throw new NewAssertError("For locator '" + field_locator + "' value '" + db_value_for_check[i] + "' works wrong!");
                }
            }
        }
    }

    public void EmailErrorCheck(String[] db_value_for_check, boolean expected_result) throws Exception{
        for (int i = 0; i < db_value_for_check.length; i++) {
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("registration_investor_email")));
            JavascriptExecutor jse = (JavascriptExecutor)SetupClass.GetDriver();
            jse.executeScript("window.scrollTo(5000,0)");
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("registration_investor_email")));
            Thread.sleep(400);

            field.EnterValue("//input[@id='registration_investor_email']",db_value_for_check[i]);
            SetupClass.GetDriver().findElement(By.id("next")).click();
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.id("registration_investor_email")));
            Thread.sleep(800);

            jse.executeScript("window.scrollTo(5000,0)");
            if(expected_result) { // must appears errors => negative test cases
                if (!field.ExistElementOnThePage("//*[@id=\"ajaxContent\"]/div[3]/div[2]/div/form/div[3]/div/div/ul/li", 2) &&
                        !field.ExistElementOnThePage("//*[@id=\"ajaxContent\"]/div[3]/div[2]/div/form/div[1]/div/div/ul/li",2)) {
                    throw new NewAssertError("For locator email value '" + db_value_for_check[i] + "' works wrong!");
                }
            }
            else{ // does not appears error => positive test cases
                if (field.ExistElementOnThePage("//*[@id=\"ajaxContent\"]/div[3]/div[2]/div/form/div[3]/div/div/ul/li", 1) &&
                        field.ExistElementOnThePage("//*[@id=\"ajaxContent\"]/div[3]/div[2]/div/form/div[1]/div/div/ul/li",1)) {
                    throw new NewAssertError("For locator email value '" + db_value_for_check[i] + "' works wrong!");
                }
            }
        }
    }

    public void EnterAndCheckValueForMoney(String[] db_value_for_check, String field_locator, String check_locator, boolean expected_result) throws Exception{
        for (int i = 0; i < db_value_for_check.length; i++){
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(field_locator)));
            Thread.sleep(300);
            field.EnterValue(field_locator,db_value_for_check[i]);

            SetupClass.GetDriver().findElement(By.xpath(field_locator)).sendKeys(Keys.TAB);
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(field_locator)));
            Thread.sleep(300);
            String value = db_value_for_check[i];
            if(db_value_for_check[i].contains(",")){
                value = value.replace(",","");
            }
            if(db_value_for_check[i].contains(".")){
                value = value.substring(0, value.indexOf("."));
                //System.out.println("Check value is " + value);
            }
//            if (value.equals("0") || value.equals("")){
//                continue;
//            }

            if (expected_result){
                if (!value.equals(SetupClass.GetDriver().findElement(By.xpath(check_locator)).getAttribute("value"))){
                    throw new NewAssertError("Values don't match for positive test");
                }
            }
            else{
                if (value.equals(SetupClass.GetDriver().findElement(By.xpath(check_locator)).getAttribute("value"))){
                    throw new NewAssertError("Values match for negative test");
                }
            }
        }
    }

    public void EnterAndCheckValuePhone(String[] db_value_for_check, String field_locator, boolean expected_result) throws Exception{
        for (int i = 0; i < db_value_for_check.length; i++){
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(field_locator)));
            Thread.sleep(600);
            field.EnterValue(field_locator,db_value_for_check[i]);

            SetupClass.GetDriver().findElement(By.xpath("//*[@id='next']")).click();
            SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(field_locator)));
            Thread.sleep(400);

            String value = db_value_for_check[i];
            String value_in_the_field = SetupClass.GetDriver().findElement(By.xpath(field_locator)).getAttribute("value");

            if(expected_result) {
                if(value_in_the_field.contains("(")){
                    value_in_the_field = value_in_the_field.replace("(","");
                }
                if(value_in_the_field.contains(")")){
                    value_in_the_field = value_in_the_field.replace(")","");
                }
                if(value_in_the_field.contains("-")){
                    value_in_the_field = value_in_the_field.replace("-","");
                }
                if(value_in_the_field.contains(" ")){
                    value_in_the_field = value_in_the_field.replace(" ","");
                }
                if (!value_in_the_field.contains(value)) {
                    throw new NewAssertError("Values don't " + value_in_the_field + " match with " + value + " for positive test");
                }
            }
            else{
                if(!value_in_the_field.equals("")){
                    throw new NewAssertError("Values don't " + value_in_the_field + " match with " + value + " for negative test");
                }
            }
        }
    }

    public void CallErrorCheck(String[] db_value_negative,String[] db_value_positive, String field_locator, String error_locator) throws Exception{
        AppearingErrorCheck(db_value_negative,field_locator,error_locator,true);
        Thread.sleep(500);
        AppearingErrorCheck(db_value_positive,field_locator,error_locator,false);
    }
}
