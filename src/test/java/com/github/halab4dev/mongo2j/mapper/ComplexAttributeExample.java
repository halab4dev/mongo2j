package com.github.halab4dev.mongo2j.mapper;

/*
 *
 * @author halab
 */
public class ComplexAttributeExample {

    private String name;
    private Address address;

    public ComplexAttributeExample() {
    }

    public ComplexAttributeExample(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ComplexAttributeExample{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    public static class Address {

        private String street;
        private String city;

        public Address() {
        }

        public Address(String street, String city) {
            this.street = street;
            this.city = city;
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
                    "street='" + street + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }
}
