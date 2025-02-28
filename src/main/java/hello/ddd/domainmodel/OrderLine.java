package hello.ddd.domainmodel;

public class OrderLine {

    private Product product;
    private int quantity;
    private int price;
    private int amounts;

    public OrderLine(Product product, int quantity, int price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.amounts = calculateAmount();
    }

    public int calculateAmount() {
        return price * quantity;
    }

    public int getAmounts() {
        return amounts;
    }
}
