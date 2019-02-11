package UnitClassSet;
import SupportClasses.SetupClass.SetupClass;
import org.openqa.selenium.By;

public class Switchers {
    public void SwitcherManage(String locator_for_check, String locator_for_click, boolean expected_result){

        if (!SetupClass.GetDriver().findElement(By.id(locator_for_check)).isSelected() && expected_result) {
            SetupClass.GetDriver().findElement(By.xpath(locator_for_click)).click();
        }
        else if (SetupClass.GetDriver().findElement(By.id(locator_for_check)).isSelected() && !expected_result){
            SetupClass.GetDriver().findElement(By.xpath(locator_for_click)).click();
        }
    }
}
