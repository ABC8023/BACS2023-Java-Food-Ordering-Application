import java.util.Scanner;

public class ItemList {

    private static final Scanner scanner = new Scanner(System.in);

    public static Pizza createPizza(int pizzaChoice) {

        switch (pizzaChoice) {
            case 1:
                return new Pizza("Zucchini & Gorgonzola", "RM 22.00", 22.0);
            case 2:
                return new Pizza("Truffle & Mushroom   ", "RM 23.00", 23.0);
            case 3:
                return new Pizza("Pepperoni Gourmet    ", "RM 22.00", 22.0);
            case 4:
                return new Pizza("Vegetarian           ", "RM 19.50", 19.5);
            default:
                throw new IllegalStateException("Unexpected value: " + pizzaChoice);
        }
    }

    public static Side createSide(int sideChoice) {

        switch (sideChoice) {
            case 1:
                return new Side("Garlic Bread      ", "RM  8.00", 8.0);
            case 2:
                return new Side("French Fries      ", "RM 10.00", 10.0);
            case 3:
                return new Side("Onion Rings       ", "RM  9.00", 9.0);
            case 4:
                return new Side("Mashed Potato     ", "RM  8.00", 8.0);
            default:
                throw new IllegalStateException("Unexpected value: " + sideChoice);
        }
    }

    public static Beverage createBeverage(int beverageChoice) {

        switch (beverageChoice) {
            case 1:
                return new Beverage("Coke                ", "RM  5.00", 5.0);
            case 2:
                return new Beverage("Orange Juice        ", "RM  6.00", 6.0);
            case 3:
                return new Beverage("Iced Lemon Tea      ", "RM  7.00", 7.0);
            case 4:
                return new Beverage("Hot Chocolate       ", "RM  8.00", 8.0);
            default:
                throw new IllegalStateException("Unexpected value: " + beverageChoice);
        }
    }
}
