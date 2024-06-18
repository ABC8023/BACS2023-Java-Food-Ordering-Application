public class LoginResult {
    private int menuCheck;
    private String membershipCardNumber;

    public LoginResult(int menuCheck, String membershipCardNumber) {
        this.menuCheck = menuCheck;
        this.membershipCardNumber = membershipCardNumber;
    }

    public int getMenuCheck() {
        return menuCheck;
    }

    public String getMembershipCardNumber() {
        return membershipCardNumber;
    }
}
