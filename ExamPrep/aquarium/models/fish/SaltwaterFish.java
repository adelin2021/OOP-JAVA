package aquarium.models.fish;
//created by J.M.

public class SaltwaterFish extends BaseFish {

    private static final int INITIAL_SIZE_CONST = 5;
    private static final int INCREASE_SIZE_CONST = 2;

    public SaltwaterFish(String name, String species, double price) {

        super(name, species, price);
        super.setSize(INITIAL_SIZE_CONST);

    }

    @Override
    public void eat() {

        super.setSize(super.getSize() + INCREASE_SIZE_CONST);
    }
}
