package UnitClassSet.Registration;

import UnitClassSet.Gmail_Mailbox.EmailReceivingChecking_Registration;
import SupportClasses.SetupClass.SetupClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class test_RegistrationAllTypes extends SetupClass {
    // user type indexes
    //1 - Individual US
    //2 - Individual noUS
    //3 - Entity US
    //4 - Entity noUS
    //5 - Individual no Accredited
    private Registration r = new Registration();

    @Title("Test register process for Individual US users")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Registration","Individual","US"})
    @Description("Go to registration page > go throw all steps using db values for check fields correct work > check data on the my 'My Account' page")
    @Test
    public void Register_IndividualUS() throws Exception{
        r.Register(1);
    }

    @Title("Test register email receiving process for Individual US users")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Registration","Check email receiving","Individual","US"})
    @Description("Go to gmail account > find all message with required title > check content if it's required email, otherwise delete message")
    @Test
    public void CheckRegisterEmail_IndividualUS() throws Exception{
        EmailReceivingChecking_Registration check = new EmailReceivingChecking_Registration();
        check.Gmail_TestMailForRegistration();
    }

    @Title("Test register process for Individual no US users")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Registration","Individual","no US"})
    @Description("Go to registration page > go throw all steps using db values for check fields correct work > check data on the my 'My Account' page")
    @Test
    public void Register_IndividualnoUS() throws Exception{
        r.Register(2);
    }

    @Title("Test register email receiving process for Individual no US users")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Registration","Check email receiving","Individual","no US"})
    @Description("Go to gmail account > find all message with required title > check content if it's required email, otherwise delete message")
    @Test
    public void CheckRegisterEmail_IndividualnoUS() throws Exception{
        EmailReceivingChecking_Registration check = new EmailReceivingChecking_Registration();
        check.Gmail_TestMailForRegistration();
    }

    @Title("Test register process for Entity US users")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Registration","Entity","US"})
    @Description("Go to registration page > go throw all steps using db values for check fields correct work > check data on the my 'My Account' page")
    @Test
    public void Register_EntityUS() throws Exception{
        r.Register(3);
    }

    @Title("Test register email receiving process for Entity US users")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Registration","Check email receiving","Entity","US"})
    @Description("Go to gmail account > find all message with required title > check content if it's required email, otherwise delete message")
    @Test
    public void CheckRegisterEmail_EntityUS() throws Exception{
        EmailReceivingChecking_Registration check = new EmailReceivingChecking_Registration();
        check.Gmail_TestMailForRegistration();
    }

    @Title("Test register process for Entity no US users")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Registration","Entity","no US"})
    @Description("Go to registration page > go throw all steps using db values for check fields correct work > check data on the my 'My Account' page")
    @Test
    public void Register_EntitynoUS() throws Exception{
        r.Register(4);
    }

    @Title("Test register email receiving process for Entity no US users")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Registration","Check email receiving","Entity","no US"})
    @Description("Go to gmail account > find all message with required title > check content if it's required email, otherwise delete message")
    @Test
    public void CheckRegisterEmail_EntitynoUS() throws Exception{
        EmailReceivingChecking_Registration check = new EmailReceivingChecking_Registration();
        check.Gmail_TestMailForRegistration();
    }

    @Title("Test register process for Individual US no Accredited users")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Registration","Individual","US no Accredited"})
    @Description("Go to registration page > go throw all steps using db values for check fields correct work > check data on the my 'My Account' page")
    @Test
    public void Register_IndividualUSnoAccredited() throws Exception{
        r.Register(5);
    }

    @Title("Test register email receiving process for Individual US no Accredited users")
    @Severity(SeverityLevel.CRITICAL)
    @Features({"Registration","Check email receiving","Individual","US no Accredited"})
    @Description("Go to gmail account > find all message with required title > check content if it's required email, otherwise delete message")
    @Test
    public void CheckRegisterEmail_IndividualUSnoAccredited() throws Exception{
        EmailReceivingChecking_Registration check = new EmailReceivingChecking_Registration();
        check.Gmail_TestMailForRegistration();
    }
}
