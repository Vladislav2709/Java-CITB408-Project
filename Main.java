package magazin;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Store store = new Store("Моят Магазин", 30, 50, 5, 20);

        Product p1 = new FoodProduct(1, "Кашкавал", 5.00, LocalDate.now().plusDays(4), 20);
        Product p2 = new NonFoodProduct(2, "Шампоан", 3.00, LocalDate.now().plusDays(20), 15);

        store.addProduct(p1);
        store.addProduct(p2);

        Cashier c1 = new Cashier(1, "Владислав Колев", 1200);
        store.addCashier(c1);

        Map<Integer, Integer> order = new HashMap<>();
        order.put(1, 2); // 2 кашкавала
        order.put(2, 1); // 1 шампоан

        try {
            Receipt r = store.sellProducts(c1, order);
            System.out.println(r);
        } catch (InsufficientQuantityException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nБрой издадени касови бележки: " + store.getReceiptCount());
        System.out.printf("Оборот: %.2f лв.\n", store.getTotalRevenue());
        System.out.printf("Разходи (заплати + доставки): %.2f лв.\n", store.getTotalSalaries() + store.getTotalPurchaseCost());
        System.out.printf("Печалба: %.2f лв.\n", store.getProfit());
    }
}