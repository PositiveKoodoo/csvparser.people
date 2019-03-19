import java.time.LocalDate;
import java.util.Comparator;

public class Person {
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private Address address;


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

    public int getAge() {
        LocalDate current = LocalDate.now();

        if (current.getDayOfYear() < birthday.getDayOfYear()) { //Birthday is after this date
            return current.getYear() - birthday.getYear() - 1;
        } else { //Birthday was before this date (or is happening right now, Happy birthday!)
            return current.getYear() - birthday.getYear();
        }

    }

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

    @Override
    public int hashCode() {
        //Stichwort Hash-Kollision???
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return "Person{" +
                "Firstname='" + firstname + '\'' +
                ", Lastname='" + lastname + '\'' +
                ", Birthday=" + birthday +
                ", Address=" + address.toString() +
                '}';
    }

    public Comparator<Person> ageComparator() {
        return (o1, o2) -> o1.getAge() - o2.getAge();
    }

    public Comparator<Person> firstNameComparator() {
        return (o1, o2) -> o1.getFirstname().compareTo(o2.getFirstname());
    }

    public Comparator<Person> lastNameComparator() {
        return (o1, o2) -> o1.getLastname().compareTo(o2.getLastname());
    }

    public boolean livingAtTheSameAddress(Person p) {
        return this.getAddress().equals(p.getAddress());
    }

    public void setAddress(Address pAddress) throws IllegalArgumentException {
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
