package app.services;

import app.domain.entities.Tube;
import app.domain.models.service.TubeServiceModel;

import java.util.List;

public interface TubeService {

    Tube getTubeByName(String tubeName);

    void saveTube(TubeServiceModel tubeServiceModel);

    void removeTube(TubeServiceModel tubeServiceModel);

    void updateTube(TubeServiceModel tubeServiceModel);

    List<Tube> getAllTubes();

    Tube getTubeById(String id);
}
