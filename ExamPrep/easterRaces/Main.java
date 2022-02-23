package easterRaces;
//created by J.M.

import easterRaces.core.ControllerImpl;
import easterRaces.core.EngineImpl;
import easterRaces.core.interfaces.Controller;
import easterRaces.entities.interfaces.Car;
import easterRaces.entities.interfaces.Driver;
import easterRaces.entities.interfaces.Race;
import easterRaces.io.ConsoleReader;
import easterRaces.io.ConsoleWriter;
import easterRaces.repositories.CarRepository;
import easterRaces.repositories.DriverRepository;
import easterRaces.repositories.RaceRepository;
import easterRaces.repositories.interfaces.Repository;

public class Main {
    public static void main(String[] args) {
        Repository<Car> motorcycleRepository = new CarRepository<>();
        Repository<Race> raceRepository = new RaceRepository<>();
        Repository<Driver> riderRepository = new DriverRepository<>();

        Controller controller = new ControllerImpl(riderRepository, motorcycleRepository, raceRepository);

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();
    }
}
