import java.time.LocalDate;
import java.util.Comparator;

public class Person {
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private Address address;

    /**
     * the constructor for class Person
     *
     * @param pFirst first name parameter
     * @param pLast last name parameter
     * @param pStreet street name parameter
     * @param pZip zip code parameter
     * @param pCity city name parameter
     * @param pBirthday date of birth parameter
     * @throws IllegalArgumentException if first name, last name or date of birth is empty
     */
    public Person(String pFirst, String pLast, String pStreet, int pZip, String pCity, LocalDate pBirthday) throws IllegalArgumentException {
        if (pFirst == null || pFirst.isEmpty()) {
            throw new IllegalArgumentException("first name must not be empty");
        }
        this.firstname = pFirst;
        if (pLast == null || pLast.isEmpty()) {
            throw new IllegalArgumentException("last name must not be empty");
        }
        this.lastname = pLast;

        this.address = new Address(pStreet, pZip, pCity);

        if (pBirthday == null) {
            throw new IllegalArgumentException("birthday must not be empty");
        }

        this.birthday = pBirthday;
    }

    /**
     *
     * @return Years of age.
     */
    public int getAge() {
        LocalDate current = LocalDate.now();

        if (current.getDayOfYear() < birthday.getDayOfYear()) { //Birthday is after this date
            return current.getYear() - birthday.getYear() - 1;
        } else { //Birthday was before this date (or is happening right now, Happy birthday!)
            return current.getYear() - birthday.getYear();
        }

    }

    /**
     * Checks if this object is equal to the given one
     *
     * @param obj the object that is to be compared
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Person p = (Person) obj;

        /* Two persons are the same, when they have the same name, birthday and address. */
        return firstname.equals(p.getFirstname()) && lastname.equals(p.getLastname()) && birthday.equals(p.getBirthday()) && address.equals(p.getAddress());

    }

    /**
     * Returns a hashcode value for the object
     *
     * @return a hashcode value
     */
    @Override
    public int hashCode() {
        //Stichwort Hash-Kollision???
        return this.toString().hashCode();
    }

    /**
     * Outputs all data to a person object
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Person{" +
                "Firstname='" + firstname + '\'' +
                ", Lastname='" + lastname + '\'' +
                ", Birthday=" + birthday +
                ", Address=" + address.toString() +
                '}';
    }

    /**
     * Static method to create a Comparator object, which simplifies comparing the age of two Persons
     *
     * @return Comparator for the age variable
     */
    public static Comparator<Person> ageComparator() {
        return (o1, o2) -> o1.getAge() - o2.getAge();
    }

    /**
     * Static method to create a Comparator object, which simplifies comparing the first name of two Persons
     *
     * @return Comparator for the first name variable
     */
    public static Comparator<Person> firstNameComparator() {
        return (o1, o2) -> o1.getFirstname().compareTo(o2.getFirstname());
    }

    /**
     * Static method to create a Comparator object, which simplifies comparing the last name of two Persons
     *
     * @return Comparator for the last name variable
     */
    public static Comparator<Person> lastNameComparator() {
        return (o1, o2) -> o1.getLastname().compareTo(o2.getLastname());
    }

    /**
     * Checks if two person objects have equal address objects
     *
     * @param p person object
     * @return true or false
     */
    public boolean livingAtTheSameAddress(Person p) {
        return this.getAddress().equals(p.getAddress());
    }

    /**
     * Changes Address to new one
     *
     * @param pAddress an address object
     * @throws IllegalArgumentException in case pAddress is null
     */
    public void setAddress(Address pAddress) throws IllegalArgumentException { //maybe NullPointerException?
        if (pAddress == null) {
            throw new IllegalArgumentException("Address must not be empty");
        } else {
            this.address = pAddress;
        }
    }


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Address getAddress() {
        return address;
    }
}
