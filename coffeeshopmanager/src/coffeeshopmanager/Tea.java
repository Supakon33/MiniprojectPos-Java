package coffeeshopmanager;

public class Tea extends MenuItem {
    public Tea(String name, double price) {
        super(name, price);
    }

    @Override
    public double getFinalPrice() {
        return price;
    }
}
