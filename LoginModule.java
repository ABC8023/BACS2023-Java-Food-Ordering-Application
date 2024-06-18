import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoginModule {

    public LoginResult customerLogin() {
        Scanner scanner = new Scanner(System.in);
        int menuCheck = 0; // Initialize menuCheck
        String membershipCardNumber = null;
        String Yellow = "\u001B[33m";
        String RESET = "\u001B[0m";
        while (true) { // Loop for retrying login
            System.out.println(Yellow + "\t\tBack to main menu. (Press 0)"+ RESET);
            System.out.print("\n\t\tEnter membership card number for customer login: ");
            membershipCardNumber = scanner.nextLine();

            if (membershipCardNumber.equals("0")) {
                return new LoginResult(0, null); // Return menuCheck as 0 to go back to the previous menu
            }

            try {
                BufferedReader reader = new BufferedReader(new FileReader("customerDetails.txt"));
                String line;
                boolean customerFound = false; // Flag to check if customer is found

                while ((line = reader.readLine()) != null) {

                    String[] parts = line.split(",");
                    if (parts[0].equals(membershipCardNumber)) {
                        // Customer found, display details
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println(Yellow + "\n\n\n\n\t\tLogin Successful" + RESET);
                        // System.out.println("\n\t\tMembership Card Number: " + parts[0]);
                        // System.out.println("\t\tCustomer Name: " + parts[1]);
                        // System.out.println("\t\tContact Number: " + parts[2]);
                        // System.out.println("\t\tBirthday: " + parts[3]);
                        // System.out.println("\t\tCountry: " + parts[4]);
                        // System.out.println("\t\tMembership Points: " + parts[5]);

                        // // Debug information
                        // System.out.println("\n\t\tDEBUG: Membership Card Number: " + parts[0]);
                        // System.out.println("\t\tDEBUG: Customer Name: " + parts[1]);
                        // System.out.println("\t\tDEBUG: Contact Number: " + parts[2]);
                        // System.out.println("\t\tDEBUG: Birthday: " + parts[3]);
                        // System.out.println("\t\tDEBUG: Country: " + parts[4]);
                        // System.out.println("\t\tDEBUG: Membership Points: " + parts[8]);

                        reader.close();
                        customerFound = true;
                        menuCheck = 1; // Set menuCheck to 1 for successful login
                        break;
                    }
                }
                reader.close();
                if (customerFound) {
                    break; // Exit the loop if customer is found
                } else {

                    System.out.println(Yellow + "\t\tCustomer not found. Please try again." + RESET);
                }
            } catch (IOException e) {
                System.out.println("\t\tError reading customer details file.");
                e.printStackTrace();
            }

        }
        return new LoginResult(menuCheck, membershipCardNumber);// Return menuCheck and membershipCardNumber
    }

    public static void staffLogin(Staff staff) {
        Scanner scanner = new Scanner(System.in);
        String staffID = staff.getStaffID();
        String password = staff.getPassword();

        if (staffID.equals("2307577") && password.equals("123456")) {
            staff.displayInformation();
            ManagementMenuModule.viewCustomerDetails();
        } else {
            String Yellow = "\u001B[33m";
            String RESET = "\u001B[0m";
            System.out.println(Yellow + "\t\tInvalid staff credentials. Press ENTER one more time." + RESET);

            scanner.nextLine();
        }
    }

}
