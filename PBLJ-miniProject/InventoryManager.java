import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryManager {

    // Use a List to store all Item objects
    private static List<Item> inventory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextId = 1; // Counter for unique item IDs

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("--- Simple Inventory Management System ---");

        while (running) {
            displayMenu();
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addItem();
                        break;
                    case 2:
                        viewInventory();
                        break;
                    case 3:
                        checkStock();
                        break;
                    case 4:
                        System.out.println("Exiting the Inventory Manager. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add New Item");
        System.out.println("2. View All Inventory");
        System.out.println("3. Check and Update Stock");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addItem() {
        System.out.println("\n--- Add New Item ---");
        System.out.print("Enter Item Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Quantity: ");
        // Input validation for quantity
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid quantity. Enter a number: ");
            scanner.next();
        }
        int quantity = scanner.nextInt();

        System.out.print("Enter Price: $");
        // Input validation for price
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid price. Enter a number: $");
            scanner.next();
        }
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        // Create the new Item object
        Item newItem = new Item(nextId++, name, quantity, price);
        inventory.add(newItem);

        System.out.println("\nItem added successfully: " + newItem.getName() + " (ID: " + newItem.getId() + ")");
    }

    private static void viewInventory() {
        System.out.println("\n--- Current Inventory ---");
        if (inventory.isEmpty()) {
            System.out.println("The inventory is currently empty.");
        } else {
            for (Item item : inventory) {
                System.out.println(item); // Uses the overridden toString method
            }
        }
    }

    private static void checkStock() {
        System.out.println("\n--- Check and Update Stock ---");
        System.out.print("Enter the ID of the item to update: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid ID format.");
            scanner.nextLine(); // Clear the invalid input
            return;
        }
        int idToFind = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Loop through the inventory to find the item by ID
        Item foundItem = null;
        for (Item item : inventory) {
            if (item.getId() == idToFind) {
                foundItem = item;
                break; // Stop searching once found
            }
        }

        if (foundItem != null) {
            System.out.println("Current Stock for " + foundItem.getName() + ": " + foundItem.getQuantity());
            System.out.print("Enter the NEW quantity (e.g., 50): ");

            // Input validation for new quantity
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid quantity. Enter a number: ");
                scanner.next();
            }
            int newQuantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            foundItem.setQuantity(newQuantity);
            System.out.println(foundItem.getName() + " stock updated successfully to: " + newQuantity);
        } else {
            System.out.println("Error: Item with ID " + idToFind + " not found.");
        }
    }
}
