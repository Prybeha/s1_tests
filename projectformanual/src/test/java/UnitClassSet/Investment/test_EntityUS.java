package UnitClassSet.Investment;

import SupportClasses.AllureFunc.LogUtil;
import SupportClasses.SetupClass.SetupClass;
import UnitClassSet.Login.Login;
import UnitClassSet.Maintenance.Maintenance;
import UnitClassSet.StaticPages.PagesURL;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class test_EntityUS extends SetupClass {
    private String entity_US = "prybehav+176@gmail.com";

    @Title("Test Entity US investment process for ACH method")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Investment","Entity US","ACH"})
    @Description("Login > check account type > make ACH investment")
    @Test
    public void Investment_ACH() throws Exception{
        PagesURL.LoginPage();

        Maintenance maintenance = new Maintenance();
        maintenance.MaintenancePageCheck();

        Login l = new Login();
        l.LoginTest(entity_US);
        LogUtil.log("Login");

        AccountType acc_type_obj = new AccountType();
        int acc_type = acc_type_obj.AccountTypeCheck();
        LogUtil.log("Type is: " + acc_type);

        Investment TestInvestment = new Investment();
        TestInvestment.Investment(acc_type,"ACH","100");
    }

    @Title("Test Entity US investment process for Wire method")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Investment","Entity US","Wire"})
    @Description("Login > check account type > make Wire investment")
    @Test
    public void Investment_Wire() throws Exception{
        PagesURL.LoginPage();

        Maintenance maintenance = new Maintenance();
        maintenance.MaintenancePageCheck();

        Login l = new Login();
        l.LoginTest(entity_US);
        LogUtil.log("Login");

        AccountType acc_type_obj = new AccountType();
        int acc_type = acc_type_obj.AccountTypeCheck();
        LogUtil.log("Type is: " + acc_type);

        Investment TestInvestment = new Investment();
        TestInvestment.Investment(acc_type,"Wire","100");
    }

    @Title("Test Entity US investment process for Bitcoin method")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Investment","Entity US","Bitcoin"})
    @Description("Login > check account type > make Bitcoin investment")
    @Test
    public void Investment_Bitcoin() throws Exception{
        PagesURL.LoginPage();

        Maintenance maintenance = new Maintenance();
        maintenance.MaintenancePageCheck();

        Login l = new Login();
        l.LoginTest(entity_US);
        LogUtil.log("Login");

        AccountType acc_type_obj = new AccountType();
        int acc_type = acc_type_obj.AccountTypeCheck();
        LogUtil.log("Type is: " + acc_type);

        Investment TestInvestment = new Investment();
        TestInvestment.Investment(acc_type,"Bitcoin","0.5");
    }

    @Title("Test Entity US investment process for Ethereum method")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Investment","Entity US","Ethereum"})
    @Description("Login > check account type > make Ethereum investment")
    @Test
    public void Investment_Ethereum() throws Exception{
        PagesURL.LoginPage();

        Maintenance maintenance = new Maintenance();
        maintenance.MaintenancePageCheck();

        Login l = new Login();
        l.LoginTest(entity_US);
        LogUtil.log("Login");

        AccountType acc_type_obj = new AccountType();
        int acc_type = acc_type_obj.AccountTypeCheck();
        LogUtil.log("Type is: " + acc_type);

        Investment TestInvestment = new Investment();
        TestInvestment.Investment(acc_type,"Ethereum","0.5");
    }
}
