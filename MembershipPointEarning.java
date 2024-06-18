import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MembershipPointEarning {

    public static int convertToPoints(double totalCost) {
        // Conversion rate: 10 Ringgit to 1 point
        return (int) (totalCost / 10);
    }

    public static double PurchaseWithVoucher(LoginResult loginResult, double totalcost) {
        List<String> customerDetailsList = readCustomerDetailsFromFile();
        String membershipCard = loginResult.getMembershipCardNumber();
        Scanner scanner = new Scanner(System.in);

        boolean voucherCheck = false;
        boolean returnToMainMenu = false;

        // Iterate through customer details to find matching membership number
        for (int i = 0; i < customerDetailsList.size(); i++) {
            String customerDetails = customerDetailsList.get(i);
            String[] parts = customerDetails.split(",");
            if (parts[0].equals(membershipCard)) { // Assuming membership number is at index 0
                // Update membership points

                int currentVoucher1 = parts[6].equals("none") ? 0 : Integer.parseInt(parts[6]); // Assuming voucher 1
                                                                                                // count is at index 9
                int currentVoucher2 = parts[7].equals("none") ? 0 : Integer.parseInt(parts[7]); // Assuming voucher 2
                                                                                                // count is at index 10

                do {
                    String Yellow = "\u001B[33m";
                    String RESET = "\u001B[0m";

                    System.out.println("\t\t Would you like to pay with voucher?");
                    System.out.println("\t\t 1. 10% voucher");
                    System.out.println("\t\t 2. 20% voucher");
                    System.out.println("\t\t 3. No, proceed to payment");
                    System.out.println("\t\t 4. Back to Main Menu");
                    System.out.print("\n\t\t Enter Your choice: ");

                    try {
                        int voucherChoice = scanner.nextInt();
                        // Validate the range of voucher options
                        if (voucherChoice >= 1 && voucherChoice <= 4) {

                            if (voucherChoice == 1 && currentVoucher1 >= 1) {
                                currentVoucher1--;
                                totalcost = totalcost * 0.9;
                                parts[6] = String.valueOf(currentVoucher1); // Update voucher 1 count in the array
                                customerDetailsList.set(i, String.join(",", parts));
                                voucherCheck = true;
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                writeCustomerDetailsToFile(customerDetailsList);
                            } else if (voucherChoice == 2 && currentVoucher2 >= 1) {
                                currentVoucher2--;
                                totalcost = totalcost * 0.8;
                                parts[7] = String.valueOf(currentVoucher2); // Update voucher 2 count in the array
                                customerDetailsList.set(i, String.join(",", parts));
                                voucherCheck = true;
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                writeCustomerDetailsToFile(customerDetailsList);
                            } else if (voucherChoice == 3) {
                                voucherCheck = true;
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                            } else if ((voucherChoice == 1 && currentVoucher1 == 0)
                                    || (voucherChoice == 2 && currentVoucher2 == 0)) {
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println(Yellow+"\t\t Insufficient Voucher.\n"+RESET);
                            }
                            else if (voucherChoice == 4){
                                returnToMainMenu = true;
                                break;
                            }
                        } else {
                            System.out.println(Yellow + "\n\t\t Invalid choice. Please enter number 1 to 4.\n" + RESET);
                            voucherCheck = false;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(Yellow + "\n\t\t Invalid input. Please enter a number.\n" + RESET);
                        scanner.next(); // Consume the invalid input
                    }
                } while (!voucherCheck);

            }
        }
        if (returnToMainMenu) {
            System.out.println("\n\t\t Returning to Main Menu.");
            return totalcost = 0; 
        } else {
            return totalcost; // Returning totalcost with applied voucher (if any)
        }
        // return totalcost;

    }

    public static int updateMemberPoint(LoginResult loginResult, double totalCost) {
        List<String> customerDetailsList = readCustomerDetailsFromFile();
        String membershipCard = loginResult.getMembershipCardNumber();
        int pointsEarned = convertToPoints(totalCost);

        boolean found = false;
        int newEarned = 0; // Declare newEarned outside of the loop

        // Iterate through customer details to find matching membership number
        for (int i = 0; i < customerDetailsList.size(); i++) {
            String customerDetails = customerDetailsList.get(i);
            String[] parts = customerDetails.split(",");
            if (parts[0].equals(membershipCard)) { // Assuming membership number is at index 0
                // Update membership points
                int currentPoints = Integer.parseInt(parts[5]); // Assuming membership points is at index 5
                int newPoints;

                if (currentPoints > 3000) {
                    newEarned = (int) (pointsEarned * 2);
                } else if (currentPoints > 2000) {
                    newEarned = (int) (pointsEarned * 1.8);
                } else if (currentPoints > 1000) {
                    newEarned = (int) (pointsEarned * 1.5);
                } else {
                    newEarned = pointsEarned;
                }
                newPoints = currentPoints + newEarned;

                parts[5] = String.valueOf(newPoints); // Update membership points in the array

                // Update the corresponding line in the list
                customerDetailsList.set(i, String.join(",", parts));
                found = true;
                break;
            }
        }

        // Write updated customer details back to file
        if (found) {
            writeCustomerDetailsToFile(customerDetailsList);
        } else {
            System.out.println("Customer not found.");
        }

        return newEarned;
    }

    // Assuming you want to return the membershipCard
    private static void writeCustomerDetailsToFile(List<String> customerDetailsList) {
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("customerDetails.txt"))) {
            for (String customerDetails : customerDetailsList) {
                writer.write(customerDetails);
                writer.newLine();
            }
            System.out.println(GREEN + "\t\tMembership Point updated successfully." + RESET);
        } catch (IOException e) {
            System.out.println(RED + "Error writing customer details file." + RESET);
            e.printStackTrace();
        }
    }

    private static List<String> readCustomerDetailsFromFile() {
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        List<String> customerDetailsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("customerDetails.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                customerDetailsList.add(line);
            }
        } catch (IOException e) {
            System.out.println(RED + "\n\t\tError reading customer details file." + RESET);
            e.printStackTrace();
        }

        return customerDetailsList;
    }

}