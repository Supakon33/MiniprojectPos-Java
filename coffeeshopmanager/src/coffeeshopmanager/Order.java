package coffeeshopmanager;

import java.util.ArrayList;

public class Order {
    private ArrayList<OrderItem> orderItems;

    public Order() {
        orderItems = new ArrayList<>();
    }

    public void addItem(MenuItem item, int quantity) {
        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem oi = orderItems.get(i);
            if (oi.getItem().getName().equals(item.getName())) {
                orderItems.set(i, new OrderItem(item, oi.getQuantity() + quantity));
                return;
            }
        }
        orderItems.add(new OrderItem(item, quantity));
    }

    public void removeItemByIndex(int index) {
        if (index >= 0 && index < orderItems.size()) {
            orderItems.remove(index);
        }
    }

    public double getTotalPrice() {
        double total = 0;
        for (OrderItem oi : orderItems) {
            total += oi.getTotalPrice();
        }
        return total;
    }

    public String getOrderSummary() {
        StringBuilder sb = new StringBuilder();
        for (OrderItem oi : orderItems) {
            sb.append(oi.getItem().getName())
              .append(" x ")
              .append(oi.getQuantity())
              .append(" = ")
              .append(String.format("%.2f", oi.getTotalPrice()))
              .append(" Baht\n");
        }
        sb.append("------------------\nTotal: ").append(String.format("%.2f", getTotalPrice())).append(" Baht");
        return sb.toString();
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }
}
