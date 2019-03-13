import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;


public class PersonTest {

    @Test
    public void constructorNormalValues(){
        Person p = new Person("Erika","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", LocalDate.of(1964,8,12));

        assertNotNull(p);

        //testing if parameters are saved
        assertEquals("Erika", p.getFirstname());
        assertEquals("Musterfrau", p.getLastname());
        assertNotNull(p.getAddress());
        assertNotNull(p.getBirthday());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWrongFirstname(){
        new Person("","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", LocalDate.of(1964,8,12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWrongLastname(){
        new Person("Erika",null,"Am Hauptbahnhof 1", 12345, "Köln", LocalDate.of(1964,8,12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWrongAdress(){
        new Person("Erika","Musterfrau",null, 12345, "Köln", LocalDate.of(1964,8,12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWrongBirthday(){
        new Person("Erika","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", null);
    }

    @Test
    public void constructorExtremeName(){
        new Person("a","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", LocalDate.of(1964,8,12));
    }

    @Test
    public void constructorExtremeZip(){
        new Person("Erika","Musterfrau","Am Hauptbahnhof 1", Integer.MAX_VALUE, "Köln", LocalDate.of(1964,8,12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMissingAddress(){
        Person p = new Person("Erika","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", LocalDate.of(1964,8,12));

        p.setAddress(null);
    }

    @Test
    public void testingEquals(){
        Person first = new Person("Erika","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", LocalDate.of(1964,8,12));

        assertEquals(first, first);
        assertNotEquals(first, null);

        Person second = new Person("Max","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", LocalDate.of(1964,8,12));
        assertNotEquals(first, second);
        assertNotEquals(second, first);

        Person third = new Person("Erika","Musterfrau","Am Hauptbahnhof 1", 12345, "Köln", LocalDate.of(1964,8,12));
        assertEquals(third, first);
        assertEquals(first,third);
    }


    /*
    Idee: Geburtstag der Testperson in Abhängigkeit zum aktuellen Datum erstellen. (also z.B. aktuelles Datum -5 Tage)
     */
    @Test
    public void testingAge(){
        LocalDate now = LocalDate.now();

        LocalDate sixtyyears = now.minusYears(60);

        //Birthday today
        Person p = new Person("Erika", "Musterfrau", "Am Hauptbahnhof 1", 12345, "Köln", sixtyyears);
        assertNotEquals(p.getAge(),0);
        assertEquals(p.getAge(), 60);

        //Birthday 5 days ago
        p = new Person("Erika", "Musterfrau", "Am Hauptbahnhof 1", 12345, "Köln", sixtyyears.minusDays(5));
        assertNotEquals(p.getAge(), 0);
        assertEquals(p.getAge(),60);

        //Birthday in 5 days
        p = new Person("Erika", "Musterfrau", "Am Hauptbahnhof 1", 12345, "Köln", sixtyyears.plusDays(5));
        assertNotEquals(p.getAge(), 0);
        assertEquals(p.getAge(), 59);
    }
}
