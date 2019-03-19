
public class Address {

    private String street;
    private int zipCode;
    private String city;


    /**
     * The constructor for class Address
     *
     * @param pStreet Street name
     * @param pZip  zip code
     * @param pCity city name
     * @throws IllegalArgumentException if pStreet or pCity is Empty or pZip is negative
     */
    public Address(String pStreet, int pZip, String pCity) throws IllegalArgumentException{
        if(pStreet == null || pStreet.isEmpty()){
            throw new IllegalArgumentException("Street must not be empty");
        }
        if(pZip < 0){
            throw new IllegalArgumentException("Zip-Code must not be negative");
        }
        if(pCity == null || pCity.isEmpty()){
            throw new IllegalArgumentException("City must not be empty");
        }

        this.street = pStreet;
        this.zipCode = pZip;
        this.city = pCity;

    }

    /**
     * Checks if this object is equal to the given one
     *
     * @param obj The object that is to be compared
     * @return true or false
     */
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
        return (a.getStreet().equals(street) && a.getZipCode() == zipCode && a.getCity().equals(city));
    }

    /**
     * Outputs all parts of an address
     *
     * @return String
     */
    @Override
    public String toString() {
        return street +"\n"+ zipCode +" "+city;
    }

    /**
     * Returns a hashcode value for the object
     *
     * @return a hashcode value
     */
    @Override
    public int hashCode() {
        //Stichwort Hashkollision?
        return this.toString().hashCode();
    }

    public String getStreet() {
        return street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

}
