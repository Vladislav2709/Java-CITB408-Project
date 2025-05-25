package magazin;

import java.time.LocalDate;

public class FoodProduct extends Product {
    public FoodProduct(int id, String name, double purchasePrice, LocalDate expiryDate, int quantity) {
        super(id, name, purchasePrice, "Хранителна", expiryDate, quantity);
    }

    @Override
    public double getSellingPrice(LocalDate currentDate, int daysBeforeExpiry, double discountPercentage, double markupPercentage) {
        double price = getPurchasePrice() * (1 + markupPercentage / 100);
        if (!isExpired(currentDate) && getExpiryDate().minusDays(daysBeforeExpiry).isBefore(currentDate)) {
            price = price * (1 - discountPercentage / 100);
        }
        return price;
    }
}