package CounterStriker.repositories;
//created by J.M.

import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;

import static CounterStriker.common.ExceptionMessages.INVALID_PLAYER_REPOSITORY;

public class PlayerRepository implements Repository<Player>{

    private Collection<Player> players;

    public PlayerRepository() {
        this.players = new ArrayList<>();
    }

    @Override
    public Collection<Player> getPlayers() {

        return this.players;
    }

    @Override
    public void add(Player model) {
        if(model==null){
            throw new NullPointerException(INVALID_PLAYER_REPOSITORY);
        }
        this.players.add(model);
    }

    @Override
    public boolean remove(Player model) {
        return this.players.remove(model);
    }

    @Override
    public Player findByName(String name) {
        return this.players.stream().filter(e->e.getUsername().equals(name)).findFirst().orElse(null);
    }
}
