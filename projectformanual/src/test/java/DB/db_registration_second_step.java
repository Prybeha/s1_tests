package DB;

public class db_registration_second_step {
    public static String[] SecondStep_Switchers_Negative(){
        String[] TC_switchers = {"00"};// 0 value means that switcher take false value, 1 - takes true.
        return TC_switchers;
    }
    public static String[] SecondStep_Switchers_Positive(){
        String[] TC_switchers = {"10","01","11"};// 0 value means that switcher take false value, 1 - takes true.
        return TC_switchers;
    }
    public static String[] SecondStep_NetWorth_Negative(){
        String[] TC_nw = {"qwerty","!@#$%^","-200,000.00"};
        return TC_nw;
    }
    public static String[] SecondStep_NetWorth_Positive(){
        String[] TC_nw = {"200000","200,000","200000.00","200,000.00"};
        return TC_nw;
    }
    public static String[] SecondStep_Income_Negative(){
        String[] TC_in = {"qwerty","!@#$%^","-20,000.00"};
        return TC_in;
    }
    public static String[] SecondStep_Income_Positive(){
        String[] TC_in = {"20000","20,000","20000.00","20,000.00"};
        return TC_in;
    }
}
