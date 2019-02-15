package UnitClassSet.ContactUS;

import SupportClasses.SetupClass.SetupClass;
import org.testng.annotations.Test;

public class test_ContactUS extends SetupClass {
    private Raise_Funds_ContactUS_Form contact = new Raise_Funds_ContactUS_Form();

    @Test
    public void ContactUSForms_Raise_Funds() throws Exception{
        contact.SeriesOne_FillInContactUSForm(1);
    }

    @Test
    public void ContactUSForms_License_Platform() throws Exception{
        contact.SeriesOne_FillInContactUSForm(2);
    }

    @Test
    public void ContactUSForms_ContactUS() throws Exception{
        contact.SeriesOne_FillInContactUSForm(3);
    }
}
