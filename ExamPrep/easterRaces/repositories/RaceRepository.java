package easterRaces.repositories;
//created by J.M.

import easterRaces.entities.interfaces.Race;
import easterRaces.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class RaceRepository<T> implements Repository<Race> {
    private Map<String, Race> models;

    public RaceRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Race getByName(String name) {
        Race race = null;
        if (models.containsKey(name)) {
            race = models.get(name);
        }
        return race;
    }

    @Override
    public Collection<Race> getAll() {

        return Collections.unmodifiableCollection(this.models.values());
    }

    @Override
    public void add(Race model) {
        this.models.putIfAbsent(model.getName(), model);
    }

    @Override
    public boolean remove(Race model) {
        Race remove = models.remove(model.getName());
        return remove != null;
    }
}
