package UnitClassSet.ContactUS;

import UnitClassSet.Field;
import UnitClassSet.PagesURL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Raise_Funds_ContactUS_Form {
    private Field field = new Field();
    private PagesURL url = new PagesURL();
    public void Contact_US_Form() throws Exception{
        url.FundsPage();

        // fill in fields for creating SF lead
        field.EnterValue("//input[@id='start_raise_firstName']","test");
        field.EnterValue("//input[@id='start_raise_lastName']","test");
        Scanner reader = new Scanner(new File("src/test/java/UnitClassSet/Registration/id.txt"));
        int id = reader.nextInt(); // id > unique number for email, that reads from the file and writes bigger on one.

        field.EnterValue("//input[@id='start_raise_email']","prybehav+lead_" + id + "@gmail.com");
        id++;
        String str = Integer.toString(id);
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/java/UnitClassSet/ContactUS/lead_id.txt"));
        writer.write(str);
        writer.close();
        field.EnterValue("//input[@id='start_raise_description']","test description");
    }
}
