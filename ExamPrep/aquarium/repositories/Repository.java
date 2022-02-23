package aquarium.repositories;
//created by J.M.

import aquarium.models.decorations.Decoration;

public interface Repository  {

    void add(Decoration decoration);

    boolean remove(Decoration decoration);

    Decoration findByType(String type);
}
