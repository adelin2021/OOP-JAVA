package easterRaces.repositories;
//created by J.M.

import easterRaces.entities.interfaces.Car;
import easterRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CarRepository<C> implements Repository<Car> {
    private Map<String, Car> models;

    public CarRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Car getByName(String name) {
        Car car = null;
        if (this.models.containsKey(name)) {
            car = this.models.get(name);
        }
        return car;
    }

    @Override
    public Collection<Car> getAll() {

        return Collections.unmodifiableCollection(this.models.values());
    }

    @Override
    public void add(Car model) {
        this.models.putIfAbsent(model.getModel(), model);
    }

    @Override
    public boolean remove(Car model) {
        Car car = models.remove(model.getModel());
        return car != null;
    }
}
