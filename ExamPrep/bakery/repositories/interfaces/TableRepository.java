package bakery.repositories.interfaces;
//created by J.M.

public interface TableRepository<T> extends Repository <T>{
     T getByNumber(int number);
}
