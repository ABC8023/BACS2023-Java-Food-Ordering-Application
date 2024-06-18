import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSliderUI;

public class ManagementMenuModule {
    public static void viewCustomerDetails() {
        // Read customer details from file
        List<String> customerDetailsList = readCustomerDetailsFromFile();

        if (customerDetailsList.isEmpty()) {
            System.out.println("\n\t\tNo customer records found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean validOption = false;

        while (!validOption) {
            System.out.println("\n\t\tSelect an option:");
            System.out.println("\t\t1. Customers Information");
            System.out.println("\t\t2. Customers Orders");
            System.out.println("\t\t3. Customer Statistics");
            System.out.println("\t\t4. Search Records");
            System.out.println("\t\t5. Sort Records by Customer Name");
            System.out.println("\t\t6. Edit Customer Details");
            System.out.println("\t\t7. Return to Main Menu");
            System.out.print("\n\t\tEnter your choice: ");

            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (option) {
                    case 1:
                        displayCustomerDetails(customerDetailsList);
                        break;
                    case 2:
                        displayCustomerOrders();
                        break;
                    case 3:
                        calculateCustomerStatistics(customerDetailsList);
                        break;
                    case 4:
                        searchRecords(customerDetailsList);
                        break;
                    case 5:
                        sortRecords(customerDetailsList);
                        break;
                    case 6:
                        editCustomerDetails(customerDetailsList);
                        break;
                    case 7:
                        Main.main(null);
                        return;
                    default:
                        String Yellow = "\u001B[33m";
                        String RESET = "\u001B[0m";
                        System.out.println(Yellow + "\n\t\tInvalid option selected." + RESET);
                }
            } else {
                String Yellow = "\u001B[33m";
                String RESET = "\u001B[0m";
                System.out.println(Yellow + "\n\t\tInvalid input. Please enter a number." + RESET);
                scanner.next(); // Clear invalid input
            }
        }
    }

    private static void displayCustomerDetails(List<String> customerDetailsList) {
        System.out.println("\n\t\tCustomer Details:");
        System.out.println("\n\t\tMembership Card Number  Customer Name                 Contact Number  Birthday       Country         Membership Points         10% Voucher    20% Voucher");
        
        // Iterate over the sorted list and display each customer's details
        for (String customerDetails : customerDetailsList) {
            String[] parts = customerDetails.split(",");
            
            // Check if the number of elements in the parts array is at least 11
            System.out.printf("\t\t%-24s%-30s%-16s%-15s%-16s%-26s%-15s%-15s\n", 
            parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);


        }
    }

    private static void processCustomerFavouriteOrders(List<String> customerOrderList) {
        // Map to store the count of each2 pizza, beverage, and side
        Map<String, Integer> favoriteMealsCount = new HashMap<>();
        // Count the occurrences of each order
        for (String customerOrder : customerOrderList) {
            // Remove '1.' and 'x 1' from each row
            String foodItem = customerOrder.replaceAll("^\\d+\\.\\s*|\\s*x\\s*\\d+$", "").trim();
            favoriteMealsCount.put(foodItem, favoriteMealsCount.getOrDefault(foodItem, 0) + 1);
        }
        // Calculate total number of orders
        int totalOrders = customerOrderList.size();
        // Display the results
        System.out.println("\n\t\tPercentage of Overall Customer Favorite Order:");
        // Sort the entries by percentage in descending order
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(favoriteMealsCount.entrySet());
        sortedEntries.sort((entry1, entry2) -> {
            double percentage1 = (entry1.getValue() / (double) totalOrders) * 100;
            double percentage2 = (entry2.getValue() / (double) totalOrders) * 100;
            return Double.compare(percentage2, percentage1);
        });
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            String favorder = entry.getKey();
            int count = entry.getValue();
            double percentage = (count / (double) totalOrders) * 100;
            System.out.printf("\n\t\tOrder: %s, \n\t\tPercentage: %.2f%%\n", favorder, percentage);
        }
    }

    private static class Customer {
        private String name;
        private int membershipPoints;

        public Customer(String name, int membershipPoints) {
            this.name = name;
            this.membershipPoints = membershipPoints;
        }

        public String getName() {
            return name;
        }

        public int getMembershipPoints() {
            return membershipPoints;
        }
    }

    private static void calculateOverallMembershipPoints(List<String> customerDetailsList) {
        // Create a list to store Customer objects
        List<Customer> customers = new ArrayList<>();

        // Extract customer's name and membership points from each customer's details and add to the list
        for (String customerDetails : customerDetailsList) {
            String[] parts = customerDetails.split(",");
            String name = parts[1]; // Assuming name is at index 1
            int membershipPoints = Integer.parseInt(parts[5]); // Assuming membership points are at index 5
            customers.add(new Customer(name, membershipPoints));
        }

        // Sort the customers based on membership points in descending order
        Collections.sort(customers, (c1, c2) -> c2.getMembershipPoints() - c1.getMembershipPoints());

        // Display the top 5 highest membership point holders
        System.out.println("\n\t\tTop 5 Highest Membership Point Holders:");
        for (int i = 0; i < Math.min(5, customers.size()); i++) {
            Customer customer = customers.get(i);
            System.out.println("\t\tRank " + (i + 1) + ": " + customer.getName() + " - " + customer.getMembershipPoints() + " points");
        }
    }

    // Existing method to display customer orders
    private static void displayCustomerOrders() {
        List<String> customerOrderList = readCustomerOrdersFromFile(); // Read customer orders from file

        if (customerOrderList.isEmpty()) {
            System.out.println("\n\t\tNo customer orders found.");
            return;
        }

        System.out.println("\n\t\tCustomer Orders:");

        // Iterate over the list and display each customer's order
        for (String customerOrder : customerOrderList) {
            System.out.println(customerOrder);
        }
    }

    // New method to display customer favourite orders
    private static void displayCustomerFavouriteOrders() {
        List<String> customerOrderList = readCustomerFavouriteOrdersFromFile(); // Read customer orders from file

        if (customerOrderList.isEmpty()) {
            System.out.println("\n\t\tNo customer orders found.");
            return;
        }

        processCustomerFavouriteOrders(customerOrderList); // Display overall customer favorite meal
    }

    // Helper method to read customer favourite orders from file
    private static List<String> readCustomerFavouriteOrdersFromFile() {
        List<String> customerOrderList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("TotalCustomerOrder.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                customerOrderList.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading customer favourite orders from file: " + e.getMessage());
        }

        return customerOrderList;
    }

    // Helper method to read customer orders from file
    private static List<String> readCustomerOrdersFromFile() {
        List<String> customerOrderList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("order.txt"))) {
            String line;
            StringBuilder orderStringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Membership Number: ")) {
                    // Start of a new order
                    if (orderStringBuilder.length() > 0) {
                        // Append the completed order to the list
                        customerOrderList.add(orderStringBuilder.toString());
                        // Reset the string builder for the next order
                        orderStringBuilder.setLength(0);
                    }
                    // Append the membership number to the order string
                    orderStringBuilder.append(line).append("\n");
                } else {
                    // Append other lines to the current order string
                    orderStringBuilder.append(line).append("\n");
                }
            }
            // Append the last completed order to the list
            if (orderStringBuilder.length() > 0) {
                customerOrderList.add(orderStringBuilder.toString());
            }
        } catch (IOException e) {
            System.err.println("Error reading customer orders from file: " + e.getMessage());
        }

        return customerOrderList;
    }
    

    private static void calculateCustomerStatistics(List<String> customerDetailsList) {
        Scanner scanner = new Scanner(System.in);
    
        // Present options to the user
        System.out.println("\n\t\tSelect an option:");
        System.out.println("\t\t1. Overall Customer Location");
        System.out.println("\t\t2. Customer Favorite Meal");
        System.out.println("\t\t3. Overall Customer Membership Point");
        System.out.print("\n\t\tEnter your choice: ");
    
        if (scanner.hasNextInt()) {
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
    
            switch (option) {
                case 1:
                    calculateLocationPercentage(customerDetailsList);
                    break;
                case 2:
                    displayCustomerFavouriteOrders();
                    break;
                case 3:
                    calculateOverallMembershipPoints(customerDetailsList);
                    break;
                default:
                    String Yellow = "\u001B[33m";
                    String RESET = "\u001B[0m";
                    System.out.println(Yellow + "\n\t\tInvalid option selected." + RESET);
            }
        } else {
            String Yellow = "\u001B[33m";
            String RESET = "\u001B[0m";
            System.out.println(Yellow + "\n\t\tInvalid input. Please enter a number." + RESET);
            scanner.next(); // Clear invalid input
        }
    }
    
    private static void calculateLocationPercentage(List<String> customerDetailsList) {
        Map<String, Integer> locationCount = new HashMap<>();
    
        for (String customerDetails : customerDetailsList) {
            String[] parts = customerDetails.split(",");
            String location = parts[4]; // Assuming location is at index 4
            locationCount.put(location, locationCount.getOrDefault(location, 0) + 1);
        }
    
        int totalCustomers = customerDetailsList.size();
        System.out.println("\n\t\tCustomer Location Statistics:");
        for (Map.Entry<String, Integer> entry : locationCount.entrySet()) {
            String location = entry.getKey();
            int count = entry.getValue();
            double percentage = (count / (double) totalCustomers) * 100;
            System.out.printf("\n\t\tLocation: %s, \n\t\tPercentage: %.2f%%\n", location, percentage);
        }
    }
    

    private static void searchRecords(List<String> customerDetailsList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\t\tEnter search keyword: ");
        String keyword = scanner.nextLine();
        boolean found = false;
        
        // List to store matched customer details
        List<String> matchedCustomers = new ArrayList<>();

        for (String customerDetails : customerDetailsList) {
            if (customerDetails.contains(keyword)) {
                matchedCustomers.add(customerDetails);
                found = true;
            }
        }
        
        if (found) {
            displayCustomerDetails(matchedCustomers); // Pass the list of matched customer details
        } else {
            String Yellow = "\u001B[33m";
            String RESET = "\u001B[0m";
            System.out.println(Yellow + "\n\t\tNo matching records found." + RESET);
        }
    }
    

    private static void sortRecords(List<String> customerDetailsList) {
        // Sort customerDetailsList based on a specific field
        Collections.sort(customerDetailsList, Comparator.comparing(customerDetails -> {
            String[] parts = customerDetails.split(",");
            // Assuming parts[1] contains the customer name, sort based on it
            return parts[1];
        }));

        // Display sorted records
        displayCustomerDetails(customerDetailsList);
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

    private static void editCustomerDetails(List<String> customerDetailsList) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\t\tEnter the Membership Card Number of the customer to edit:");
        String membershipCardNumber = scanner.nextLine();

        // Find the customer details based on the Membership Card Number
        for (int i = 0; i < customerDetailsList.size(); i++) {
            String customerDetails = customerDetailsList.get(i);
            if (customerDetails.startsWith(membershipCardNumber)) {
                System.out.println("\n\t\tEnter updated details for the customer: ");
                System.out.print("\t\tCustomer Name     : ");
                String customerName = scanner.nextLine();
                System.out.print("\t\tContact Number    : ");
                String contactNumber = scanner.nextLine();
                System.out.print("\t\tBirthday          : ");
                String birthday = scanner.nextLine();
                System.out.print("\t\tCountry           : ");
                String country = scanner.nextLine().toUpperCase();
                System.out.print("\t\tMembership Points: ");
                String membershipPoints = scanner.nextLine();
                System.out.print("\t\tVoucher Code 1    : ");
                String voucherCode = scanner.nextLine();
                System.out.print("\t\tVoucher Code 2    : ");
                String voucherCode2 = scanner.nextLine();

                // Update the customer details
                String updatedCustomerDetails = membershipCardNumber + "," + customerName + "," + contactNumber + "," + birthday + "," + country + "," + membershipPoints + "," + voucherCode + "," + voucherCode2;
                customerDetailsList.set(i, updatedCustomerDetails);
                System.out.println("\n\t\tCustomer details updated successfully.");

                // Write updated customer details back to file
                writeCustomerDetailsToFile(customerDetailsList);
                return;
            }
        }
        String Yellow = "\u001B[33m";
        String RESET = "\u001B[0m";
        System.out.println(Yellow + "\n\t\tCustomer not found." + RESET);
    }

    private static void writeCustomerDetailsToFile(List<String> customerDetailsList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("customerDetails.txt"))) {
            for (String customerDetails : customerDetailsList) {
                writer.write(customerDetails);
                writer.newLine();
            }
            System.out.println("\n\t\tCustomer details written to file successfully.");
        } catch (IOException e) {
            System.out.println("\n\t\tError writing customer details to file.");
            e.printStackTrace();
        }
    }

    public static void displayAccountDetail(String membershipNumber) {
        // Read customer details from file
        
        List<String> customerDetailsList = readCustomerDetailsFromFile();
    
        boolean found = false;
        // Iterate through customer details to find matching membership number
        for (String customerDetails : customerDetailsList) {
            String[] parts = customerDetails.split(",");
            if (parts[0].equals(membershipNumber)) { // Assuming membership number is at index 0
                // Print customer details in two columns
                System.out.println("\n\n\t\tCustomer Details:");
                System.out.println("\n\t\tMembership Card Number: " + parts[0]);
                System.out.println("\t\tCustomer Name: " + parts[1]);
                System.out.println("\t\tContact Number: " + parts[2]);
                System.out.println("\t\tBirthday: " + parts[3]);
                System.out.println("\t\tCountry: " + parts[4]);
                System.out.println("\t\tMembership Points: " + parts[5]);
                System.out.println("\t\t10% Voucher : " + parts[6]);
                System.out.print("\t\t20% Voucher : " + parts[7]);
                found = true;
                break;
            }
        }
    
        // If customer not found, print message
        if (!found) {
            System.out.println("\n\t\tCustomer with membership number " + membershipNumber + " not found.");
        }
    }

    
}
