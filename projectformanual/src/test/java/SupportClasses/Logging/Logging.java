package SupportClasses.Logging;

import SupportClasses.AllureFunc.LogUtil;
import org.apache.log4j.Logger;

public class Logging {
    public static void Log_info(Logger log, String message){
        log.info(message);
        LogUtil.log(message);
    }
    public static void Log_debug(Logger log, String message){
        log.debug(message);
        LogUtil.log(message);
    }
    public static void Log_warning(Logger log, String message){
        log.warn(message);
        LogUtil.log(message);
    }
    public static void Log_error(Logger log, String message){
        log.error(message);
        LogUtil.log(message);
    }
}
