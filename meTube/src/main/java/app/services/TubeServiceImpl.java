package app.services;

import app.domain.entities.Tube;
import app.domain.models.service.TubeServiceModel;
import app.repositories.TubeRepository;
import app.utils.Mapper;

import javax.inject.Inject;
import java.util.List;

public class TubeServiceImpl implements TubeService {
    private final TubeRepository tubeRepository;
    private final Mapper mapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, Mapper mapper) {
        this.tubeRepository = tubeRepository;
        this.mapper = mapper;
    }

    @Override
    public Tube getTubeByName(String tubeName) {
        return this.tubeRepository.findByName(tubeName).orElse(null);
    }

    @Override
    public void saveTube(TubeServiceModel tubeServiceModel) {
        Tube tube = this.mapper.map(tubeServiceModel, Tube.class);
        this.tubeRepository.save(tube);
    }

    @Override
    public void removeTube(TubeServiceModel tubeServiceModel) {
        Tube tube = this.mapper.map(tubeServiceModel, Tube.class);
        this.tubeRepository.delete(tube);
    }

    @Override
    public void updateTube(TubeServiceModel tubeServiceModel) {
        Tube tube = this.mapper.map(tubeServiceModel, Tube.class);
        this.tubeRepository.update(tube);
    }

    @Override
    public List<Tube> getAllTubes() {
        return this.tubeRepository.findAll();
    }

    @Override
    public Tube getTubeById(String id) {
        return this.tubeRepository.findById(id).orElse(null);
    }
}
