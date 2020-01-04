package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;

public class MissionImpl implements Mission {

    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {

        for (Astronaut astronaut : astronauts) {
            if (!astronaut.canBreath()) continue;

            for (String item : planet.getItems()) {
                planet.getItems().remove(item);
                astronaut.getBag().getItems().add(item);

                astronaut.breath();
                if (astronaut.getOxygen() == 0) break;
            }
        }
    }
}
