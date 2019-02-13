package UnitClassSet.Investment;

import SupportClasses.AllureFunc.LogUtil;
import SupportClasses.SetupClass.SetupClass;
import UnitClassSet.AccountType;
import UnitClassSet.Investment.Investment;
import UnitClassSet.Login.Login;
import UnitClassSet.Maintenance.Maintenance;
import UnitClassSet.PagesURL;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class test_IndividualUS extends SetupClass{
    private String individual_US = "prybehav+153@gmail.com";

    @Title("Test Individual US investment process for ACH method")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Investment","Individual US","ACH"})
    @Description("Login > check account type > make ACH investment")
    @Test
    public void Investment_ACH() throws Exception{
        PagesURL url = new PagesURL();
        url.LoginPage();

        Maintenance maintenance = new Maintenance();
        if (maintenance.MaintenancePageCheck()){
            LogUtil.log("Maintenance mode is on!");
            return;
        }

        Login l = new Login();
        l.LoginTest(individual_US);
        LogUtil.log("Login");

        AccountType acc_type_obj = new AccountType();
        int acc_type = acc_type_obj.AccountTypeCheck();
        LogUtil.log("Type is: " + acc_type);

        Investment TestInvestment = new Investment();
        TestInvestment.Investment(acc_type,"ACH","100");
    }

    @Title("Test Individual US investment process for Wire method")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Investment","Individual US","Wire"})
    @Description("Login > check account type > make Wire investment")
    @Test
    public void Investment_Wire() throws Exception{
        PagesURL url = new PagesURL();
        url.LoginPage();

        Maintenance maintenance = new Maintenance();
        if (maintenance.MaintenancePageCheck()){
            LogUtil.log("Maintenance mode is on!");
            return;
        }

        Login l = new Login();
        l.LoginTest(individual_US);
        LogUtil.log("Login");

        AccountType acc_type_obj = new AccountType();
        int acc_type = acc_type_obj.AccountTypeCheck();
        LogUtil.log("Type is: " + acc_type);

        Investment TestInvestment = new Investment();
        TestInvestment.Investment(acc_type,"Wire","100");
    }

    @Title("Test Individual US investment process for Bitcoin method")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Investment","Individual US","Bitcoin"})
    @Description("Login > check account type > make Bitcoin investment")
    @Test
    public void Investment_Bitcoin() throws Exception{
        PagesURL url = new PagesURL();
        url.LoginPage();

        Maintenance maintenance = new Maintenance();
        if (maintenance.MaintenancePageCheck()){
            LogUtil.log("Maintenance mode is on!");
            return;
        }

        Login l = new Login();
        l.LoginTest(individual_US);
        LogUtil.log("Login");

        AccountType acc_type_obj = new AccountType();
        int acc_type = acc_type_obj.AccountTypeCheck();
        LogUtil.log("Type is: " + acc_type);

        Investment TestInvestment = new Investment();
        TestInvestment.Investment(acc_type,"Bitcoin","0.5");
    }

    @Title("Test Individual US investment process for Ethereum method")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Investment","Individual US","Ethereum"})
    @Description("Login > check account type > make Ethereum investment")
    @Test
    public void Investment_Ethereum() throws Exception{
        PagesURL url = new PagesURL();
        url.LoginPage();

        Maintenance maintenance = new Maintenance();
        if (maintenance.MaintenancePageCheck()){
            LogUtil.log("Maintenance mode is on!");
            return;
        }

        Login l = new Login();
        l.LoginTest(individual_US);
        LogUtil.log("Login");

        AccountType acc_type_obj = new AccountType();
        int acc_type = acc_type_obj.AccountTypeCheck();
        LogUtil.log("Type is: " + acc_type);

        Investment TestInvestment = new Investment();
        TestInvestment.Investment(acc_type,"Ethereum","0.5");
    }
}
