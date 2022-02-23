package aquarium.core;
//created by J.M.

public interface Controller {

    String addAquarium(String aquariumType, String aquariumName);

    String addDecoration(String type);

    String insertDecoration(String aquariumName, String decorationType);

    String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) throws NoSuchFieldException, IllegalAccessException;

    String feedFish(String aquariumName);

    String calculateValue(String aquariumName);

    String report();
}
