package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;
import java.util.Iterator;

public class MissionImpl implements Mission {

    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {

        for (Astronaut astronaut : astronauts) {
            if (!astronaut.canBreath()) continue;

            Iterator<String> planetItems = planet.getItems().iterator();

            while (planetItems.hasNext()) {
                String item = planetItems.next();

                planetItems.remove();
                astronaut.getBag().getItems().add(item);

                astronaut.breath();
                if (astronaut.getOxygen() == 0) break;
            }
        }
    }
}
