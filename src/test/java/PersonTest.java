import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;


public class PersonTest {

    @Test
    public void constructorNormalValues(){
        Person p = new Person("Erika","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", new GregorianCalendar(1964, Calendar.AUGUST, 12));

        assertNotNull(p);

        //testing if parameters are saved
        assertEquals("Erika", p.getFirstname());
        assertEquals("Musterfrau", p.getLastname());
        assertNotNull(p.getAddress());
        assertNotNull(p.getBirthday());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWrongFirstname(){
        Person p = new Person("","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", new GregorianCalendar(1964,Calendar.AUGUST, 12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWrongLastname(){
        Person p = new Person("Erika",null,"Am Hauptbahnhof 1", 12345, "Köln", new GregorianCalendar(1964,Calendar.AUGUST, 12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWrongAdress(){
        Person p = new Person("Erika","Musterfrau",null, 12345, "Köln", new GregorianCalendar(1964,Calendar.AUGUST, 12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWrongBirthday(){
        Person p = new Person("Erika","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", null);
    }

    @Test
    public void constructorExtremeName(){
        Person p = new Person("a","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", new GregorianCalendar(1964,Calendar.AUGUST, 12));
    }

    @Test
    public void constructorExtremeZip(){
        Person p = new Person("Erika","Musterfrau","Am Hauptbahnhof 1", Integer.MAX_VALUE, "Köln", new GregorianCalendar(1964,Calendar.AUGUST, 12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMissingAddress(){
        Person p = new Person("Erika","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", new GregorianCalendar(1964,Calendar.AUGUST, 12));

        p.setAddress(null);
    }

    @Test
    public void testingEquals(){
        Person first = new Person("Erika","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", new GregorianCalendar(1964,Calendar.AUGUST, 12));

        assertEquals(first, first);
        assertNotEquals(first, null);

        Person second = new Person("Max","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", new GregorianCalendar(1964,Calendar.AUGUST, 12));
        assertNotEquals(first, second);
        assertNotEquals(second, first);

        Person third = new Person("Erika","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", new GregorianCalendar(1964,Calendar.AUGUST, 12));
        assertEquals(third, first);
        assertEquals(first,third);
    }

    //TODO getAge testen... aber wie?

    /*
    Idee: Geburtstag der Testperson in Abhängigkeit zum aktuellen Datum erstellen. (also z.B. aktuelles Datum -5 Tage)
     */
}
