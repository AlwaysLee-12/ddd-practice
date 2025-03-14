package hello.ddd.domain.order.model;

import static hello.ddd.domain.order.policy.OrderState.CANCELED;
import static hello.ddd.domain.order.policy.OrderState.PAYMENT_WAITING;
import static hello.ddd.domain.order.policy.OrderState.PREPARING;
import static hello.ddd.domain.order.policy.OrderState.SHIPPED;

import hello.ddd.domain.event.Events;
import hello.ddd.domain.event.OrderCanceledEvent;
import hello.ddd.domain.order.vo.Money;
import hello.ddd.domain.order.vo.OrderNo;
import hello.ddd.domain.order.policy.OrderState;
import java.util.List;

public class Order {

    private OrderNo orderNo;
    private List<OrderLine> orderLines;
    private ShippingInfo shippingInfo;
    private Money totalAmount;
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
                .map(OrderLine::getAmounts)
                .reduce(new Money(0), Money::add);
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
        Events.raise(new OrderCanceledEvent(orderNo.getNo()));
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
