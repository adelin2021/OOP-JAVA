package CounterStriker.repositories;
//created by J.M.

import CounterStriker.models.guns.Gun;

import java.util.ArrayList;
import java.util.Collection;

import static CounterStriker.common.ExceptionMessages.INVALID_GUN_REPOSITORY;

public class GunRepository implements Repository<Gun>{

    private Collection<Gun> guns;

   public GunRepository() {

        this.guns = new ArrayList<>();
    }

    @Override
    public Collection<Gun> getPlayers() {
        return this.guns;
    }

    @Override
    public void add(Gun model) {
        if(model==null){
            throw new NullPointerException(INVALID_GUN_REPOSITORY);
        }
        this.guns.add(model);
    }

    @Override
    public boolean remove(Gun model) {
        return this.guns.remove(model);
    }

    @Override
    public Gun findByName(String name) {
        return this.guns.stream().filter(e->e.getName().equals(name)).findFirst().orElse(null);
    }
}
