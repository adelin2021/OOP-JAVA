package onlineShop.models.products.peripherals;
//created by J.M.

public class Keyboard extends BasePeripheral{

    public Keyboard(int id,
                    String manufacturer,
                    String model,
                    double price,
                    double overallPerformance,
                    String connectionType) {

        super(id, manufacturer, model, price, overallPerformance, connectionType);
    }
}
