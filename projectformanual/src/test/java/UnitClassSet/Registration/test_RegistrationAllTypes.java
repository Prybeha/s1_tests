package UnitClassSet.Registration;

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

    @Test
    public void Register_IndividualUS() throws Exception{
        r.Register(1);
    }

    @Test
    public void Register_IndividualnoUS() throws Exception{
        r.Register(2);
    }

    @Test
    public void Register_EntityUS() throws Exception{
        r.Register(3);
    }

    @Test
    public void Register_EntitynoUS() throws Exception{
        r.Register(4);
    }

    @Test
    public void Register_IndividualUSnoAccredited() throws Exception{
        r.Register(5);
    }
}
