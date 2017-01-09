package dev.halab.mongo2j;

/**
 * @author Apollo
 */
public class Address {

    private int no;
    private String street;
    private String city;

    public Address() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "Address{" +
                "no=" + no +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
