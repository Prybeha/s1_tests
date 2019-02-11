package DB;

public class db_registration_first_step {
    public static String[] FirstStep_UserName_Negative(){
        String[] TC_fn = {""};// last value must be correct for field
        return TC_fn;
    }
    public static String[] FirstStep_UserName_Positive(){
        String[] TC_fn = {"test","123456789","!@#$%^","test_UserName"};// last value must be correct for field
        return TC_fn;
    }
    public static String[] FirstStep_UserLastName_Negative(){
        String[] TC_ln = {""};// last value must be correct for field
        return TC_ln;
    }
    public static String[] FirstStep_UserLastName_Positive(){
        String[] TC_ln = {"test","123456789","!@#$%^","test_UserLastName"};// last value must be correct for field
        return TC_ln;
    }
    public static String[] FirstStep_Email_Negative() {
        String[] TC_email = {"","prybeha","prybehav+1@gmail.com","123456789","prybehav@","prybehav@gmail","prybehavgmail.com","prybehav.123@com","p@.","prybehav@gmail.com"};
        return TC_email;
    }
    public static String[] FirstStep_Email_Positive(String correct_email) {
        String[] TC_email = {correct_email};// add new values for check before correct_mail
        return TC_email;
    }
    public static String[] FirstStep_Pass_Negative() {
        String[] TC_pass = {"","1234567"};// add new values for check before correct_mail
        return TC_pass;
    }
    public static String[] FirstStep_Pass_Positive() {
        String[] TC_pass = {"12345678","aq1sw2de3","!@#$%^&*(","qweeerty","Aq1sw2de3"};// add new values for check before correct_mail
        return TC_pass;
    }
}
