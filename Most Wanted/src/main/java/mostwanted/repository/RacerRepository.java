package mostwanted.repository;

import mostwanted.domain.entities.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Integer> {

    Racer findRacerByName(String racerName);

    @Query("select r from mostwanted.domain.entities.Racer as r where size(r.cars) <> 0")
    List<Racer> findRacersByCarsNotNull();
}
