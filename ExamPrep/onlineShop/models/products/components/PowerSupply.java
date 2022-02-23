package onlineShop.models.products.components;
//created by J.M.

public class PowerSupply extends BaseComponent{

    private static double multiplier = 1.05;


    public PowerSupply(int id,
                       String manufacturer,
                       String model,
                       double price,
                       double overallPerformance,
                       int generation) {

        super(id, manufacturer, model, price, overallPerformance * multiplier, generation);
    }
}
