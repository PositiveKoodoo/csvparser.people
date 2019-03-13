import java.time.LocalDate;

public class Person {
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private Address address;


    public Person(String pFirst, String pLast, String pStreet, int pZip, String pCity, LocalDate pBirthday) throws IllegalArgumentException {
        if (pFirst == null || pFirst.isEmpty()) {
            throw new IllegalArgumentException("Firstname must not be empty");
        }
        this.firstname = pFirst;
        if (pLast == null || pLast.isEmpty()) {
            throw new IllegalArgumentException("Lastname must not be empty");
        }
        this.lastname = pLast;

        try {
            this.address = new Address(pStreet, pZip, pCity);
        } catch (IllegalArgumentException e) {
            throw e;
        }

        if (pBirthday == null) {
            throw new IllegalArgumentException("Birthday must not be empty");
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
    public String toString() {
        return "Person{" +
                "Firstname='" + firstname + '\'' +
                ", Lastname='" + lastname + '\'' +
                ", Birthday=" + birthday +
                ", Address=" + address.toString() +
                '}';
    }

    public void setAddress(Address pAddress) throws IllegalArgumentException {
        if (pAddress == null) {
            throw new IllegalArgumentException("Address must not be empty");
        } else {
            this.address = pAddress;
        }
    }

    //TODO hashcode-Methode?

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
