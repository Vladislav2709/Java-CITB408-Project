package magazin;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Receipt implements Serializable {
    private static int receiptCounter = 0;
    private int receiptNumber;
    private Cashier cashier;
    private LocalDateTime dateTime;
    private List<SoldItem> items;
    private double total;

    public Receipt(Cashier cashier, List<SoldItem> items, double total) {
        this.receiptNumber = ++receiptCounter;
        this.cashier = cashier;
        this.dateTime = LocalDateTime.now();
        this.items = items;
        this.total = total;
    }

    public int getReceiptNumber() { return receiptNumber; }
    public Cashier getCashier() { return cashier; }
    public LocalDateTime getDateTime() { return dateTime; }
    public List<SoldItem> getItems() { return items; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Касова бележка №").append(receiptNumber).append("\n");
        sb.append("Касиер: ").append(cashier).append("\n");
        sb.append("Дата/час: ").append(dateTime).append("\n");
        sb.append("Продукти:\n");
        for (SoldItem item : items) {
            sb.append("- ").append(item.getProductName()).append(" (x").append(item.getQuantity())
              .append(") - ").append(String.format("%.2f", item.getPrice())).append(" лв.\n");
        }
        sb.append("Общо: ").append(String.format("%.2f", total)).append(" лв.");
        return sb.toString();
    }
}