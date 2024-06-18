import java.util.Scanner;

public class Side extends Food {
    private String sauce;

    public Side(String name, String price, double priceCal) {
        setName(name);
        setPrice(price);
        setPriceCal(priceCal);
        this.sauce = getAdditionalInfo();

    }

    private String getAdditionalInfo() {
        int top = 0;
        String Yellow = "\u001B[33m";
        String RESET = "\u001B[0m";
        Scanner scanner = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n\n\n\t\t          Choose sauce for " + getName() + ":");
        System.out.println("\t\t          [1] Cheesy       ");
        System.out.println("\t\t          [2] Thousand Island            ");
        System.out.println("\t\t          [3] BBQ");
        System.out.println("\t\t          [4] Thai Chilli Sauce\n");

        while (true) {
            System.out.print("\n\t\t          Enter your choice: ");
            String extraInfo = "";
            if (scanner.hasNextInt()) {
                top = scanner.nextInt();
                if (top >= 1 && top <= 4) {
                    switch (top) {
                        case 1:
                            extraInfo = " (With Cheesy Sauce)   ";
                            break;
                        case 2:
                            extraInfo = " (With Thousand Island)";
                            break;
                        case 3:
                            extraInfo = " (With BBQ Sauce)      ";
                            break;
                        case 4:
                            extraInfo = " (With Thai Chilli)    ";
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
                System.out.println(Yellow + "\n\t\t\t  Invalid input.Please enter a number." + RESET);
                scanner.nextLine();
                continue;
            }
        }

    }

    public String getFullNameWithInfo() {
        return getName() + sauce;
    }

}