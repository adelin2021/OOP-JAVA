package viceCity.models.neighbourhood;
//created by J.M.

import viceCity.models.players.Player;

import java.util.Collection;

public interface Neighbourhood {

    void action(Player mainPlayer, Collection<Player> civilPlayers);

}
