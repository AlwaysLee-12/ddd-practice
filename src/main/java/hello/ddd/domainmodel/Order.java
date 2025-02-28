package hello.ddd.domainmodel;

import static hello.ddd.domainmodel.OrderState.CANCELED;
import static hello.ddd.domainmodel.OrderState.PAYMENT_WAITING;
import static hello.ddd.domainmodel.OrderState.PREPARING;
import static hello.ddd.domainmodel.OrderState.SHIPPED;

import java.util.List;

public class Order {

    private List<OrderLine> orderLines;
    private ShippingInfo shippingInfo;
    private int totalAmount;
    private OrderState state;

    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmount();
    }

    private void verifyAtLeastOrMoreOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    private void calculateTotalAmount() {
        this.totalAmount = orderLines.stream()
                .mapToInt(OrderLine::getAmounts)
                .sum();
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        if (shippingInfo == null) {
            throw new IllegalArgumentException("no shippingInfo");
        }
        this.shippingInfo = shippingInfo;
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        verifyNotYetShipped();
        setShippingInfo(newShippingInfo);
    }

    public void cancel(){
        verifyNotYetShipped();
        this.state = CANCELED;
    }

    private void verifyNotYetShipped() {
        if (!PAYMENT_WAITING.equals(state) && !PREPARING.equals(state)) {
            throw new IllegalStateException("already shipped");
        }
    }

    public void changeShipped() {
        this.state = SHIPPED;
    }

    public void completePayment() {
        this.state = PREPARING;
    }
}
