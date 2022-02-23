package viceCity.models.players;
//created by J.M.

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

public interface Player {
    String getName();

    int getLifePoints();

    boolean isAlive();

    Repository<Gun> getGunRepository();

    void takeLifePoints(int points);
}
