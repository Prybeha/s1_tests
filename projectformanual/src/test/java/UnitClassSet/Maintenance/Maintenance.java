package UnitClassSet.Maintenance;

import UnitClassSet.Field;

public class Maintenance {
    private Field field = new Field();
    public boolean MaintenancePageCheck(){
        if(field.ExistElementOnThePage("//*[@class='maintenance-text']",2)){
            System.out.println("Maintenance mode is on!");
            return true;
        }
        return false;
    }
}
