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
        url.FundsPage();

    }
}
