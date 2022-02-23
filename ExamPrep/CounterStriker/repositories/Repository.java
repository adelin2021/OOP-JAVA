package CounterStriker.repositories;
//created by J.M.

import java.util.Collection;

public interface Repository<T> {
    Collection<T> getPlayers();

    void add(T model);

    boolean remove(T model);

    T findByName(String name);
}
