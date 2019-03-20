import org.junit.Test;

import java.time.LocalDate;
import java.util.Comparator;

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
        assertNotNull(p.getDateOfBirth());
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

    @Test
    public void ageComparatorTestEqual(){
        Person a = new Person("Alice","Müller", "Hauptstraße", 12345,"Dülmen" ,LocalDate.of(1963,6,2));
        Person b = new Person("Bob", "Müller", "Am See" , 12345, "Dülmen", LocalDate.of(1963,6,2));

        Comparator<Person> comp = Person.ageComparator();
        assertEquals(0, comp.compare(a,b));
        assertEquals(0, comp.compare(b,a));
    }

    @Test
    public void ageComparatorTestGreaterThan(){
        Person a = new Person("Alice","Müller", "Hauptstraße", 12345,"Dülmen" ,LocalDate.of(1960,6,2));
        Person b = new Person("Bob", "Müller", "Am See" , 12345, "Dülmen", LocalDate.of(1963,6,2));

        Comparator<Person> comp = Person.ageComparator();
        assertTrue(comp.compare(a, b) > 0);
        assertFalse(comp.compare(b, a) > 0);
    }

    @Test
    public void ageComparatorTestLessThan(){
        Person a = new Person("Alice","Müller", "Hauptstraße", 12345,"Dülmen" ,LocalDate.of(1963,6,2));
        Person b = new Person("Bob", "Müller", "Am See" , 12345, "Dülmen", LocalDate.of(1961,6,2));

        Comparator<Person> comp = Person.ageComparator();
        assertTrue(comp.compare(a, b) < 0);
        assertFalse(comp.compare(b, a) < 0);
    }

    @Test
    public void firstNameComparatorTestEqual(){
        Person a = new Person("Alice","Müller", "Hauptstraße", 12345,"Dülmen" ,LocalDate.of(1963,6,2));
        Person b = new Person("Alice", "Müller", "Am See" , 12345, "Dülmen", LocalDate.of(1958,6,2));

        Comparator<Person> comp = Person.firstNameComparator();
        assertEquals(0, comp.compare(a, b));
        assertEquals(0, comp.compare(b, a));
        assertEquals(0, comp.compare(a, a));
    }

    @Test
    public void firstNameComparatorTestGreaterThan(){
        Person a = new Person("Alice","Müller", "Hauptstraße", 12345,"Dülmen" ,LocalDate.of(1963,6,2));
        Person b = new Person("Bob", "Müller", "Am See" , 12345, "Dülmen", LocalDate.of(1958,6,2));

        Comparator<Person> comp = Person.firstNameComparator();
        assertTrue(comp.compare(b,a) > 0);
        assertFalse(comp.compare(a, b) > 0);
    }

    @Test
    public void firstNameComparatorTestLessThan(){
        Person a = new Person("Alice","Müller", "Hauptstraße", 12345,"Dülmen" ,LocalDate.of(1963,6,2));
        Person b = new Person("Bob", "Müller", "Am See" , 12345, "Dülmen", LocalDate.of(1958,6,2));

        Comparator<Person> comp = Person.firstNameComparator();
        assertTrue(comp.compare(a, b) < 0);
        assertFalse(comp.compare(b, a) < 0);
    }

    @Test
    public void lastNameComparatorTestEqual(){
        Person a = new Person("Alice","Müller", "Hauptstraße", 12345,"Dülmen" ,LocalDate.of(1963,6,2));
        Person b = new Person("Alice", "Müller", "Am See" , 12345, "Dülmen", LocalDate.of(1958,6,2));

        Comparator<Person> comp = Person.lastNameComparator();
        assertEquals(0, comp.compare(a, b));
        assertEquals(0, comp.compare(b, a));
        assertEquals(0, comp.compare(a, a));
    }

    @Test
    public void lastNameComparatorTestGreaterThan(){
        Person a = new Person("Alice","Müller", "Hauptstraße", 12345,"Dülmen" ,LocalDate.of(1963,6,2));
        Person b = new Person("Bob", "Zernial", "Am See" , 12345, "Dülmen", LocalDate.of(1958,6,2));

        Comparator<Person> comp = Person.lastNameComparator();
        assertTrue(comp.compare(b,a) > 0);
        assertFalse(comp.compare(a, b) > 0);
    }

    @Test
    public void lastNameComparatorTestLessThan(){
        Person a = new Person("Alice","Müller", "Hauptstraße", 12345,"Dülmen" ,LocalDate.of(1963,6,2));
        Person b = new Person("Bob", "Zernial", "Am See" , 12345, "Dülmen", LocalDate.of(1958,6,2));

        Comparator<Person> comp = Person.lastNameComparator();
        assertTrue(comp.compare(a, b) < 0);
        assertFalse(comp.compare(b, a) < 0);
    }

    @Test
    public void livingAtSameAddressTest(){
        Person a = new Person("Alice","Müller", "Hauptstraße", 12345,"Dülmen" ,LocalDate.of(1963,6,2));
        Person b = new Person("Bob", "Müller", "Am See" , 12345, "Dülmen", LocalDate.of(1958,6,2));
        Person c = new Person("Hans", "Schmidt", "Hauptstraße", 12345, "Dülmen", LocalDate.of(1990, 12, 21));

        assertTrue(a.livingAtTheSameAddress(a));
        assertTrue(a.livingAtTheSameAddress(c));
        assertFalse(a.livingAtTheSameAddress(b));
        assertFalse(b.livingAtTheSameAddress(c));
    }
}
