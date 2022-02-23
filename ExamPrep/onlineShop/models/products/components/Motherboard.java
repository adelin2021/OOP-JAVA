package onlineShop.models.products.components;
//created by J.M.

public class Motherboard extends BaseComponent{

    private static double multiplier = 1.25;

    public Motherboard(int id,
                       String manufacturer,
                       String model,
                       double price,
                       double overallPerformance,
                       int generation) {

        super(id, manufacturer, model, price, overallPerformance * multiplier, generation);
    }
}
