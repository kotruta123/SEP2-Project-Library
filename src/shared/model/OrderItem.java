package shared.model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private int orderItemId;
    private Order order;
    private Item item;
    private int quantity;

    public OrderItem(int orderItemId, Order order, Item item, int quantity) {
        this.orderItemId = orderItemId;
        this.order = order;
        this.item = item;
        this.quantity = quantity;
    }

    public OrderItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", order=" + order +
                ", item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
