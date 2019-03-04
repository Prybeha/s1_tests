import SupportClasses.SetupClass.SetupClass;
import UnitClassSet.Gmail_Mailbox.EmailReceivingChecking_ContactUS;
import UnitClassSet.Gmail_Mailbox.EmailReceivingChecking_Registration;
import org.testng.annotations.Test;

public class TestClass extends SetupClass {

//    @Test
//    public void SupportTest1() throws Exception{
//        EmailReceivingChecking_Registration check = new EmailReceivingChecking_Registration();
//        check.Gmail_TestMailForRegistration();
//    }

    @Test
    public void SupportTest2() throws Exception{
        EmailReceivingChecking_ContactUS check = new EmailReceivingChecking_ContactUS();
        check.Gmail_TestMailForLeadCreation(1);
    }
}
