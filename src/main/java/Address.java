public class Address {

    // https://www.mjt.me.uk/posts/falsehoods-programmers-believe-about-addresses/

    private String street;
    private int zipcode;
    private String city;

    public Address(String pStreet, int pzip, String pCity) throws IllegalArgumentException{
        if(pStreet == null || pStreet.isEmpty()){
            throw new IllegalArgumentException("Street must not be empty");
        }
        if(pzip < 0){
            throw new IllegalArgumentException("Zip-Code must not be negative");
        }
        if(pCity == null || pCity.isEmpty()){
            throw new IllegalArgumentException("City must not be empty");
        }

        this.street = pStreet;
        this.zipcode = pzip;
        this.city = pCity;

    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        if(this == obj){
            return true;
        }
        Address a = (Address) obj;
        //Two Addresses are the same, when the street, the zip code and the city is the same.
        return (a.getStreet().equals(street) && a.getZipcode() == zipcode && a.getCity().equals(city));
    }

    @Override
    public String toString() {
        return street +"\n"+zipcode+" "+city;
    }

    //TODO hashcode-Methode?

    public String getStreet() {
        return street;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

}
