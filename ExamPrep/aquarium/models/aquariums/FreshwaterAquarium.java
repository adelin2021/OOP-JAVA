package aquarium.models.aquariums;
//created by J.M.

public class FreshwaterAquarium extends BaseAquarium {
    private static final int CAPACITY_AQUARIUM_CONST = 50;
    public static final String AQUARIUM_TYPE_CONST = "FreshwaterAquarium";

    public FreshwaterAquarium(String name) {
        super(name, CAPACITY_AQUARIUM_CONST);
    }


    @Override
    public String getInfo() {

        return String.format("%s (%s):", super.getName(), AQUARIUM_TYPE_CONST) +
                System.lineSeparator() +
                super.getInfo();
    }
}