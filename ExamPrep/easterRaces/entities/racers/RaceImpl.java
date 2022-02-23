package easterRaces.entities.racers;
//created by J.M.

import easterRaces.entities.interfaces.Driver;
import easterRaces.entities.interfaces.Race;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//created by J.M.

import static easterRaces.common.ExceptionMessages.*;

public class RaceImpl implements Race {

    private String name;
    private int laps;
    private List<Driver> drivers;

    public RaceImpl(String name, int laps) {
        setName(name);
        setLaps(laps);
        this.drivers = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.isEmpty() || name.length() < 5){
            throw new IllegalArgumentException(String.format(INVALID_NAME, name,5));
        }
        this.name = name;
    }

    public void setLaps(int laps) {
        if (laps < 1){
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_LAPS, 1));
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return this.drivers;
    }

    @Override
    public void addDriver(Driver driver) {

        if (driver == null){
            throw new IllegalArgumentException(DRIVER_INVALID);
        }
        if(driver.getCar() == null){
            throw new IllegalArgumentException(String.format(DRIVER_NOT_PARTICIPATE, driver.getName()));
        }
        if (drivers.contains(driver)){
            throw new IllegalArgumentException(String.format(DRIVER_ALREADY_ADDED, driver.getName(), this.name));
        }
        drivers.add(driver);

    }
}
