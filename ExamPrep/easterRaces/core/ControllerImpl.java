package easterRaces.core;
//created by J.M.

import easterRaces.core.interfaces.Controller;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.interfaces.Car;
import easterRaces.entities.interfaces.Driver;
import easterRaces.entities.interfaces.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.interfaces.Repository;

import java.util.HashMap;
import java.util.Map;

import static easterRaces.common.ExceptionMessages.*;
import static easterRaces.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private static String place = "First";

    private Repository<Driver> driverRepository;
    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> driverRepository,
                          Repository<Car> carRepository,
                          Repository<Race> raceRepository) {

        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createDriver(String driverName) {

        Driver newDriver = new DriverImpl(driverName);

        if (driverRepository.getByName(driverName) != null) {
            throw new IllegalArgumentException(String.format(DRIVER_EXISTS, driverName));
        }
        driverRepository.add(newDriver);

        return String.format(DRIVER_CREATED, driverName);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {

        Car car = null;

        if (type.equals("Muscle")) {
            car = new MuscleCar(model, horsePower);
        } else if (type.equals("Sports")) {
            car = new SportsCar(model, horsePower);
        }
        if (carRepository.getByName(model) != null) {
            throw new IllegalArgumentException(String.format(CAR_EXISTS, model));
        } else {
            carRepository.add(car);
        }
        assert car != null;

        return String.format(CAR_CREATED, car.getClass().getSimpleName(), model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {

        if (driverRepository.getByName(driverName) == null) {

            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));
        }
        if (carRepository.getByName(carModel) == null) {

            throw new IllegalArgumentException(String.format(CAR_NOT_FOUND, carModel));
        }

        driverRepository.getByName(driverName).addCar(carRepository.getByName(carModel));

        return String.format(CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {

        if (driverRepository.getByName(driverName) == null) {
            throw new IllegalArgumentException(String.format(DRIVER_NOT_FOUND, driverName));

        } else if (raceRepository.getByName(raceName) == null) {

            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        raceRepository.getByName(raceName).addDriver(driverRepository.getByName(driverName));

        return String.format(DRIVER_ADDED, driverName, raceName);

    }

    @Override
    public String createRace(String name, int laps) {

        Race race = new RaceImpl(name, laps);

        if (raceRepository.getAll().contains(race)) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, name));
        }
        raceRepository.add(race);

        return String.format(RACE_CREATED, name);
    }

    @Override
    public String startRace(String raceName) {

        if (raceRepository.getByName(raceName) == null) {
            throw new IllegalArgumentException(String.format(RACE_NOT_FOUND, raceName));
        }
        Map<String, Double> drivers = new HashMap<>();
        raceRepository.getByName(raceName).getDrivers().forEach(e ->
                drivers.put(e.getName(), e.getCar().
                        calculateRacePoints(raceRepository.getByName(raceName).getLaps())));

        if (drivers.size() < 3) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, 3));
        }

        StringBuilder output = new StringBuilder();

        drivers.entrySet().stream().sorted((first, second) ->
                second.getValue().compareTo(first.getValue())).limit(3).forEach(entry -> {

            switch (place) {
                case "First":
                    driverRepository.getByName(entry.getKey()).winRace();
                    output.append(String.format(DRIVER_FIRST_POSITION, entry.getKey(), raceName));
                    output.append(System.lineSeparator());
                    place = "Second";
                    break;
                case "Second":
                    output.append(String.format(DRIVER_SECOND_POSITION, entry.getKey(), raceName));
                    output.append(System.lineSeparator());
                    place = "Third";
                    break;
                case "Third":
                    output.append(String.format(DRIVER_THIRD_POSITION, entry.getKey(), raceName));
                    place = "First";
                    break;
            }
        });

        return output.toString().trim();
    }
}
