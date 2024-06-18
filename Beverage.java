import java.util.Scanner;

public class Beverage extends Food {
    private String flavour;

    public Beverage(String name, String price, double priceCal) {
        setName(name); // Using the setter method to set the name
        setPrice(price);
        setPriceCal(priceCal);
        this.flavour = getAdditionalInfo();
    }

    private String getAdditionalInfo() {
        int top = 0;
        String Yellow = "\u001B[33m";
        String RESET = "\u001B[0m";

        Scanner scanner = new Scanner(System.in);

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n\n\n\t\t          Choose special flavouring for " + getName() + ":");
        System.out.println("\t\t          [1] Strawberry       ");
        System.out.println("\t\t          [2] Lemon            ");
        System.out.println("\t\t          [3] Lime");
        System.out.println("\t\t          [4] Grape\n");

        while (true) {
            System.out.print("\n\t\t          Enter your choice: ");
            String extraInfo = "";
            if (scanner.hasNextInt()) {
                top = scanner.nextInt();
                if (top >= 1 && top <= 4) {
                    switch (top) {
                        case 1:
                            extraInfo = " (Strawberry Flavour)";
                            break;
                        case 2:
                            extraInfo = " (Lemon Flavour)     ";
                            break;
                        case 3:
                            extraInfo = " (Lime Flavour)      ";
                            break;
                        case 4:
                            extraInfo = " (Grape Flavour)     ";
                            break;
                    }
                    return extraInfo;
                } else {
                    System.out.println(
                            Yellow + "\n\t\t\t  Invalid topping choice.Please enter a number between 1 and 4." + RESET);
                    scanner.nextLine();
                    continue;
                }
            } else {
                System.out.println(Yellow + "\n\t\t\t  Invalid input.Please enter a number." + RESET);
                scanner.nextLine();
                continue;
            }

        }
    }

    public String getFullNameWithInfo() {
        return getName() + flavour;
    }

}