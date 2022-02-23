package bakery.entities.bakedFoods;
//created by J.M.

public class Bread extends BaseFood {

    public static final double INITIAL_BREAD_PORTION = 200;


    public Bread(String name, double price) {

        super(name, INITIAL_BREAD_PORTION, price);
    }
}
