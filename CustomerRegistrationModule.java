import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomerRegistrationModule {
    private static Scanner scanner = new Scanner(System.in);
    private static final String FILENAME = "customerDetails.txt"; // File to store customer details

    public static Person registerNewPerson() {
        String Yellow = "\u001B[33m";
        String RESET = "\u001B[0m";
        int cont = 0;
        do {
            // Prompt user for person details
            System.out.print("\n\t\tEnter person name: ");
            String personName = getValidPersonName();
            System.out.print("\t\tEnter contact number: ");
            String contactNumber = getValidContactNumber();
            System.out.print("\t\tEnter country: ");
            String country = getValidCountry();
            String birthday = getValidBirthday();
            do {
                // Prompt user for confirmation
                System.out.println(Yellow + "\n\t\tAre you sure the details are correct?" + RESET);
                System.out.println(Yellow + "\t\t1=yes, 2=no" + RESET);
                System.out.print("\n\t\tEnter your choice: ");

                // Validate input
                if (scanner.hasNextInt()) {
                    cont = scanner.nextInt();
                    if (cont == 1 || cont == 2) {

                        break; // Exit loop if input is valid
                    } else {
                        System.out.println(Yellow + "\t\tInvalid choice. Please enter 1 or 2." + RESET);
                        scanner.nextLine();
                    }
                } else {
                    System.out.println(Yellow + "\t\tInvalid input. Please enter a number." + RESET);
                    scanner.nextLine(); // Consume invalid input
                }
            } while (true);

            if (cont == 1) {
                // Create a new Person object with provided details
                return new Person(personName, contactNumber, country, birthday);
            }
        scanner.nextLine();
        } while (cont == 2);
        
        return null;

    }

    public static Customer registerNewCustomer() {
        Person newPerson = registerNewPerson();

        // Generate random membership card number
        String membershipCardNumber = "CBS" + String.format("%05d", (int) (Math.random() * 100000));

        String voucherCodeForCustomer = "none"; // Set the voucher code to an empty string by default
        String voucherCodeForCustomer2 = "none"; // Set the voucher code to an empty string by default

        // Assuming you have some logic to determine which type of voucher code to
        // assign
        /*
         * if (conditionForV020) {
         * voucherCodeForCustomer = "V020";
         * } else if (conditionForV010) {
         * voucherCodeForCustomer = "V010";
         * }
         */

        // Create a new Customer object inheriting from Person with provided details
        // including membership info
        Customer newCustomer = new Customer(newPerson.getPersonName(), newPerson.getContactNumber(),
                newPerson.getCountry(), newPerson.getBirthday(), membershipCardNumber, 0, voucherCodeForCustomer,
                voucherCodeForCustomer2);

        // Write customer details to file
        writeCustomerToFile(newCustomer);

        return newCustomer;
    }

    private static void writeCustomerToFile(Customer newCustomer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            // Write customer details to file in the specified format
            writer.write(newCustomer.getMembershipCardNumber() + "," +
                    newCustomer.getPersonName() + "," +
                    newCustomer.getContactNumber() + "," +
                    newCustomer.getBirthday() + "," +
                    newCustomer.getCountry() + "," +
                    newCustomer.getMembershipPoints() + "," +
                    newCustomer.getVoucherCode() + "," +
                    newCustomer.getVoucherCode2() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getValidPersonName() {
        String Yellow = "\u001B[33m";
        String RESET = "\u001B[0m";
        String personName = scanner.nextLine().trim(); // Read customer name input

        // Validate customer name (alphabet characters only)
        while (!personName.matches("[a-zA-Z ]+")) {
            personName = scanner.nextLine().trim();

            System.out.println(
                    Yellow + "\t\tInvalid input. Please enter alphabet characters only for customer name." + RESET);
            System.out.print("\t\tEnter person name: ");
            personName = scanner.nextLine().trim(); // Read customer name input
        }
        return personName;
    }

    private static String getValidContactNumber() {
        String Yellow = "\u001B[33m";
        String RESET = "\u001B[0m";
        String contactNumber = scanner.nextLine(); // Read contact number input

        // Validate contact number (maximum of 11 numbers)
        while (!contactNumber.matches("\\d{10,11}")) {
            System.out
                    .println(Yellow + "\t\tInvalid input. Please enter a 10 OR 11 numbers for contact number." + RESET);
            System.out.print("\t\tEnter contact number: ");
            contactNumber = scanner.nextLine(); // Read contact number input
        }
        return contactNumber;
    }

    private static String getValidCountry() {
        String Yellow = "\u001B[33m";
        String RESET = "\u001B[0m";
        String country = scanner.nextLine().trim().toUpperCase(); // Read customer name input

        // Validate country (alphabet characters only)
        while (!country.matches("[a-zA-Z ]+")) {
            System.out
                    .println(Yellow + "\t\tInvalid input. Please enter alphabet characters only for country." + RESET);
            System.out.print("\t\tEnter country: ");
            country = scanner.nextLine().trim().toUpperCase(); // Read customer name input
        }
        return country;
    }

    private static String getValidBirthday() {
        String Yellow = "\u001B[33m";
        String RESET = "\u001B[0m";
        String birthday = ""; // Initialize birthday string
        boolean validInput = false; // Flag to indicate valid input

        while (!validInput) {
            System.out.print("\t\tEnter birthday (DD/MM/YYYY): "); // Prompt user for birthday
            birthday = scanner.nextLine(); // Read birthday input

            // Validate birthday format (DD/MM/YYYY) using regular expression
            if (Pattern.matches("^\\d{2}/\\d{2}/\\d{4}$", birthday)) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate birthDate = LocalDate.parse(birthday, formatter);

                    // Get the current date
                    LocalDate currentDate = LocalDate.now();

                    // Validate that the birthDate is not in the future
                    if (birthDate.isAfter(currentDate)) {
                        System.out.println(Yellow + "\t\tInvalid input. Birthday cannot be in the future." + RESET);
                    } else {
                        validInput = true; // Set flag to true for valid input
                    }
                } catch (DateTimeParseException e) {
                    System.out.println(Yellow + "\t\tInvalid input. Please enter a valid date in DD/MM/YYYY format." + RESET);
                }
            } else {
                System.out.println(Yellow + "\t\tInvalid input format. Please enter birthday in DD/MM/YYYY format." + RESET);
            }
        }
        return birthday;
    }
}
