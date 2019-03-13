import org.junit.Test;

import static org.junit.Assert.*;

public class AddressTest {

    @Test
    public void constructorNormalValues() {
        Address address = new Address("Am Hauptbahnhof 1", 3975, "Hauptstadt");

        assertNotNull("Object should not be null", address);

        //testing if parameters are saved
        assertEquals("Street name was not saved", address.getStreet(), "Am Hauptbahnhof 1");
        assertEquals("City was not saved", address.getCity(), "Hauptstadt");
        assertEquals("Zip code was not saved", address.getZipcode(),3975);

    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWrongStreet() {
        Address address = new Address("", 65464, "Hallo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWrongCity() {
        Address address = new Address("Am Hauptbahnhof 1", 65464, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWrongZip() {
        Address address = new Address("Am Hauptbahnhof 1", -20, "Hauptstadt");
    }

    @Test
    public void constructorExtremeZip() {
        Address address = new Address("Am Hauptbahnhof 1", Integer.MAX_VALUE, "Hauptstadt");

        assertNotNull(address);

        assertEquals("Zip Code should be Integer.MAX_VALUE", address.getZipcode(), Integer.MAX_VALUE);
    }

    @Test
    public void testingEquals() {
        Address first = new Address("Am Hauptbahnhof 1", 3975, "Hauptstadt");
        Address second = new Address("Am Hauptbahnhof 1", 65464, "Hauptstadt");
        assertEquals(first,first);

        assertNotEquals(first, null);
        assertNotEquals(first,second);
        assertNotEquals(second, first);

        Address third = new Address("Am Hauptbahnhof 1", 3975, "Hauptstadt");
        assertEquals(first, third);
        assertEquals(third, first);
    }
}
