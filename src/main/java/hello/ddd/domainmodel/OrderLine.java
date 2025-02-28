package hello.ddd.domainmodel;

public class OrderLine {

    private Product product;
    private int quantity;
    private Money price;
    private Money amounts;

    public OrderLine(Product product, int quantity, Money price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.amounts = calculateAmount();
    }

    private Money calculateAmount() {
        return price.multiply(quantity);
    }

    public Money getAmounts() {
        return amounts;
    }
}
