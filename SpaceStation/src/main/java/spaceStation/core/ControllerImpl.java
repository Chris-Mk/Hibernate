package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private Mission mission;
    private int exploredPlanetsCount;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.mission = new MissionImpl();
        this.exploredPlanetsCount = 0;
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        if (Biologist.class.getSimpleName().equals(type)) {
            this.astronautRepository.add(new Biologist(astronautName));
        } else if (Geodesist.class.getSimpleName().equals(type)) {
            this.astronautRepository.add(new Geodesist(astronautName));
        } else if (Meteorologist.class.getSimpleName().equals(type)) {
            this.astronautRepository.add(new Meteorologist(astronautName));
        } else {
            throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }
        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        planet.getItems().addAll(Arrays.asList(items));
        this.planetRepository.add(planet);
        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        if (this.astronautRepository.findByName(astronautName) == null) {
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        this.astronautRepository.remove(this.astronautRepository.findByName(astronautName));
        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        List<Astronaut> astronauts = this.astronautRepository.getModels()
                .stream()
                .filter(astronaut -> astronaut.getOxygen() > 60)
                .collect(Collectors.toList());

        if (astronauts.isEmpty()) throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);

        Planet planet = this.planetRepository.findByName(planetName);
        this.mission.explore(planet, astronauts);
        this.exploredPlanetsCount++;

        List<Astronaut> deadAstronauts = astronauts.stream()
                .filter(astronaut -> astronaut.getOxygen() == 0)
                .collect(Collectors.toList());

        return String.format(PLANET_EXPLORED, planetName, deadAstronauts.size());
    }

    @Override
    public String report() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(REPORT_PLANET_EXPLORED, this.exploredPlanetsCount))
                .append(System.lineSeparator())
                .append("Astronauts info:")
                .append(System.lineSeparator());

        this.astronautRepository.getModels()
                .forEach(astronaut -> builder.append(astronaut.toString())
                        .append(System.lineSeparator()));
        return builder.toString().trim();
    }
}
