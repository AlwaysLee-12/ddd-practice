package hello.ddd.domainmodel;

public class ShippingInfo {

    private String receiveName;
    private String receiverPhoneNumber;
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingZipcode;

    public ShippingInfo(String receiveName, String receiverPhoneNumber, String shippingAddress1,
            String shippingAddress2, String shippingZipcode) {
        this.receiveName = receiveName;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.shippingAddress1 = shippingAddress1;
        this.shippingAddress2 = shippingAddress2;
        this.shippingZipcode = shippingZipcode;
    }
}
