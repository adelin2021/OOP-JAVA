package bakery.entities.drinks;
//created by J.M.

public class Water extends BaseDrink {

    public static final double WATER_PRICE = 1.50;

    public Water(String name, int portion, String brand) {

        super(name, portion, WATER_PRICE, brand);
    }

}
