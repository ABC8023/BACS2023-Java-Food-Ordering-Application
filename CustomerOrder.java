import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatter;


public class CustomerOrder {

    public static void writeOrder(LoginResult loginResult, String[] foodAndQuantity) {
        String GREEN = "\u001B[32m";
        String RESET = "\u001B[0m";

        String membershipCard = loginResult.getMembershipCardNumber();
        LocalDate currentDate = LocalDate.now();    
        LocalTime currentTime = LocalTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = currentDate.format(formatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(timeFormatter);


        try {
            String filePath = "order.txt";
            boolean membershipExists = checkMembershipExistence(filePath, membershipCard);

            // If the membership number doesn't exist, append it to the end of the file
            if (!membershipExists) {
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
                    bufferedWriter.newLine(); // Move to the next line
                    bufferedWriter.newLine(); // Move to the next line
                    bufferedWriter.write("Membership Number: " + membershipCard);
                    bufferedWriter.newLine(); // Move to the next line
                }
            }

            // Create a temporary file to hold the updated content
            String tempFilePath = "temp_order.txt";
            String totalOrderFilePath = "TotalCustomerOrder.txt";

            try (BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFilePath));
                BufferedWriter totalOrderWriter = new BufferedWriter(new FileWriter(totalOrderFilePath, true))) {
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        tempWriter.write(line);
                        tempWriter.newLine(); // Move to the next line

                        // If membership number found, write the new order
                        if (line.startsWith("Membership Number: " + membershipCard)) {
                            tempWriter.write("Food Ordered on " + formattedDate + " " + formattedTime);
                            tempWriter.newLine();
                            for (String item : foodAndQuantity) {
                                tempWriter.write(item);
                                tempWriter.newLine(); // Move to the next line

                                // Write the item to TotalCustomerOrder.txt
                                totalOrderWriter.write(item);
                            totalOrderWriter.newLine(); // Move to the next line
                            }
                            tempWriter.newLine(); // Add an extra newline after the order
                        }
                    }
                }
            }

            replaceFile(filePath, tempFilePath);

            System.out.println(GREEN + "\t\tYour Order has been purchased successfully.\n\n" + RESET);
        } catch (IOException e) {
            System.err.println("Error writing order to file: " + e.getMessage());
        }
    }

    private static boolean checkMembershipExistence(String filePath, String membershipNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Membership Number: " + membershipNumber)) {
                    // Membership number already exists in the file
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        // Membership number not found in the file
        return false;
    }

    private static void replaceFile(String originalFilePath, String tempFilePath) throws IOException {
        // Delete the original file
        if (!new java.io.File(originalFilePath).delete()) {
            throw new IOException("Could not delete original file: " + originalFilePath);
        }
        // Rename the temporary file to the original file name
        if (!new java.io.File(tempFilePath).renameTo(new java.io.File(originalFilePath))) {
            throw new IOException("Could not rename temporary file: " + tempFilePath);
        }
    }
}
