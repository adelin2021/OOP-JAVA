package easterRaces.entities.interfaces;
//created by J.M.

public interface Car {
    String getModel();

    int getHorsePower();

    double getCubicCentimeters();

    double calculateRacePoints(int laps);
}
