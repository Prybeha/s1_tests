package UnitClassSet.ContactUS;

import SupportClasses.SetupClass.SetupClass;
import org.testng.annotations.Test;

public class test_ContactUS extends SetupClass {
    private Raise_Funds_ContactUS_Form contact = new Raise_Funds_ContactUS_Form();

    @Test
    public void ContactUS_Raise_Funds() throws Exception{
        contact.Contact_US_Form();
    }
}
