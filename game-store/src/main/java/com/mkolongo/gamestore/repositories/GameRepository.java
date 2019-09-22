package com.mkolongo.gamestore.repositories;

import com.mkolongo.gamestore.domain.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game findGameByTitle(String title);

}
