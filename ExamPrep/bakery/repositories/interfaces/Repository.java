package bakery.repositories.interfaces;
//created by J.M.

import java.util.Collection;

public interface Repository<T> {

    Collection<T> getAll();

    void add(T t);

}
