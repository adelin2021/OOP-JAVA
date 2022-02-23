package aquarium.models.aquariums;
//created by J.M.

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.models.decorations.Decoration;
import aquarium.models.fish.Fish;

import java.util.*;
import java.util.stream.Collectors;


public abstract class BaseAquarium implements Aquarium {

    private String name;
    private final int capacity;
    private final Collection<Decoration> decorations;
    private final Collection<Fish> fish;

    public BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }


    @Override
    public int calculateComfort() {
        return this.getDecorations().stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size() >= this.capacity) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.getFish().remove(fish);

    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.getDecorations().add(decoration);
    }

    @Override
    public void feed() {
        this.getFish().forEach(Fish::eat);
    }

    @Override
    public Collection<Fish> getFish() {
        return this.fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return this.decorations;
    }

    @Override
    public String getInfo() {

        return (this.getFish().isEmpty()
                ? "none"
                : String.format("Fish: %s",
                this.getFish().stream().map(Fish::getName).collect(Collectors.joining(" ")))) +
                System.lineSeparator() +
                "Decorations: " + this.getDecorations().size() +
                System.lineSeparator() +
                "Comfort: " +
                this.calculateComfort();
    }
}