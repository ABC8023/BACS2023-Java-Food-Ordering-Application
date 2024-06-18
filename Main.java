
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
import java.util.Scanner;
import java.io.IOException;
import java.time.Year;
import java.util.InputMismatchException;
import java.util.Random;



public class Main {

        private static final Scanner scanner = new Scanner(System.in);

        public static void clearScreen() {
                System.out.print("\033[H\033[2J");
                System.out.flush();

            }

        public static void main(String[] args) {
                int option = 0;

                String Yellow = "\u001B[33m";
                String RESET = "\u001B[0m";

                do {
                        clearScreen();
                        displayMainMenu();

                        if (scanner.hasNextInt()) {

                                option = scanner.nextInt();
                                scanner.nextLine();
                                if (option >= 1 && option <= 4) {
                                        switch (option) {
                                                case 1:
                                                        registerNewCustomer();
                                                        clearScreen();
                                                        break;

                                                case 2:
                                                        customerLogins();
                                                        break;
                                                case 3:
                                                        staffLogin();
                                                        break;
                                                case 4:
                                                        clearScreen();
                                                        System.exit(1);
                                                        System.out.println("\n\t\tExiting program...");
                                                        break;
                                        }
                                } else {
                                        System.out.println("");
                                        System.out.println(
                                                        Yellow + "\t\tInvalid choice. Please enter valid number. Enter one more time"
                                                                        + RESET);

                                        scanner.nextLine(); // Wait for user to press Enter
                                }
                        } else {
                                scanner.nextLine();
                                System.out.println("");
                                System.out.println(
                                                Yellow + "\t\tInvalid input. Please enter a number. Enter one more time."
                                                                + RESET);

                                scanner.nextLine(); // Wait for user to press Enter
                        }

                } while (option != 4);
        }

        public static void customerLogins() {
                String Yellow = "\u001B[33m";
                String RESET = "\u001B[0m";

                int option = 0;
                int menuCheck = 0;
                int menu2Check = 0;
                int paymentcheck = 0;
                int paymentSuc = 0;

                double totalCost = 0.0;
                LoginModule loginModule = new LoginModule();
                LoginResult loginResult = loginModule.customerLogin();
                String membershipCardNumber = loginResult.getMembershipCardNumber();

                if (loginResult.getMenuCheck() == 0) {
                        return;
                    }

                else if (loginResult.getMenuCheck() == 1) {
                        do {
                                paymentSuc = 0;
                                paymentcheck = 0;
                                MainCustomerMenu();

                                if (scanner.hasNextInt()) {

                                        option = scanner.nextInt();
                                        scanner.nextLine();
                                        if (option >= 1 && option <= 5) {

                                                switch (option) {
                                                        case 1:
                                                                clearScreen();
                                                                ManagementMenuModule.displayAccountDetail(
                                                                                membershipCardNumber);
                                                                menu2Check = 0;
                                                                break;
                                                        case 2: {

                                                                clearScreen();

                                                                Food[] orders = new Food[100];
                                                                int i = 0;

                                                                char DT;

                                                                char again = 'y';
                                                                int menu3Check =1;
                                                                do {
                                                                        menu3Check =1;
                                                                        clearScreen();
                                                                        DineinOrTakeAway();
                                                                        DT = scanner.next().charAt(0);
                                                                        DT = Character.toUpperCase(DT);

                                                                        if (DT == '0') {
                                                                                DT = 'E';
                                                                            }
                                                        
                                                                        if (DT != 'A' && DT != 'B' && DT != 'E') {
                                                                            System.out.println(Yellow + "\n\t\t\tInvalid choice. Please enter 'A' for Dine-in or 'B' for Take-away." + RESET);
                                                                            System.out.println(Yellow + "\t\t\tPress ENTER one more time." + RESET);
                                                                            scanner.nextLine();
                                                                        }
                                                                } while (DT != 'A' && DT != 'B' && DT != 'E');
                                                                scanner.nextLine();

                                                                if (DT == 'E') {
                                                                        clearScreen();
                                                                        menu2Check = 0;
                                                                        break;
                                                                    }
                                                                



                                                                do {

                                                                        clearScreen();
                                                                        menu3Check = 1;
                                                                        again = 'y';
                                                                        int menuChoice = 0;
                                                                        menuChoice = OrderMenu(menuChoice);

                                                                        // if (menuChoice >= 1 && menuChoice <= 3) {
                                                                        switch (menuChoice) {

                                                                                case 1: {
                                                                                        clearScreen();
                                                                                        System.out.println(
                                                                                                        "\n\n\n\t\t\t\t ------------------------------------------------------- ");
                                                                                        System.out.println(
                                                                                                        "\t\t\t\t$$$                       Pizza                       $$$ ");
                                                                                        System.out.println(
                                                                                                        "\t\t\t\t ------------------------------------------------------- ");

                                                                                        System.out.println(
                                                                                                        "\n\t                 *--[  1  ]-------------------* \t   *--[  2  ]-------------------* ");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            | ");
                                                                                        System.out.println(
                                                                                                        "\t                 |    Zucchini & Gorgonzola   |\t           |     Truffle & Mushroom     | ");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            | ");
                                                                                        System.out.println(
                                                                                                        "\t                 |    Price : RM 22.00 /SLC   |\t           |    Price : RM 23.00 /SLC   | ");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            | ");
                                                                                        System.out.println(
                                                                                                        "\t                 +----------------------------+\t           +----------------------------+ ");
                                                                                        System.out.println(
                                                                                                        "\n\n\t                 +--[  3  ]-------------------+ \t   *--[  4  ]-------------------* ");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            | ");
                                                                                        System.out.println(
                                                                                                        "\t                 |      Pepperoni Gourmet     |\t           |         Vegetarian         | ");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            | ");
                                                                                        System.out.println(
                                                                                                        "\t                 |    Price : RM 22.00 /SLC   |\t           |    Price : RM 19.50 /SLC   | ");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            | ");
                                                                                        System.out.println(
                                                                                                        "\t                 *----------------------------*\t           *----------------------------* ");
                                                                                        System.out.println(Yellow  + "\t\t          Back to food menu. (Press 0)" + RESET);


                                                                                        while (true) {

                                                                                                try {
                                                                                                        System.out.print(
                                                                                                                        "\n\t\t          Enter pizza number to order: ");
                                                                                                        int pizzaChoice = scanner
                                                                                                                        .nextInt();
                                                                                                        scanner.nextLine();

                                                                                                        if (pizzaChoice == 0){
                                                                                                           menu3Check = 0;
                                                                                                           break;
                                                                                                        }

                                                                                                        if (pizzaChoice >= 1
                                                                                                                        && pizzaChoice <= 4) {
                                                                                                                Pizza pizza = ItemList
                                                                                                                                .createPizza(pizzaChoice);
                                                                                                                                int quantity =0;

                                                                                                                if (pizza != null) {

                                                                                                                        do {
                                                                                                                                
                                                                                                                                System.out.print(Yellow+
                                                                                                                                        "\n\t\t          Enter quantity: 0 to cancel order. "+RESET);                                                                                                                        System.out.print(
                                                                                                                                                "\n\n\t\t          Enter quantity: ");

                                                                                                                                if (scanner.hasNextInt()) {
                                                                                                                                        quantity = scanner
                                                                                                                                                        .nextInt();
                                                                                                                                        if (quantity == 0){
                                                                                                                                                menu3Check = 0;
                                                                                                                                                break;
                                                                                                                                        }
                                                                                                                                        if (quantity > 0) {
                                                                                                                                                // Quantity
                                                                                                                                                // is
                                                                                                                                                // valid
                                                                                                                                                pizza.setQuantity(
                                                                                                                                                                quantity);
                                                                                                                                                pizza.setTotalOrder(
                                                                                                                                                                pizza.getPriceCal()
                                                                                                                                                                                * pizza.getQuantity());
                                                                                                                                                break;
                                                                                                                                        } else {
                                                                                                                                                System.out.println(
                                                                                                                                                                Yellow + "\n\t\t          Invalid quantity. Please enter a positive number."
                                                                                                                                                                                + RESET);
                                                                                                                                        }
                                                                                                                                } else {
                                                                                                                                        System.out.println(
                                                                                                                                                        Yellow + "\n\t\t          Invalid input. Please enter a number."
                                                                                                                                                                        + RESET);
                                                                                                                                        scanner.next(); // Consume
                                                                                                                                                        // the
                                                                                                                                                        // invalid
                                                                                                                                                        // input
                                                                                                                                }
                                                                                                                        } while (true);
                                                                                                                }

                                                                                                                if (quantity == 0) {
                                                                                                                        // Do not add to orders array if quantity is 0
                                                                                                                        break;}

                                                                                                                orders[i] = pizza;
                                                                                                                i++;
                                                                                                                break;
                                                                                                        } else {
                                                                                                                System.out.println(
                                                                                                                                Yellow + "\n\t\t\t  Invalid choice! Please choose a pizza between 1 and 4."
                                                                                                                                                + RESET);

                                                                                                        }
                                                                                                } catch (InputMismatchException e) {
                                                                                                        System.out.println(
                                                                                                                        Yellow + "\n\t\t\t  Invalid input! Please enter a valid number."
                                                                                                                                        + RESET);

                                                                                                        scanner.nextLine();
                                                                                                }
                                                                                        }
                                                                                        break;
                                                                                }

                                                                                case 2: {
                                                                                        clearScreen();
                                                                                        System.out.println(
                                                                                                        "\n\n\n\t\t\t\t -------------------------------------------------------");
                                                                                        System.out.println(
                                                                                                        "\t\t\t\t$$$                       Sides                      $$$");
                                                                                        System.out.println(
                                                                                                        "\t\t\t\t -------------------------------------------------------");

                                                                                        System.out.println(
                                                                                                        "\n\t                 *--[  1  ]-------------------* \t   *--[  2  ]-------------------*");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            |");
                                                                                        System.out.println(
                                                                                                        "\t                 |       Garlic Bread         |\t           |       French Fries         |");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            |");
                                                                                        System.out.println(
                                                                                                        "\t                 |    Price : RM  8.00 /PLT   |\t           |    Price : RM 10.00 /PLT   |");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            |");
                                                                                        System.out.println(
                                                                                                        "\t                 +----------------------------+\t           +----------------------------+");
                                                                                        System.out.println(
                                                                                                        "\n\n\t                 +--[  3  ]-------------------+ \t   *--[  4  ]-------------------*");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            |");
                                                                                        System.out.println(
                                                                                                        "\t                 |       Onion Rings          |\t           |       Mashed Potato        |");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            |");
                                                                                        System.out.println(
                                                                                                        "\t                 |    Price : RM  9.00 /PLT   |\t           |    Price : RM  8.00 /PLT   |");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            |");
                                                                                        System.out.println(
                                                                                                        "\t                 *----------------------------*\t           *----------------------------*");
                                                                                        System.out.println(Yellow  + "\t\t          Back to food menu. (Press 0)" + RESET);


                                                                                        while (true) {

                                                                                                try {
                                                                                                        System.out.print(
                                                                                                                        "\n\t\t          Enter side number to order: ");

                                                                                                        int sideChoice = scanner
                                                                                                                        .nextInt();

                                                                                                        if (sideChoice == 0){
                                                                                                                menu3Check = 0;
                                                                                                                break;
                                                                                                        }

                                                                                                        // Validate
                                                                                                        // side
                                                                                                        // choice
                                                                                                        if (sideChoice >= 1
                                                                                                                        && sideChoice <= 4) {
                                                                                                                Side side = ItemList
                                                                                                                                .createSide(sideChoice);
                                                                                                                int quantity = 0;
                                                                                                                if (side != null) {

                                                                                                                        do {
                                                                                                                                
                                                                                                                                System.out.print(Yellow+
                                                                                                                                        "\n\t\t          Enter quantity: 0 to cancel order. "+RESET);                                                                                                                              System.out.print(
                                                                                                                                                "\n\n\t\t          Enter quantity: ");
                                                                                                                                if (scanner.hasNextInt()) {
                                                                                                                                        quantity = scanner
                                                                                                                                                        .nextInt();
                                                                                                                                        if (quantity == 0){
                                                                                                                                                menu3Check = 0;
                                                                                                                                                break;
                                                                                                                                        }
                                                                                                                                        if (quantity > 0) {
                                                                                                                                                // Quantity
                                                                                                                                                // is
                                                                                                                                                // valid
                                                                                                                                                side.setQuantity(
                                                                                                                                                                quantity);
                                                                                                                                                side.setTotalOrder(
                                                                                                                                                                side.getPriceCal()
                                                                                                                                                                                * side.getQuantity());
                                                                                                                                                break;
                                                                                                                                        } else {
                                                                                                                                                System.out.println(
                                                                                                                                                                Yellow + "\n\t\t          Invalid quantity. Please enter a positive number."
                                                                                                                                                                                + RESET);
                                                                                                                                        }
                                                                                                                                } else {
                                                                                                                                        System.out.println(
                                                                                                                                                        Yellow + "\n\t\t          Invalid input. Please enter a number."
                                                                                                                                                                        + RESET);
                                                                                                                                        scanner.next(); // Consume
                                                                                                                                                        // the
                                                                                                                                                        // invalid
                                                                                                                                                        // input
                                                                                                                                }
                                                                                                                        } while (true);
                                                                                                                }

                                                                                                                if (quantity == 0) {
                                                                                                                        // Do not add to orders array if quantity is 0
                                                                                                                        break;}

                                                                                                                orders[i] = side;
                                                                                                                i++;
                                                                                                                break;

                                                                                                        } else {
                                                                                                                System.out.println(
                                                                                                                                Yellow + "\n\t\t\t  Invalid choice! Please choose a side between 1 and 4."
                                                                                                                                                + RESET);

                                                                                                                continue;
                                                                                                        }
                                                                                                } catch (InputMismatchException e) {
                                                                                                        System.out.println(
                                                                                                                        Yellow + "\n\t\t\t  Invalid input! Please enter a valid number."
                                                                                                                                        + RESET);

                                                                                                        scanner.nextLine(); // Consume
                                                                                                        // invalid
                                                                                                        // input
                                                                                                }
                                                                                        }
                                                                                        break;

                                                                                }

                                                                                case 3: {
                                                                                        clearScreen();
                                                                                        System.out.println(
                                                                                                        "\n\n\n\t\t\t\t -------------------------------------------------------");
                                                                                        System.out.println(
                                                                                                        "\t\t\t\t$$$                  Our Beverages                    $$$");
                                                                                        System.out.println(
                                                                                                        "\t\t\t\t -------------------------------------------------------");

                                                                                        System.out.println(
                                                                                                        "\n\t                 *--[  1  ]-------------------* \t   *--[  2  ]-------------------*");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            |");
                                                                                        System.out.println(
                                                                                                        "\t                 |         Coke               |\t           |        Orange Juice        |");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            |");
                                                                                        System.out.println(
                                                                                                        "\t                 |    Price : RM  5.00 /GLS   |\t           |    Price : RM  6.00 /GLS   |");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            |");
                                                                                        System.out.println(
                                                                                                        "\t                 +----------------------------+\t           +----------------------------+");
                                                                                        System.out.println(
                                                                                                        "\n\n\t                 +--[  3  ]---- ---------------+ \t   *--[  4  ]-------------------*");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            |");
                                                                                        System.out.println(
                                                                                                        "\t                 |      Iced Lemon Tea        |\t           |       Hot Chocolate        |");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            |");
                                                                                        System.out.println(
                                                                                                        "\t                 |    Price : RM  7.00 /GLS   |\t           |    Price : RM  8.00 /GLS   |");
                                                                                        System.out.println(
                                                                                                        "\t                 |                            |\t           |                            |");
                                                                                        System.out.println(
                                                                                                        "\t                 *----------------------------*\t           *----------------------------*");
                                                                                        System.out.println(Yellow  + "\t\t          Back to food menu. (Press 0)" + RESET);

                                                                                        while (true) {

                                                                                                try {
                                                                                                        System.out.print(
                                                                                                                        "\n\t\t          Enter beverage number to order: ");

                                                                                                        int beverageChoice = scanner
                                                                                                                        .nextInt();

                                                                                                        if (beverageChoice == 0){
                                                                                                                menu3Check = 0;
                                                                                                                break;
                                                                                                        }

                                                                                                        if (beverageChoice >= 1
                                                                                                                        && beverageChoice <= 4) {
                                                                                                                Beverage beverage = ItemList
                                                                                                                                .createBeverage(beverageChoice);
                                                                                                                                int quantity = 0;
                                                                                                                if (beverage != null) {

                                                                                                                        do {
                                                                                                                                
                                                                                                                                System.out.print(Yellow+
                                                                                                                                        "\n\t\t          Enter quantity: 0 to cancel order. "+RESET);                                                                                                                        System.out.print(
                                                                                                                                                "\n\n\t\t          Enter quantity: ");
                                                                                                                                if (scanner.hasNextInt()) {
                                                                                                                                        quantity = scanner
                                                                                                                                                        .nextInt();
                                                                                                                                        if (quantity == 0){
                                                                                                                                                menu3Check = 0;
                                                                                                                                                break;
                                                                                                                                        }
                                                                                                                                        if (quantity > 0) {
                                                                                                                                                // Quantity
                                                                                                                                                // is
                                                                                                                                                // valid
                                                                                                                                                beverage.setQuantity(
                                                                                                                                                                quantity);
                                                                                                                                                beverage.setTotalOrder(
                                                                                                                                                                beverage.getPriceCal()
                                                                                                                                                                                * beverage.getQuantity());
                                                                                                                                                break;
                                                                                                                                        } else {
                                                                                                                                                System.out.println(
                                                                                                                                                                Yellow + "\n\t\t          Invalid quantity. Please enter a positive number."
                                                                                                                                                                                + RESET);
                                                                                                                                        }
                                                                                                                                } else {
                                                                                                                                        System.out.println(
                                                                                                                                                        Yellow + "\n\t\t          Invalid input. Please enter a number."
                                                                                                                                                                        + RESET);
                                                                                                                                        scanner.next(); // Consume
                                                                                                                                                        // the
                                                                                                                                                        // invalid
                                                                                                                                                        // input
                                                                                                                                }
                                                                                                                        } while (true);
                                                                                                                }

                                                                                                                if (quantity == 0) {
                                                                                                                        // Do not add to orders array if quantity is 0
                                                                                                                        break;}

                                                                                                                orders[i] = beverage;
                                                                                                                i++;

                                                                                                                break;
                                                                                                        } else {
                                                                                                                System.out.println(
                                                                                                                                Yellow + "\n\t\t\t  Invalid choice! Please choose a beverage between 1 and 4."
                                                                                                                                                + RESET);

                                                                                                                continue;
                                                                                                        }

                                                                                                } catch (InputMismatchException e) {
                                                                                                        System.out.println(
                                                                                                                        Yellow + "\n\t\t\t  Invalid input! Please enter a valid number."
                                                                                                                                        + RESET);

                                                                                                        scanner.nextLine();// Consume
                                                                                                        //
                                                                                                }
                                                                                        }
                                                                                        break;

                                                                                }

                                                                                case 0: {
                                                                                menu3Check = 0;
                                                                                again = 'b';
                                                                                break;     
                                                                                }

                                                                        }
                                                                        
                                                                        if (menu3Check == 1){
                                                                        clearScreen();
                                                                        System.out.print(
                                                                                        "\n\n\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\t\tOrder again? ( Y=yes/ N=no ): ");
                                                                        again = scanner.next().charAt(0);

                                                                        while (again != 'Y' && again != 'y'
                                                                                        && again != 'N'
                                                                                        && again != 'n') {
                                                                                System.out.print(
                                                                                                Yellow + "\n\t\t\t\t\t\tInvalid input. Please enter Y/y or N/n: "
                                                                                                                + RESET);
                                                                                again = scanner.next().charAt(0);
                                                                        }
                                                                }

                                                                } while (again == 'y' || again == 'Y' );
                                                                
                                                                if (again == 'b'){
                                                                        clearScreen();
                                                                        break;
                                                                }


                                                                // Display all orders
                                                                clearScreen();
                                                                for (int j = 0; j < i; j++) {
                                                                        totalCost += orders[j].getTotalOrder();
                                                                }

                                                                System.out.println(
                                                                                "\n\n\n\t\t ----------------------------------------------------------------------------------");
                                                                System.out.println(
                                                                                "\t\t                            BaoBaoPizza ");
                                                                System.out.println(
                                                                                "\t\t                            ChinBS.WongRS,RayKKK,Evise  ");
                                                                System.out
                                                                                .println(
                                                                                                "\t\t                            L307, Suria KLCC, Kuala Lumpur City Center ");
                                                                System.out.println(
                                                                                "\t\t                            50088 Kuala Lumpur");
                                                                System.out.println(
                                                                                "\t\t ----------------------------------------------------------------------------------");
                                                                System.out.println("\t\t  You have ordered: ");
                                                                System.out.println(
                                                                                "\t\t ----------------------------------------------------------------------------------");
                                                                System.out.println(
                                                                                "\t\t                    ITEM\t            | UNIT PRICE | QUANTITY |   AMOUNT");
                                                                System.out.println(
                                                                                "\t\t ----------------------------------------------------------------------------------");

                                                                String[] foodAndQuantity = new String[i];

                                                                for (int j = 0; j < foodAndQuantity.length; j++) {
                                                                        Food order = orders[j];

                                                                        System.out.print("\t\t "
                                                                                        + order.getFullNameWithInfo()
                                                                                        + "  |  " + order.getPrice()
                                                                                        + "  |    "
                                                                                        + order.getQuantity()
                                                                                        + "     |  RM ");
                                                                        System.out.printf("%.2f",
                                                                                        order.getTotalOrder());
                                                                        System.err.println("");

                                                                        foodAndQuantity[j] = (j + 1) + ". "
                                                                                        + order.getFullNameWithInfo()
                                                                                        + " x "
                                                                                        + order.getQuantity();

                                                                }

                                                                System.out.println(
                                                                                "\t\t ----------------------------------------------------------------------------------");

                                                                System.out
                                                                                .print("\t\t                                | Total Amount               RM ");
                                                                System.out.printf("%.2f", totalCost);
                                                                System.out.println(
                                                                                "\n\t\t                                |--------------------------------------------------");
                                                                // Charge

                                                                switch (DT) {
                                                                        case 'a':
                                                                        case 'A':
                                                                                double service = totalCost
                                                                                                * 0.1;
                                                                                totalCost = totalCost + service;
                                                                                System.out
                                                                                                .print("\t\t                                | Service Charge (10%)       RM ");
                                                                                System.out.printf("%.2f",
                                                                                                service);
                                                                                System.out.println(
                                                                                                "\n\t\t                                |--------------------------------------------------");
                                                                                System.out
                                                                                                .print("\t\t                                | Total Bill                 RM ");
                                                                                System.out.printf("%.2f",
                                                                                                totalCost);
                                                                                System.out.println(
                                                                                                "\n\t\t ----------------------------------------------------------------------------------");
                                                                                System.out.println("\t\t  ");
                                                                                break;

                                                                        case 'b':
                                                                        case 'B':

                                                                                System.out.println(
                                                                                                "\t\t                                | Packaging Charge           RM  2.00");
                                                                                totalCost = totalCost + 2;
                                                                                System.out.println(
                                                                                                "\t\t                                |--------------------------------------------------");
                                                                                System.out
                                                                                                .print("\t\t                                | Total Bill                 RM ");
                                                                                System.out.printf("%.2f",
                                                                                                totalCost);
                                                                                System.out.println(
                                                                                                "\n\t\t ----------------------------------------------------------------------------------");
                                                                                System.out.println(
                                                                                                "\t\t\t\t\t  ");
                                                                                break;

                                                                }

                                                                paymentcheck = 1;
                                                                if (paymentcheck == 1) {

                                                                        totalCost = MembershipPointEarning
                                                                                        .PurchaseWithVoucher(
                                                                                                        loginResult,
                                                                                                        totalCost);
                                                                        if (totalCost == 0){
                                                                                clearScreen();
                                                                                break;
                                                                        }
                                                                        
                                                                        int paymentDecision = 0;
                                                                        do {
                                                                                System.out.println(
                                                                                                "\n\n\n\t\t,----------------------------------------------------------------,");
                                                                                System.out.print(
                                                                                                "\t\t| Grand Total : RM ");
                                                                                System.out.printf("%.2f", totalCost);
                                                                                System.out.println(
                                                                                                "                                         |");
                                                                                System.out.println(
                                                                                                "\t\t| Choose Paymant Method                                          |");
                                                                                System.out.println(
                                                                                                "\t\t| Online Banking ( No tax )-------------------------------- [1]  |");
                                                                                System.out.println(
                                                                                                "\t\t| Touch n Go eWallet ( 10% tax )--------------------------- [2]  |");
                                                                                System.out.println(
                                                                                                "\t\t| Cancel Payment ------------------------------------------ [3]  |");
                                                                                System.out.println(
                                                                                                "\t\t|                                                                |");
                                                                                System.out.println(
                                                                                                "\t\t'----------------------------------------------------------------'");
                                                                                System.out.print(
                                                                                                "\n\t\tEnter your choice : ");
                                                                                scanner.nextLine();

                                                                                String input = scanner.next();

                                                                                try {
                                                                                        // Convert the string input to
                                                                                        // an integer
                                                                                        paymentDecision = Integer
                                                                                                        .parseInt(input);

                                                                                        // Validate the range of payment
                                                                                        // options
                                                                                        if (paymentDecision >= 1
                                                                                                        && paymentDecision <= 3) {
                                                                                                switch (paymentDecision) {
                                                                                                        case 1:
                                                                                                                paymentSuc = 1;
                                                                                                                break;
                                                                                                        case 2:
                                                                                                                paymentSuc = 1;
                                                                                                                totalCost *= 1.1;
                                                                                                                break;
                                                                                                        case 3:
                                                                                                                totalCost = 0;
                                                                                                                clearScreen();
                                                                                                                System.out.println(
                                                                                                                                "\n\n\n\n\n\n\n\n\n\n\n");
                                                                                                                paymentSuc = 0;
                                                                                                                menu2Check = 0;
                                                                                                                break;
                                                                                                }
                                                                                        } else {
                                                                                                clearScreen();
                                                                                                System.out.println(
                                                                                                                Yellow + "\t\tInvalid valid. Please enter number 1 to 3."
                                                                                                                                + RESET);
                                                                                        }
                                                                                } catch (NumberFormatException e) {
                                                                                        // Handle non-integer input
                                                                                        clearScreen();
                                                                                        System.out.println(Yellow
                                                                                                        + "\t\tInvalid input. Please enter a number."
                                                                                                        + RESET);

                                                                                }

                                                                        } while (paymentDecision < 1
                                                                                        || paymentDecision > 3);
                                                                }

                                                                if (paymentSuc == 1) {
                                                                        clearScreen();
                                                                        ThankYouMessage();
                                                                        System.out.println("\n\n\n\n\n\n\t\t"
                                                                                        + membershipCardNumber
                                                                                        + " Payment Successful!");
                                                                        System.out.print("\t\tAmount Paid = RM ");
                                                                        System.out.printf("%.2f", totalCost);
                                                                        System.out.println("");
                                                                        CustomerOrder.writeOrder(loginResult,
                                                                                        foodAndQuantity);
                                                                        int pointsEarned = MembershipPointEarning
                                                                                        .updateMemberPoint(loginResult,
                                                                                                        totalCost);
                                                                        System.out.println(
                                                                                        "\t\tMembership Point Earned: "
                                                                                                        + pointsEarned
                                                                                                        + " Points");
                                                                                                        Policy.updateMemberPointOnBirthday(loginResult);

                                                                        if (totalCost >= 200) {
                                                                                 String[] prizes = {"1.8","1.5","1.0","2.0"
                                                                                                 };
                                                                                Random random = new Random();
                                                                                int index = random
                                                                                                .nextInt(prizes.length);
                                                                                String winningPrize = prizes[index];

                                                                                // Simulate spinning animation
                                                                                for (i = 0; i < 50; i++) {
                                                                                        System.out.print(
                                                                                                        "\r\t\tSpinning: x"
                                                                                                                        + prizes[i % prizes.length]);
                                                                                        try {
                                                                                                Thread.sleep(150); // Adjust
                                                                                                                   // the
                                                                                                                   // delay
                                                                                                                   // to
                                                                                                                   // change
                                                                                                                   // the
                                                                                                                   // speed
                                                                                                                   // of
                                                                                                                   // spinning
                                                                                        } catch (InterruptedException e) {
                                                                                                e.printStackTrace();
                                                                                        }
                                                                                }
                                                                                System.out.print("\r\t\tSpinning: x"
                                                                                                + winningPrize);

                                                                                System.out.println(
                                                                                                "\n\t\tCongratulations! You won x"
                                                                                                                + winningPrize
                                                                                                                + " Point Bonus!");

                                                                                int AdditionalPoint = Policy
                                                                                                .LuckyPoint(winningPrize,
                                                                                                                pointsEarned);
                                                                                System.out.println(
                                                                                                "\t\tAdditional Point earned: "
                                                                                                                + AdditionalPoint);
                                                                                Policy.updateMemberPoint(
                                                                                                loginResult,
                                                                                                AdditionalPoint);

                                                                        }

                                                                        System.out.println("\n\t\t1. Back to Customer Menu");
                                                                        System.out.println("\t\t2. Exit\n");

                                                                        int afterPay = getValidChoice();

                                                                        paymentcheck = 0;
                                                                        paymentSuc = 0;
                                                                        totalCost = 0;

                                                                        switch (afterPay) {
                                                                                case 1:
                                                                                        clearScreen();
                                                                                        menuCheck = 0;
                                                                                        break;

                                                                                case 2:
                                                                                        System.exit(1);
                                                                                        break;
                                                                        }
                                                                }

                                                                break;
                                                        }
                                                        
                                                        case 3:
                                                                clearScreen();
                                                                policyMenuPage();
                                                                break;

                                                        case 4:

                                                                clearScreen();
                                                                while (true) {

                                                                        System.out.println(
                                                                                        "\n\n\n\t\t\t\t -------------------------------------------------------");
                                                                        System.out.println(
                                                                                        "\t\t\t\t$$$              Membership Point Redeemtion                  $$$");
                                                                        System.out.println(
                                                                                        "\t\t\t\t -------------------------------------------------------");

                                                                        System.out.println(
                                                                                        "\n\t       *--[  9  ]--------*\t*--[ 10  ]--------*\t*--[ 11  ]--------*\t*--[ 12  ]--------*");
                                                                        System.out.println(
                                                                                        "\t       |                 |\t|                 |\t|                 |\t|                 |");
                                                                        System.out.println(
                                                                                        "\t       |    BAOBAO       |\t|   BAOBAO        |\t|     10% OFF     |\t|     20% OFF     |");
                                                                        System.out.println(
                                                                                        "\t       |    PIZZA        |\t|   PIZZA         |\t|     VOUCHER     |\t|     VOUCHER     |");
                                                                        System.out.println(
                                                                                        "\t       |    Cupholder    |\t|   Snackholder   |\t|                 |\t|                 |");
                                                                        System.out.println(
                                                                                        "\t       |                 |\t|                 |\t|                 |\t|                 |");
                                                                        System.out.println(
                                                                                        "\t       |                 |\t|                 |\t|                 |\t|                 |");
                                                                        System.out.println(
                                                                                        "\t       |                 |\t|                 |\t|                 |\t|                 |");
                                                                        System.out.println(
                                                                                        "\t       |                 |\t|                 |\t|                 |\t|                 |");
                                                                        System.out.println(
                                                                                        "\t       |                 |\t|                 |\t|                 |\t|                 |");
                                                                        System.out.println(
                                                                                        "\t       |                 |\t|                 |\t|                 |\t|                 |");
                                                                        System.out.println(
                                                                                        "\t       |   Point: 50     |\t|   Point: 70     |\t|   Point: 100    |\t|   Point: 200    |");
                                                                        System.out.println(
                                                                                        "\t       |                 |\t|                 |\t|                 |\t|                 |");
                                                                        System.out.println(
                                                                                        "\t       *-----------------*\t*-----------------*\t*-----------------*\t*-----------------*");

                                                                        System.out.println(Yellow
                                                                                        + "\t\tBack to main menu. (Press 13)"
                                                                                        + RESET);
                                                                        int RedeemItem;

                                                                        while (true) {
                                                                                System.out.print(
                                                                                                "\n\t\t            Enter your choice: ");
                                                                                if (scanner.hasNextInt()) {
                                                                                        RedeemItem = scanner.nextInt();
                                                                                        clearScreen();
                                                                                        break;
                                                                                } else {
                                                                                        System.out.println(
                                                                                                        Yellow + "\t\tInvalid choice. The choice must be between 9 and 13. Enter one more time"
                                                                                                                        + RESET);

                                                                                        scanner.next(); // Consume
                                                                                                        // invalid input
                                                                                }
                                                                        }

                                                                        boolean paymentSuccessful = false;

                                                                        switch (RedeemItem) {
                                                                                case 9:
                                                                                case 10:
                                                                                case 11:
                                                                                case 12:
                                                                                        paymentSuccessful = MembershipPointRedemption
                                                                                                        .MemberPointPurchase(
                                                                                                                        loginResult,
                                                                                                                        RedeemItem);

                                                                                        break;
                                                                                case 13:
                                                                                        clearScreen();
                                                                                        menuCheck = 0;
                                                                                        break;
                                                                                default:
                                                                                        if (RedeemItem < 9
                                                                                                        || RedeemItem > 13) {
                                                                                                System.out.println(
                                                                                                                Yellow + "\t\tInvalid choice. Please enter valid number. Enter one more time"
                                                                                                                                + RESET);
                                                                                        } else {
                                                                                                System.out.println(
                                                                                                                Yellow + "\t\tInvalid choice. The choice must be between 9 and 13. Enter one more time"
                                                                                                                                + RESET);
                                                                                        }
                                                                                        break;

                                                                        }
                                                                        if (RedeemItem >= 9 && RedeemItem <= 13) {
                                                                                break;
                                                                        }
                                                                }
                                                                break;

                                                        case 5:
                                                                menu2Check = 1;
                                                                menuCheck = 0;
                                                                break;
                                                }
                                        } else {
                                                System.out.println("");
                                                System.out.println(
                                                                Yellow + "\t\tInvalid choice. Please enter valid number. Enter one more time"
                                                                                + RESET);

                                                scanner.nextLine(); // Wait for user to press Enter
                                        }
                                } else {
                                        scanner.nextLine();
                                        System.out.println("");
                                        System.out
                                                        .println(Yellow + "\t\tInvalid input. Please enter a number. Enter one more time."
                                                                        + RESET);

                                        scanner.nextLine(); // Wait for user to press Enter
                                }
                        } while (option != 5);

                }
                while (menu2Check == 0)
                        ;

        }

        private static int getValidChoice() {
                String Yellow = "\u001B[33m";
                String RESET = "\u001B[0m";
                int choice;

                do {
                        // Prompt for user choice
                        System.out.print("\t\tEnter your choice: ");

                        // Check if input is an integer
                        if (scanner.hasNextInt()) {
                                choice = scanner.nextInt();

                                // Check if input is within the valid range (1 or 2)
                                if (choice >= 1 && choice <= 2) {
                                        return choice; // Return valid choice
                                } else {
                                        // Display error message for out-of-range input
                                        System.out.println(Yellow + "\n\t\tInvalid choice. Please enter 1 or 2.\n"
                                                        + RESET);
                                }
                        } else {
                                // Consume invalid input (to clear the scanner buffer)
                                String invalidInput = scanner.next();

                                // Display error message for non-integer input
                                System.out.println(Yellow + "\n\t\tInvalid input. Please enter a number.\n" + RESET);
                        }
                } while (true); // Repeat until valid input is provided
        }

        private static void displayMainMenu() {
                System.out.println("\n\t\t  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>            \n");
                System.out.println(
                                "\t\t$$\\      $$\\ $$$$$$$$\\ $$\\       $$$$$$\\   $$$$$$\\  $$\\      $$\\ $$$$$$$$\\ ");
                System.out.println(
                                "\t\t$$ | $\\  $$ |$$  _____|$$ |     $$  __$$\\ $$  __$$\\ $$$\\    $$$ |$$  _____|");
                System.out.println(
                                "\t\t$$ |$$$\\ $$ |$$ |      $$ |     $$ /  \\__|$$ /  $$ |$$$$\\  $$$$ |$$ |      ");
                System.out.println(
                                "\t\t$$ $$ $$\\$$ |$$$$$\\    $$ |     $$ |      $$ |  $$ |$$\\$$\\$$ $$ |$$$$$\\    ");
                System.out.println("\t\t$$$$  _$$$$ |$$  __|   $$ |     $$ |      $$ |  $$ |$$ \\$$$  $$ |$$  __|   ");
                System.out.println(
                                "\t\t$$$  / \\$$$ |$$ |      $$ |     $$ |  $$\\ $$ |  $$ |$$ |\\$  /$$ |$$ |      ");
                System.out.println(
                                "\t\t$$  /   \\$$ |$$$$$$$$\\ $$$$$$$$\\ $$$$$$  | $$$$$$  |$$ | \\_/ $$ |$$$$$$$$\\ ");
                System.out
                                .println("\t\t\\__/     \\__|\\________|\\________|\\______/  \\______/ \\__|     \\__|\\________|\n");
                System.out.println("\n\t\t      To    BaoBaoPizza!      >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                System.out.println("\n\t\tSelect an option:");
                System.out.println("\t\t1. Register New Customer");
                System.out.println("\t\t2. Customer Login");
                System.out.println("\t\t3. Staff Login");
                System.out.println("\t\t4. Exit");
                System.out.print("\n\t\tEnter your choice: ");
        }

        private static void ThankYouMessage(){
                System.out.println("\t\t _________ __                    __        ____  ____               _ _ _  ");
                System.out.println("\t\t|  _   _  [  |                  [  |  _   |_  _||_  _|             | | | | ");
                System.out.println("\t\t|_/ | | \\_|| |--.  ,--.  _ .--.  | | / ]    \\ \\  / / .--.  __   _  | | | | ");
                System.out.println("\t\t    | |    | .-. |`'_\\ :[ `.-. | | '' <      \\ \\/ // .'`\\ [  | | | | | | | ");
                System.out.println("\t\t   _| |_   | | | |// | |,| | | | | |`\\ \\     _|  |_| \\__. || \\_/ |,|_|_|_| ");
                System.out.println("\t\t  |_____| [___]|__]'-;__[___||__|__|  \\_]   |______|'.__.' '.__.'_/(_|_|_) ");
                System.out.println("\n\t\t\t\t\t\t\t\t Please enjoy your meals!!");
                System.out.println("\t\t\t\t\t  Have a pleasant day and hope to see you again!!!");
        }

        private static void MainCustomerMenu() {
                System.out.println("\n\t\t,-----------------------------------------------------------,");
                System.out.println("\t\t|                 >> MAIN CUSTOMER MENU <<                  |");
                System.out.println("\t\t|                                                           |");
                System.out.println("\t\t| 1. Account Information                                    |");
                System.out.println("\t\t| 2. Food Ordering Menu                                     |");
                System.out.println("\t\t| 3. Policy Page                                            |");
                System.out.println("\t\t| 4. Membership Point Redemption                            |");
                System.out.println("\t\t| 5. Back to login screen                                   |");
                System.out.println("\t\t'-----------------------------------------------------------'");

                System.out.print("\n\t\t Enter your choice: ");
        }

        private static void policyMenuPage(){
                System.out.println("\n\t\t,-------------------------------------------------------------------------------,");
                System.out.println("\t\t|                                >> POLICY MENU <<                              |");
                System.out.println("\t\t|                                                                               |");
                System.out.println("\t\t| 1. Customers can make payments for their orders using two methods             |");
                System.out.println("\t\t|    such as Touch N Go or Online Banking.                                      |");
                System.out.println("\t\t|    Additionally, they have the option to apply voucher codes                  |");
                System.out.println("\t\t|    for discounts.                                                             |");
                System.out.println("\t\t|                                                                               |");
                System.out.println("\t\t| 2. The amount paid by customers will be converted to points, where            |");
                System.out.println("\t\t|    RM10 spent equals 1 point. These points can later be redeemed              |");
                System.out.println("\t\t|    for merchandises or discount vouchers, effectively                         |");
                System.out.println("\t\t|    reducing the amount to pay in subsequent                                   |");System.out.println("\t\t|    purchases.                                                                 |");
                System.out.println("\t\t|                                                                               |");
                System.out.println("\t\t| 3. Customers earn points based on their spending, with bonuses awarded for    |");System.out.println("\t\t|    reaching certain thresholds. They receive a x1.5 bonus                     |");System.out.println("\t\t|    after accumulating 1000 points, a x1.8 bonus after 2000 points,            |");
                System.out.println("\t\t|    and a x2 bonus after 3000 points.                                          |");
                System.out.println("\t\t|                                                                               |");
                System.out.println("\t\t| 4. Upon spending RM200 or more in a single purchase, customers become eligible|");
                System.out.println("\t\t|    to participate in a lucky draw activity, potentially winning bonus points. |");
                System.out.println("\t\t|                                                                               |");
                System.out.println("\t\t| 5. During customers' birthday date, they will receive an additional 5 points  |");
                System.out.println("\t\t|    for every purchase made.                                                   |");
                System.out.println("\t\t|                                                                               |");
                System.out.println("\t\t'-------------------------------------------------------------------------------'");

        }

        private static void DineinOrTakeAway() {
                String Yellow = "\u001B[33m";
                String RESET = "\u001B[0m";

                System.out
                                .println("\n\n\t                --[ A ]-------------------------------------------------------------");
                System.out.println(
                                "\t               |                                                     .....           |");
                System.out.println(
                                "\t               |                                                 _.:.--|--.:._       |");
                System.out.println(
                                "\t               |                                               .: .'\\o  | o /'. '.   |");
                System.out
                                .println("\t               |                  DINE-IN                     // '.  \\ o|  /  o '.\\  |");
                System.out
                                .println("\t               |                                             //'.o'. \\ |o/ o.-'o  \\\\ |");
                System.out.println(
                                "\t               |                                             || o '-.'.\\|/.-'_o __|| |");
                System.out.println(
                                "\t               |                                             ||--o--o-->|            |");
                System.out.println(
                                "\t               ----------------------------------------------------------------------");
                System.out.println(
                                "\n\t                --[ B ]--------------------------------------------------------------");
                System.out.println(
                                "\t               |                                              _____________          |");
                System.out.println(
                                "\t               |                                             |             |____     |");
                System.out.println(
                                "\t               |                 Take-Away                   |  DELIVERY   |    \\    |");
                System.out.println(
                                "\t               |                                             |   ,,,       | ,,, \\   |");
                System.out.println(
                                "\t               |                                             |__@@@@@______|@@@@@_|  |");
                System.out.println(
                                "\t               |                                                '@@@'       '@@@'    |");
                System.out.println(
                                "\t                ---------------------------------------------------------------------");
                                System.out.println(Yellow
                                + "\t\t        Back to main menu. (Press 0)"
                                + RESET);
                System.out.print("\n\t                Dine-in or Take-away? (A/a or B/b): ");
        }

        private static int OrderMenu(int menuChoice) {
                Scanner scanner = new Scanner(System.in);
                int choice = 0;
                String Yellow = "\u001B[33m";
                String RESET = "\u001B[0m";

                System.out.println(
                                "\n\n\n                                    _____                                                 ");
                System.out.println(
                                "                                   (,   ,)                Welcome to BaoBaoPizza!       ");
                ;
                System.out.println(
                                "                                  ,_|!!!|_, ______,      ,______, ,___,       --~~~~.             ");
                System.out.println(
                                "                                  |///////|/////,|______| o   o|/o   |_,  ,__|%=@%%/     ");
                System.out.println(
                                "                                  |   |   |   |  |   ____|   o  |   o|  |  | |o%%%/      ");
                System.out.println(
                                "                                  |   |   |   |  |  |____| o |  | o  |  |  | |%%o/       ");
                System.out.println(
                                "                                  |   |   |   |  |   ____|   | o|   o|  |__| |(_/        ");
                System.out.println(
                                "                                  |   |   |   |  |  |____| o |   o   |       |o/         ");
                System.out.println(
                                "                                  |___|______/|__|_______|___|____/|_|_______|/          ");

                System.out.println(
                                "\n\t                      ! &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& !   ");
                System.out.println(
                                "\n\t                      * ------------------------------------------------------ *   ");
                System.out.println(
                                "\n\t                      [ %        Pizza                                      1 ]   ");
                System.out.println(
                                "\t                      [ %        Sides                                      2 ]   ");
                System.out.println(
                                "\t                      [ %        Beverage                                   3 ]   ");
                                System.out.println(
                                        "\t                      [ %        Back to Main Menu                          0 ]   ");

                System.out.println(
                                "\n\t                      * &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& *   ");
                do {
                        System.out.print("\n\t\t\t\tPlease Enter your Choice: ");
                        if (scanner.hasNextInt()) {
                                choice = scanner.nextInt();
                                if (choice < 1 || choice > 3) {

                                        System.out.println(Yellow
                                                        + "\n\t\t\t\tInvalid choice. Please enter a number between 0 and 3."
                                                        + RESET);
                                        continue;

                                }
                        } else {

                                System.out.println(Yellow + "\n\t\t\t\tInvalid input. Please enter a number." + RESET);
                                scanner.next(); // Clear the input buffer
                                continue;

                        }
                } while (choice < 0 || choice > 3);

                return choice;
        }

        private static void registerNewCustomer() {
                String Yellow = "\u001B[33m";
                String RESET = "\u001B[0m";

                Customer newCustomer = CustomerRegistrationModule.registerNewCustomer();
                // Display registered customer details
                clearScreen();
                System.out.println(Yellow + "\n\t\tNew Customer Registered:\n" + RESET);
                newCustomer.displayInformation();
        }

        private static void staffLogin() {
                System.out.print("\n\t\tEnter staff ID: ");
                String staffID = scanner.nextLine();
                System.out.print("\t\tEnter password: ");
                String password = scanner.nextLine();

                // Create a Staff object with the entered credentials
                Staff staff = new Staff("Travis Scott", "0163875233", "Malaysia", "06/10/2004", staffID, password);

                // Call staffLogin method of Staff class
                staff.staffLogin();
        }


}
