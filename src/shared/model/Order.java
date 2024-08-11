package shared.model;

import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    private int orderId;
    private User user;
    private double totalPrice;
    private List<OrderItem> orderItems;

    public Order(int orderId, User user, double totalPrice, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.user = user;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }

    public Order(int orderId, ObservableList<OrderItem> orderItems) {
        this.orderId = orderId;
        this.orderItems = new ArrayList<>(orderItems);
        calculateTotalPrice();
    }

    public Order(User user, ObservableList<OrderItem> orderItems) {
        this.user = user;
        this.orderItems = new ArrayList<>(orderItems);
        calculateTotalPrice();
    }

    public Order(List<Item> items) {
        this.orderItems = new ArrayList<>();
        for (Item item : items) {
            OrderItem orderItem = new OrderItem(item, 1);
            this.orderItems.add(orderItem);
        }
        calculateTotalPrice();
    }

    public Order(int userId, double v) {
        this.user = new User(userId, "", "", "", 0, "");
        this.totalPrice = v;
    }

    public Order() {

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    private void calculateTotalPrice() {
        this.totalPrice = orderItems.stream()
                .mapToDouble(item -> item.getItem().getPrice() * item.getQuantity())
                .sum();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", totalPrice=" + totalPrice +
                ", orderItems=" + orderItems +
                '}';
    }
}
