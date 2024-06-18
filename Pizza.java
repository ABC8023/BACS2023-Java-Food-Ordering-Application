import java.util.Scanner;

public class Pizza extends Food {
    private String topping;

    public Pizza(String name, String price, double priceCal) {
        setName(name);
        setPrice(price);
        setPriceCal(priceCal);
        this.topping = getAdditionalInfo();
    }

    private String getAdditionalInfo() {
        Scanner scanner = new Scanner(System.in);
        int top;
        String Yellow = "\u001B[33m";
        String RESET = "\u001B[0m";

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n\n\n\t\t          Choose additional topping for " + getName() + ":");
        System.out.println("\t\t          [1] Pineapple");
        System.out.println("\t\t          [2] Cheese");
        System.out.println("\t\t          [3] Caramelised onion");
        System.out.println("\t\t          [4] Roast cauliflower\n");

        while (true) {
            System.out.print("\n\t\t          Enter your choice: ");
            String extraInfo = "";
            if (scanner.hasNextInt()) {
                top = scanner.nextInt();
                if (top >= 1 && top <= 4) {
                    switch (top) {
                        case 1:
                            extraInfo = " (Extra Pineapple)  ";
                            break;
                        case 2:
                            extraInfo = " (Extra Cheese)     ";
                            break;
                        case 3:
                            extraInfo = " (Caramelised onion)";
                            break;
                        case 4:
                            extraInfo = " (Roast cauliflower)";
                            break;
                    }
                    return extraInfo;

                } else {
                    System.out.println(
                            Yellow + "\n\t\t\t  Invalid topping choice. Please enter a number between 1 and 4." + RESET);
                    scanner.nextLine();
                    continue;
                }
            } else {
                System.out.println(Yellow + "\n\t\t\t  Invalid input. Please enter a number." + RESET);
                scanner.nextLine(); // Clear the invalid input from the scanner
                continue;

            }

        }

    }

    public String getFullNameWithInfo() {
        return getName() + topping;
    }

}