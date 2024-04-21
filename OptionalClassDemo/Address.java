package OptionalClassDemo;

public class Address {
    private int no;
    private String country;

    public Address(int no, String country) {
        this.no = no;
        this.country = country;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
