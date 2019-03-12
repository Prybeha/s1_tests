package UnitClassSet.Maintenance;

import SupportClasses.Exceptions.NewException;
import SupportClasses.Field;
import SupportClasses.Logging.Logging;
import org.apache.log4j.Logger;

public class Maintenance {
    private Field field = new Field();
    private Logger log = Logger.getLogger(Maintenance.class);

    public void MaintenancePageCheck() throws Exception{
        if(field.ExistElementOnThePage("//*[@class='maintenance-text']",2)){
            Logging.Log_warning(log, "Maintenance mode is on!");
            throw new NewException("Maintenance mode is on!");
        }
    }
}
