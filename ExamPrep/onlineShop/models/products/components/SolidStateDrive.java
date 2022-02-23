package onlineShop.models.products.components;
//created by J.M.

public class SolidStateDrive extends BaseComponent{

    private static double multiplier = 1.20;

    public SolidStateDrive(int id,
                           String manufacturer,
                           String model,
                           double price,
                           double overallPerformance,
                           int generation) {

        super(id, manufacturer, model, price, overallPerformance * multiplier, generation);
    }
}
