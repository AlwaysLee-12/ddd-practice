package hello.ddd.domainmodel;

public class Money {

    private int value;

    public Money(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Money add(Money money) {
        return new Money(value + money.getValue());
    }

    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }
}
