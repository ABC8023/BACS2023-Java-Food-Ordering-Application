public class Staff extends Person implements PersonalDetails{
    private String staffID;
    private String password;
    private String name;
    private String contactNumber;
    private String country;

    // Constructor
    public Staff(String personName, String contactNumber, String country, String birthday, String staffID, String password) {
        super(personName, contactNumber, country, birthday);
        this.staffID = staffID;
        this.password = password;
        this.name = personName;
        this.contactNumber = contactNumber;
        this.country = country;
    }

    // Getter methods
    public String getStaffID() {
        return staffID;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getCountry() {
        return country;
    }

    // Method to display staff information
    public void displayInformation() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n\t\tStaff Information:");
        System.out.println("\n\t\tName    : " + getName());
        System.out.println("\t\tStaff ID: " + getStaffID());
        System.out.println("\t\tContact : " + getContactNumber());
        System.out.println("\t\tCountry : " + getCountry());
    }

    // Method to perform staff login
    public void staffLogin() {
        LoginModule.staffLogin(this); // Pass the current Staff object
    }
}
