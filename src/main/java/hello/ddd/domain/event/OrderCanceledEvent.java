package hello.ddd.domain.event;

public class OrderCanceledEvent extends Event {

    private String orderNumber;

    public OrderCanceledEvent(String orderNumber) {
        super();
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
}
