package bakery.entities.drinks;
//created by J.M.

public class Tea extends BaseDrink {

    public static final double TEA_PRICE = 2.50;

    public Tea(String name, int portion, String brand) {

        super(name, portion, TEA_PRICE, brand);
    }

}
