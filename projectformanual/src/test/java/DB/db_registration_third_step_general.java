package DB;

public class db_registration_third_step_general {
    public static String[] ThirdStep_UserName_Negative(){
        String[] TC_fn = {""};
        return TC_fn;
    }
    public static String[] ThirdStep_UserName_Positive(){
        String[] TC_fn = {"test","123456789","!@#$%^","test_newUserName"};
        return TC_fn;
    }
    public static String[] ThirdStep_UserLastName_Negative(){
        String[] TC_ln = {""};
        return TC_ln;
    }
    public static String[] ThirdStep_UserLastName_Positive(){
        String[] TC_ln = {"test","123456789","!@#$%^","test_newUserLastName"};
        return TC_ln;
    }
    public static String[] ThirdStep_Address_Negative(){
        String[] TC_ad = {""};
        return TC_ad;
    }
    public static String[] ThirdStep_Address_Positive(){
        String[] TC_ad = {"test","123456789","!@#$%^","test_Address"};
        return TC_ad;
    }
    public static String[] ThirdStep_City_Negative(){
        String[] TC_ci = {""};
        return TC_ci;
    }
    public static String[] ThirdStep_City_Positive(){
        String[] TC_ci = {"test","123456789","!@#$%^","test_City"};
        return TC_ci;
    }
    public static String[] ThirdStep_Phone_Negative(){
        String[] TC_ci = {"test","!@#$%^"};
        return TC_ci;
    }
    public static String[] ThirdStep_Phone_Positive(){
        String[] TC_ci = {"1234567890"};
        return TC_ci;
    }
    public static String[] ThirdStep_ZipCode_Negative(){
        String[] TC_ci = {"","test","!@#$%^","1234"};
        return TC_ci;
    }
    public static String[] ThirdStep_ZipCode_Positive(){
        String[] TC_ci = {"12345"};
        return TC_ci;
    }
    public static String[] ThirdStep_Address2_Negative(){
        String[] TC_ad = {""};
        return TC_ad;
    }
    public static String[] ThirdStep_Address2_Positive(){
        String[] TC_ad = {"test","123456789","!@#$%^","test_Address2"};
        return TC_ad;
    }
}
