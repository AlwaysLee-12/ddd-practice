package hello.ddd.domain.order.model;

import hello.ddd.domain.order.vo.Address;
import hello.ddd.domain.order.vo.Receiver;

public class ShippingInfo {

    private Receiver receiver;
    private Address address;

    public ShippingInfo(Receiver receiver, Address address) {
        this.receiver = receiver;
        this.address = address;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public Address getAddress() {
        return address;
    }
}
