package spaceStation.repositories;

import spaceStation.models.astronauts.Astronaut;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

public class AstronautRepository implements Repository<Astronaut> {
    private Collection<Astronaut> astronauts;

    public AstronautRepository() {
        this.astronauts = new LinkedHashSet<>();
    }

    @Override
    public Collection<Astronaut> getModels() {
        return Collections.unmodifiableCollection(this.astronauts);
    }

    @Override
    public void add(Astronaut model) {
        this.astronauts.add(model);
    }

    @Override
    public boolean remove(Astronaut model) {
        return this.astronauts.removeIf(astronaut -> astronaut.equals(model));
    }

    @Override
    public Astronaut findByName(String name) {
        return this.astronauts.stream().filter(astronaut -> astronaut.getName().equals(name)).findFirst().orElse(null);
    }
}
