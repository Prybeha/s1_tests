package DB;

public class db_registration_third_step_E {
    public static String[] ThirdStep_EntityName_Negative(){
        String[] TC_en = {""};
        return TC_en;
    }
    public static String[] ThirdStep_EntityName_Positive(){
        String[] TC_en = {"test","123456789","!@#$%^","test_EntityName"};
        return TC_en;
    }
    public static String[] ThirdStep_EntityAddress_Negative(){
        String[] TC_ea = {""};
        return TC_ea;
    }
    public static String[] ThirdStep_EntityAddress_Positive(){
        String[] TC_ea = {"test","123456789","!@#$%^","test_EntityAddress"};
        return TC_ea;
    }
    public static String[] ThirdStep_EntityCity_Negative(){
        String[] TC_ec = {""};
        return TC_ec;
    }
    public static String[] ThirdStep_EntityCity_Positive(){
        String[] TC_ec = {"test","123456789","!@#$%^","test_EntityCity"};
        return TC_ec;
    }
    public static String[] ThirdStep_EntityEmail_Negative() {
        String[] TC_email = {"","prybeha","123456789","prybehav@","prybehav@gmail","prybehavgmail.com","prybehav.123@com","p@."};
        return TC_email;
    }
    public static String[] ThirdStep_EntityEmail_Positive() {
        String[] TC_email = {"prybehav+test_EntityEmail@gmail.com"};
        return TC_email;
    }
    public static String[] ThirdStep_JobTitle_Negative() {
        String[] TC_email = {""};
        return TC_email;
    }
    public static String[] ThirdStep_JobTitle_Positive() {
        String[] TC_email = {"test","123456789","!@#$%^","test_JobTitle"};
        return TC_email;
    }
    public static String[] ThirdStep_ZipCode_Positive(){
        String[] TC_ci = {"test","!@#$%^","12345"};
        return TC_ci;
    }
    public static String[] ThirdStep_Phone_Positive(){
        String[] TC_ci = {"test","!@#$%^","1234567890"};
        return TC_ci;
    }
}
