public class Item {
    private int id;
    private String name;
    private int quantity;
    private double price;

    // Constructor to create a new Item object
    public Item(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // --- Getters (Methods to read the private fields) ---
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // --- Setter (Method to update the quantity) ---
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // --- Override the toString method for easy printing ---
    @Override
    public String toString() {
        return "ID: " + id +
                " | Name: " + name +
                " | Quantity: " + quantity +
                " | Price: $" + String.format("%.2f", price);
    }
}
