public class Food {
    private String name;
    private String price;
    private double priceCal;
    private int quantity;
    private double totalOrder;

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getPriceCal() {
        return priceCal;
    }

    public void setPriceCal(double priceCal) {
        this.priceCal = priceCal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(double totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getFullNameWithInfo() {
        return name;
    }
}
