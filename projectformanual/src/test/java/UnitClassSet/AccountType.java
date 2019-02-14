package UnitClassSet;

import SupportClasses.SetupClass.SetupClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountType {
    //1 - Individual US
    //2 - Individual noUS
    //3 - Entity US
    //4 - Entity noUS
    //5 - Individual no Accredited

    public int AccountTypeCheck() throws Exception{
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='login-block-authorized-container pull-right']")));
        Thread.sleep(500);
        SetupClass.GetDriver().findElement(By.xpath("//div[@class='login-block-authorized-container pull-right']")).click();

        Thread.sleep(200);
        if(!SetupClass.GetDriver().findElement(By.xpath("//a[@href='https://secure-seriesone.dynamo-ny.com/profile/edit']")).isDisplayed()) {
            SetupClass.GetDriver().findElement(By.xpath("//div[@class='login-block-authorized-container pull-right']")).click();
        }
        SetupClass.GetDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://secure-seriesone.dynamo-ny.com/profile/edit']")));
        Thread.sleep(300);
        if(!SetupClass.GetDriver().findElement(By.xpath("//a[@href='https://secure-seriesone.dynamo-ny.com/profile/edit']")).isDisplayed()) {
            SetupClass.GetDriver().findElement(By.xpath("//div[@class='login-block-authorized-container pull-right']")).click();
        }
        SetupClass.GetDriver().findElement(By.xpath("//a[@href='https://secure-seriesone.dynamo-ny.com/profile/edit']")).click();

        if (SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[1]/div[2]")).getText().equals("Individual") &&
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[2]/div[2]")).getText().equals("United States Citizen") &&
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[3]/div[2]")).getText().equals("Accredited")){
            System.out.println("Individual US Accredited");
            return 1;
        }
        else if (SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[2]/div[2]")).getText().equals("Individual") &&
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[3]/div[2]")).getText().equals("United States Citizen") &&
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[4]/div[2]")).getText().equals("Accredited")){
            System.out.println("Individual US Accredited");
            return 1;
        }
        else if (SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[1]/div[2]")).getText().equals("Individual") &&
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[2]/div[2]")).getText().equals("Non-US")){
            System.out.println("Individual no US");
            return 2;
        }
        else if (SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[2]/div[2]")).getText().equals("Individual") &&
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[3]/div[2]")).getText().equals("Non-US")){
            System.out.println("Individual no US");
            return 2;
        }
        else if (SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[1]/div[2]")).getText().equals("Entity") &&
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[2]/div[2]")).getText().equals("United States Citizen")){
            System.out.println("Entity US");
            return 3;
        }
        else if (SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[2]/div[2]")).getText().equals("Entity") &&
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[3]/div[2]")).getText().equals("United States Citizen")){
            System.out.println("Entity US");
            return 3;
        }
        else if (SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[1]/div[2]")).getText().equals("Entity") &&
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[2]/div[2]")).getText().equals("Non-US")){
            System.out.println("Entity no US");
            return 4;
        }
        else if (SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[2]/div[2]")).getText().equals("Entity") &&
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[3]/div[2]")).getText().equals("Non-US")) {
            System.out.println("Entity no US");
            return 4;
        }
        else if (SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[1]/div[2]")).getText().equals("Individual") &&
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[2]/div[2]")).getText().equals("United States Citizen") &&
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[3]/div[2]")).getText().equals("Non-Accredited")){
            System.out.println("Individual US no Accredited");
            return 5;
        }
        else if (SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[2]/div[2]")).getText().equals("Individual") &&
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[3]/div[2]")).getText().equals("United States Citizen") &&
                SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[4]/div[2]")).getText().equals("Non-Accredited")){
            System.out.println("Individual US no Accredited");
            return 5;
        }
        System.out.println("Error values: ");
        System.out.println(SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[1]/div[2]")).getText());
        System.out.println(SetupClass.GetDriver().findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/div[1]/div[2]/div[2]")).getText());
        return 0;
    }
}
