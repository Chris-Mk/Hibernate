package app.repositories;

import app.domain.entities.Tube;

import java.util.Optional;

public interface TubeRepository extends BaseRepository<Tube, String> {

    Optional<Tube> findByName(String tubeName);
}
