package coffeeshopmanager;

public class Coffee extends MenuItem implements Discountable {

    public Coffee(String name, double price) {
        super(name, price);
    }

    @Override
    public double getFinalPrice() {
        return getDiscountPrice();
    }

    @Override
    public double getDiscountPrice() {
        return price * 0.9; // ลด 10%
    }
}
