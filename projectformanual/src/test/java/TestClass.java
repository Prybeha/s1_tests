import SupportClasses.SetupClass.SetupClass;
import SupportClasses.TestLink.TestLinkResult;
import UnitClassSet.AccountType;
import UnitClassSet.Investment.Investment;
import UnitClassSet.Login.Login;
import UnitClassSet.Maintenance.Maintenance;
import UnitClassSet.PagesURL;
import org.testng.annotations.Test;

public class TestClass extends SetupClass {
    TestLinkResult TestResult = new TestLinkResult();
    private String EntityUS = "prybehav+176@gmail.com";// +
    private String IndividualUS = "prybehav+153@gmail.com";// +
    private String EntityNoUS = "prybehav+297@gmail.com";// +
    private String IndividualNoUS = "prybehav+155@gmail.com";// +
    private String IndividualUSNoAccredited = "prybehav+303@gmail.com";// +

    //@Test
    public void Firsttest() throws Exception{
//        try {
            System.out.println("1 test");
            PagesURL URL = new PagesURL();
            URL.LoginPage();

            Maintenance maintenance = new Maintenance();
            if (maintenance.MaintenancePageCheck()){
                return;
            }

            Login L = new Login();
            L.LoginTest(EntityUS);

            AccountType acc_type_obj = new AccountType();
            int acc_type = acc_type_obj.AccountTypeCheck();

            Investment TestInvestment = new Investment();
            TestInvestment.Investment(acc_type,"ACH","100");
            TestInvestment.Investment(acc_type,"Wire","100");
            TestInvestment.Investment(acc_type,"Bitcoin","0.5");
            TestInvestment.Investment(acc_type,"Ethereum","0.5");

            TestResult.TestLinkPass("FirstTestCaseForManual"); // Must be on the last line of try block
//        }
//        catch (Exception ex){
//            TestResult.TestLinkFail("FirstTestCaseForManual",ex.getMessage()); // Send TC name and error message
//            throw new NewException("Appears exception error with massage: " + ex.getMessage()); // call error
//        }
//        catch (AssertionError e){
//            TestResult.TestLinkFail("FirstTestCaseForManual",e.getMessage()); // Send TC name and error message
//            //fail("Appears assertion error with massage: " + e.getMessage());
//            throw new NewAssertError("Appears assertion error with massage: " + e.getMessage()); // call error
//        }
    }

    //@Test
    public void Secondtest() throws Exception{
        System.out.println("2 test");
        PagesURL URL = new PagesURL();
        URL.LoginPage();

//        Maintenance maintenance = new Maintenance();
//        if (maintenance.MaintenancePageCheck()){
//            return;
//        }

        Login L = new Login();
        L.LoginTest(IndividualUS);

        AccountType acc_type_obj = new AccountType();
        int acc_type = acc_type_obj.AccountTypeCheck();

        Investment TestInvestment = new Investment();
        TestInvestment.Investment(acc_type,"ACH","100");
        TestInvestment.Investment(acc_type,"Wire","100");
        TestInvestment.Investment(acc_type,"Bitcoin","0.5");
        TestInvestment.Investment(acc_type,"Ethereum","0.5");
    }

    //@Test
    public void Thirdtest() throws Exception{
        System.out.println("3 test");
        PagesURL URL = new PagesURL();
        URL.LoginPage();

//        Maintenance maintenance = new Maintenance();
//        if (maintenance.MaintenancePageCheck()){
//            return;
//        }

        Login L = new Login();
        L.LoginTest(EntityNoUS);

        AccountType acc_type_obj = new AccountType();
        int acc_type = acc_type_obj.AccountTypeCheck();

        Investment TestInvestment = new Investment();
        TestInvestment.Investment(acc_type,"ACH","100");
        TestInvestment.Investment(acc_type,"Wire","100");
        TestInvestment.Investment(acc_type,"Bitcoin","0.5");
        TestInvestment.Investment(acc_type,"Ethereum","0.5");
    }

    //@Test
    public void Fourthtest() throws Exception{
        System.out.println("4 test");
        PagesURL URL = new PagesURL();
        URL.LoginPage();

//        Maintenance maintenance = new Maintenance();
//        if (maintenance.MaintenancePageCheck()){
//            return;
//        }

        Login L = new Login();
        L.LoginTest(IndividualNoUS);

        AccountType acc_type_obj = new AccountType();
        int acc_type = acc_type_obj.AccountTypeCheck();

        Investment TestInvestment = new Investment();
        TestInvestment.Investment(acc_type,"ACH","100");
        TestInvestment.Investment(acc_type,"Wire","100");
        TestInvestment.Investment(acc_type,"Bitcoin","0.5");
        TestInvestment.Investment(acc_type,"Ethereum","0.5");
    }

    //@Test
    public void Fifthtest() throws Exception{
        System.out.println("5 test");
        PagesURL URL = new PagesURL();
        URL.LoginPage();

//        Maintenance maintenance = new Maintenance();
//        if (maintenance.MaintenancePageCheck()){
//            return;
//        }

        Login L = new Login();
        L.LoginTest(IndividualUSNoAccredited);

        AccountType acc_type_obj = new AccountType();
        int acc_type = acc_type_obj.AccountTypeCheck();

        Investment TestInvestment = new Investment();
        TestInvestment.Investment(acc_type,"ACH","100");
        TestInvestment.Investment(acc_type,"Wire","100");
        TestInvestment.Investment(acc_type,"Bitcoin","0.5");
        TestInvestment.Investment(acc_type,"Ethereum","0.5");
    }
}
