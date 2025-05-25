package magazin;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Store {
    private String name;
    private double foodMarkup;
    private double nonFoodMarkup;
    private int daysBeforeExpiry;
    private double discountPercentage;
    private List<Product> products = new ArrayList<>();
    private List<Cashier> cashiers = new ArrayList<>();
    private List<Receipt> receipts = new ArrayList<>();

    public Store(String name, double foodMarkup, double nonFoodMarkup, int daysBeforeExpiry, double discountPercentage) {
        this.name = name;
        this.foodMarkup = foodMarkup;
        this.nonFoodMarkup = nonFoodMarkup;
        this.daysBeforeExpiry = daysBeforeExpiry;
        this.discountPercentage = discountPercentage;
    }

    public void addProduct(Product p) { products.add(p); }
    public void addCashier(Cashier c) { cashiers.add(c); }

    public Receipt sellProducts(Cashier cashier, Map<Integer, Integer> productQuantities) throws InsufficientQuantityException {
        List<SoldItem> soldItems = new ArrayList<>();
        double total = 0;
        LocalDate today = LocalDate.now();

        for (var entry : productQuantities.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();
            Product p = products.stream().filter(x -> x.getId() == productId).findFirst().orElse(null);
            if (p == null || p.isExpired(today)) continue;

            if (p.getQuantity() < quantity) {
                throw new InsufficientQuantityException("Липсва достатъчно количество от: " + p.getName() +
                        ". Нужно: " + quantity + ", налично: " + p.getQuantity());
            }

            double markup = p instanceof FoodProduct ? foodMarkup : nonFoodMarkup;
            double price = p.getSellingPrice(today, daysBeforeExpiry, discountPercentage, markup);
            soldItems.add(new SoldItem(p.getName(), quantity, price * quantity));
            total += price * quantity;
            p.setQuantity(p.getQuantity() - quantity);
        }

        Receipt receipt = new Receipt(cashier, soldItems, total);
        receipts.add(receipt);
        saveReceiptToFile(receipt);
        return receipt;
    }

    private void saveReceiptToFile(Receipt receipt) {
        String filename = "receipt_" + receipt.getReceiptNumber() + ".txt";
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(receipt.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        SerializationUtils.saveObject(receipt, "receipt_" + receipt.getReceiptNumber() + ".ser");
    }

    public int getReceiptCount() { return receipts.size(); }

    public double getTotalRevenue() {
        return receipts.stream().mapToDouble(Receipt::getTotal).sum();
    }

    public double getTotalSalaries() {
        return cashiers.stream().mapToDouble(Cashier::getSalary).sum();
    }

    public double getTotalPurchaseCost() {
        return products.stream().mapToDouble(p -> p.getPurchasePrice() * p.getQuantity()).sum();
    }

    public double getProfit() {
        return getTotalRevenue() - (getTotalSalaries() + getTotalPurchaseCost());
    }
}