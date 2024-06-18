import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.*;


public class Policy {

    public static int LuckyPoint(String winningPrize, int pointsEarned) {
        // Extract the numerical value from winningPrize
        double pointMultiplier = Double.parseDouble(winningPrize);
    
        // Calculate the points earned after applying the multiplier
        int finalPointsEarned = (int)(pointsEarned * pointMultiplier);
  
        // Calculate the difference in points earned
        // int pointsDifference = finalPointsEarned - pointsEarned;
    
        return finalPointsEarned;
    }
    

    public static void updateMemberPoint(LoginResult loginResult, int pointsEarned) {
        List<String> customerDetailsList = readCustomerDetailsFromFile();
        String membershipCard = loginResult.getMembershipCardNumber();

        boolean found = false;
        // Iterate through customer details to find matching membership number
        for (int i = 0; i < customerDetailsList.size(); i++) {
            String customerDetails = customerDetailsList.get(i);
            String[] parts = customerDetails.split(",");
            if (parts[0].equals(membershipCard)) { // Assuming membership number is at index 0
                // Update membership points
                int currentPoints = Integer.parseInt(parts[5]); // Assuming membership points is at index 8
                int newPoints = currentPoints + pointsEarned;
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
    }

public static void updateMemberPointOnBirthday(LoginResult loginResult) {
    String PINK = "\u001B[35m";
    String RESET = "\u001B[0m";

    List<String> customerDetailsList = readCustomerDetailsFromFile();
    String membershipCard = loginResult.getMembershipCardNumber();

    // Get current date
    LocalDate currentDate = LocalDate.now();

    // Iterate through customer details to find matching membership number
    for (int i = 0; i < customerDetailsList.size(); i++) {
        String customerDetails = customerDetailsList.get(i);
        String[] parts = customerDetails.split(",");
        if (parts[0].equals(membershipCard)) { // Assuming membership number is at index 0
            // Get birthday from customer details
            String birthdayStr = parts[3];
            LocalDate birthday = null;

            // Check if birthday string is in the correct format
            try {
                birthday = LocalDate.parse(birthdayStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                return; // Exit the method if birthday is not in correct format
            }

            // Check if it's the customer's birthday
            if (currentDate.getMonth() == birthday.getMonth() && currentDate.getDayOfMonth() == birthday.getDayOfMonth()) {
                // Update membership points
                int currentPoints = Integer.parseInt(parts[5]); // Assuming membership points is at index 8
                int newPoints = currentPoints + 5;
                parts[5] = String.valueOf(newPoints); // Update membership points in the array

                // Update the corresponding line in the list
                customerDetailsList.set(i, String.join(",", parts));
                writeCustomerDetailsToFile(customerDetailsList);
                System.out.print(PINK + "\n\t\tBirthday points added successfully.");
                System.out.println("\n\t\tIt's your birthday! Enjoy extra 5 points for your every purchase today!\n" + RESET);
                playBirthdaySong();
                return; // Exit the method after updating points on birthday
            } else {
                return;
            }
        }
    }

    // If no match is found, print message
    System.out.println("Customer not found.");
}


    
        // Assuming you want to return the membershipCard
        private static void writeCustomerDetailsToFile(List<String> customerDetailsList) {
            String GREEN = "\u001B[32m"; 
            String RESET = "\u001B[0m";


            try (BufferedWriter writer = new BufferedWriter(new FileWriter("customerDetails.txt"))) {
                for (String customerDetails : customerDetailsList) {
                    writer.write(customerDetails);
                    writer.newLine();
                }
                System.out.println(GREEN + "\t\tMembership Point updated successfully." + RESET);
            } catch (IOException e) {
                System.out.println("Error writing customer details file.");
                e.printStackTrace();
            }
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








        
        private static void playBirthdaySong() {
            String songPath = "birthdaysong.wav"; // Assuming the birthday song is in WAV format
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(songPath).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                System.out.println("Error playing birthday song: " + e.getMessage());
            }
        }




}


