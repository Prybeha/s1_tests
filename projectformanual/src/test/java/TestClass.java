import SupportClasses.SetupClass.SetupClass;
import SupportClasses.TestLink.TestLinkResult;
import UnitClassSet.Field;
import UnitClassSet.Login.Login;
import UnitClassSet.PagesURL;
import UnitClassSet.Switchers;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Scanner;

public class TestClass extends SetupClass {
    TestLinkResult TestResult = new TestLinkResult();
    private String EntityUS = "prybehav+176@gmail.com";// +
    private String IndividualUS = "prybehav+115@gmail.com";// +
    private String EntityNoUS = "prybehav+297@gmail.com";// +
    private String IndividualNoUS = "prybehav+155@gmail.com";// +
    private String IndividualUSNoAccredited = "prybehav+303@gmail.com";// +
    private Field field = new Field();
    private Switchers switcher = new Switchers();

    @Test
    public void SupportTest() throws Exception{
        // Checking all fields in My Account for no invested users

        PagesURL url = new PagesURL();
        url.LoginPage();
        Login l = new Login();
        l.LoginTest(IndividualUS);

        url.DealPage();
        SetupClass.GetDriver().findElement(By.xpath("//a[@href='https://secure-seriesone.dynamo-ny.com/deal/pdp-qa']")).click();
        //SetupClass.GetDriverWait().until(ExpectedConditions.titleIs("Kinesis QA | seriesOne"));
        Thread.sleep(600);

        SetupClass.GetDriver().findElement(By.xpath("//a[text()='Invest']")).click();
        if (field.ExistElementOnThePage("//div[@class='bootbox modal fade modal-edit-profile popup-agreement in']",2)){
            if (!SetupClass.GetDriver().findElement(By.xpath("//input[@name='firstAgreement']")).isSelected()) {
                SetupClass.GetDriver().findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/div/div[1]/div/label/div/div/label[2]")).click();
            }
            if (!SetupClass.GetDriver().findElement(By.xpath("//input[@name='secondAgreement']")).isSelected()) {
                SetupClass.GetDriver().findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div/div/div[2]/div/label/div/div/label[2]")).click();
            }
            Thread.sleep(200);
            SetupClass.GetDriver().findElement(By.xpath("//button[text()='Confirm']")).click();
        }
    }
}
