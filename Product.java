package magazin;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Product implements Serializable {
    private int id;
    private String name;
    private double purchasePrice;
    private String category;
    private LocalDate expiryDate;
    private int quantity;

    public Product(int id, String name, double purchasePrice, String category, LocalDate expiryDate, int quantity) {
        this.id = id;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.category = category;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPurchasePrice() { return purchasePrice; }
    public String getCategory() { return category; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public abstract double getSellingPrice(LocalDate currentDate, int daysBeforeExpiry, double discountPercentage, double markupPercentage);

    public boolean isExpired(LocalDate currentDate) {
        return currentDate.isAfter(expiryDate);
    }
}