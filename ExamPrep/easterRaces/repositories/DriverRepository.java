package easterRaces.repositories;
//created by J.M.

import easterRaces.entities.interfaces.Driver;
import easterRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DriverRepository<D> implements Repository<Driver> {
    private Map<String, Driver> models;

    public DriverRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Driver getByName(String name) {
        Driver driver = null;
        if (models.containsKey(name)) {
            driver = models.get(name);
        }
        return driver;
    }

    @Override
    public Collection<Driver> getAll() {

        return Collections.unmodifiableCollection(this.models.values());
    }

    @Override
    public void add(Driver model) {
        this.models.putIfAbsent(model.getName(), model);
    }

    @Override
    public boolean remove(Driver model) {
        Driver driver = models.remove(model.getName());
        return driver != null;
    }
}
