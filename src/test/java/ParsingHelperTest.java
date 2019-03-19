import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;


public class ParsingHelperTest {

    @Test
    public void parsingTestNormal(){
        FileReader reader = null;

        try {
            reader = new FileReader(".//src/test/resources/normal.csv");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        LinkedList<Person> people = ParsingHelper.parsing(reader);

        assertNotNull(people);
        assertTrue(people.size() > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parsingTestEmptyLastName(){
        FileReader reader = null;

        try {
            reader = new FileReader(".//src/test/resources/emptyLastName.csv");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        ParsingHelper.parsing(reader);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parsingTestEmptyStreet(){
        FileReader reader = null;

        try {
            reader = new FileReader(".//src/test/resources/emptyStreet.csv");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        ParsingHelper.parsing(reader);
    }

    @Test(expected = NumberFormatException.class)
    public void parsingTestWrongZip(){
        FileReader reader = null;

        try {
            reader = new FileReader(".//src/test/resources/wrongZip.csv");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        ParsingHelper.parsing(reader);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parsingTestWrongDatePattern(){
        FileReader reader = null;

        try {
            reader = new FileReader(".//src/test/resources/dateOfBirthWrongPattern.csv");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        ParsingHelper.parsing(reader);
    }

}
