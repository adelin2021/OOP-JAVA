package CounterStriker.models.players;
//created by J.M.

import CounterStriker.models.guns.Gun;

public interface Player {
    String getUsername();

    int getHealth();

    int getArmor();

    Gun getGun();

    boolean isAlive();

    void takeDamage(int points);
}
