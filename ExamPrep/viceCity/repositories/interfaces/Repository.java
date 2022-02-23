package viceCity.repositories.interfaces;
//created by J.M.

import java.util.Collection;

public interface Repository<T> {

    Collection<T> getModels();

    void add(T model);

    boolean remove(T model);

    T find(String name);
}
