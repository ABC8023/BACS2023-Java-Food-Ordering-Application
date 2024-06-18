public class Customer extends Person implements PersonalDetails{
    private String membershipCardNumber;
    private int membershipPoints;
    private String voucherCode;
    private String voucherCode2;

    // Constructor
    public Customer(String personName, String contactNumber, String country, String birthday, String membershipCardNumber, int membershipPoints, String voucherCode, String voucher2) {
        super(personName, contactNumber, country, birthday);
        this.membershipCardNumber = membershipCardNumber;
        this.membershipPoints = membershipPoints;
        this.voucherCode = voucherCode != null ? voucherCode : "";
        this.voucherCode2 = voucher2 != null ? voucher2 : "";
    }

    // Getter methods
    public String getMembershipCardNumber() {
        return membershipCardNumber;
    }

    public int getMembershipPoints() {
        return membershipPoints;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

        public String getVoucherCode2() {
        return voucherCode2;
    }

    public void setVoucherCode2(String voucher2) {
        this.voucherCode2 = voucher2;
    }

    // Method to display customer information
    public void displayInformation() {
        System.out.println("\t\tMembership Card Number: " + getMembershipCardNumber());
        System.out.println("\t\tCustomer Name: " + getPersonName());
        System.out.println("\t\tContact Number: " + getContactNumber());
        System.out.println("\t\tBirthday: " + getBirthday());
        System.out.println("\t\tCountry: " + getCountry()); 
        System.out.println("\t\tMembership Points: " + getMembershipPoints());
    }

    
}
