public class Person {
    private String personName;
    private String contactNumber;
    private String birthday;
    private String country;

    // Constructor
    public Person(String personName, String contactNumber, String country, String birthday) {
        this.personName = personName;
        this.contactNumber = contactNumber;
        this.birthday = birthday;
        this.country = country;
    }

    // Getter methods
    public String getPersonName() {
        return personName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCountry() {
        return country;
    }
}