package viceCity.core.interfaces;
//created by J.M.

public interface Controller {

    String addPlayer(String name);

    String addGun(String type, String name);

    String addGunToPlayer(String name);

    String fight();
}
