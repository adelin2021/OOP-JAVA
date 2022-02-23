package bakery.repositories.interfaces;
//created by J.M.

public interface FoodRepository<T> extends Repository<T> {
    T getByName(String name);
}
