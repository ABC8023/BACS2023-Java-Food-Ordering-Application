import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class MembershipPointRedemption {


    public static boolean MemberPointPurchase(LoginResult loginResult, int vouchercode) {
        List<String> customerDetailsList = readCustomerDetailsFromFile();
        String membershipCard = loginResult.getMembershipCardNumber();
        int pointDeduct = 0;
        String GREEN = "\u001B[32m";
        String Yellow = "\u001B[33m";
        String RESET = "\u001B[0m";
    
        // Determine the points to deduct based on the voucher code
        switch (vouchercode) {
            case 9:
                pointDeduct = 50;
            break;
            case 10:
                pointDeduct = 70;
            break;
            case 11:
                pointDeduct = 100;
                break;
            case 12:
                pointDeduct = 200;
                break;
            default:
                System.out.println(Yellow + "\t\tInvalid voucher code." + RESET);
                return false; // Payment failed due to invalid voucher code
        }
    
        boolean found = false;
        // Iterate through customer details to find matching membership number
        for (int i = 0; i < customerDetailsList.size(); i++) {
            String customerDetails = customerDetailsList.get(i);
            String[] parts = customerDetails.split(",");
            if (parts[0].equals(membershipCard)) { // Assuming membership number is at index 0
                // Update membership points

                int currentPoints = Integer.parseInt(parts[5]); // Assuming membership points is at index 8
                int currentVoucher1 = parts[6].equals("none") ? 0 : Integer.parseInt(parts[6]); // Assuming voucher 1 count is at index 9
                int currentVoucher2 = parts[7].equals("none") ? 0 : Integer.parseInt(parts[7]); // Assuming voucher 2 count is at index 10
    
                if (currentPoints >= pointDeduct) {
                    if (vouchercode == 11) {
                        currentVoucher1++; // Increase voucher 1 count
                    } else if (vouchercode == 12) {
                        currentVoucher2++; // Increase voucher 2 count
                    }
    
                    int newPoints = currentPoints - pointDeduct;
                    parts[5] = String.valueOf(newPoints); // Update membership points in the array
                    parts[6] = String.valueOf(currentVoucher1); // Update voucher 1 count in the array
                    parts[7] = String.valueOf(currentVoucher2); // Update voucher 2 count in the array
    
                    // Update the corresponding line in the list
                    customerDetailsList.set(i, String.join(",", parts));
                    found = true;
                    writeCustomerDetailsToFile(customerDetailsList);
                    if (vouchercode == 9 || vouchercode == 10) {
                        System.out.println(GREEN + "\t\tMerchandise purchased successfully." + RESET);
                        System.out.println(GREEN + "\t\tPlease head to redemption counter to collect merchandise." + RESET);
                    } else if (vouchercode == 11 || vouchercode == 12) {
                        System.out.println(GREEN + "\t\tVoucher purchase successful." + RESET);
                        System.out.println(GREEN + "\t\tVoucher updated successfully." + RESET);
                    }
                    return true; // Payment successful
                } else {
                    System.out.println(Yellow + "\t\tInsufficient points." + RESET);
                    System.out.println(Yellow + "\t\tRedemption unsuccessful." + RESET);
                    return false; // Payment failed due to insufficient points
                }
            }
        }
    
        System.out.println(Yellow + "Customer not found." + RESET);
        return false; // Payment failed due to customer not found
    }

    private static List<String> readCustomerDetailsFromFile() {
        List<String> customerDetailsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("customerDetails.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                customerDetailsList.add(line);
            }
        } catch (IOException e) {
            System.out.println("\n\t\tError reading customer details file.");
            e.printStackTrace();
        }

        return customerDetailsList;
    }

    private static void writeCustomerDetailsToFile(List<String> customerDetailsList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("customerDetails.txt"))) {
            for (String customerDetails : customerDetailsList) {
                writer.write(customerDetails);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing customer details file.");
            e.printStackTrace();
        }
    }


}
