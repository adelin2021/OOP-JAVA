package bakery.repositories.interfaces;
//created by J.M.

public interface DrinkRepository<T> extends Repository<T> {
    T getByNameAndBrand(String drinkName,String drinkBrand);
}
