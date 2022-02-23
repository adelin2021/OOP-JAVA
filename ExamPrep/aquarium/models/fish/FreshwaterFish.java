package aquarium.models.fish;
//created by J.M.

public class FreshwaterFish extends BaseFish {

    private static final int INITIAL_SIZE_CONST = 3;
    private static final int INCREASE_SIZE_CONST = 3;

    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(INITIAL_SIZE_CONST);
    }

    @Override
    public void eat() {

        super.setSize(super.getSize() + INCREASE_SIZE_CONST);
    }
}
