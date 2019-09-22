package com.mkolongo.gamestore.services.base;

import com.mkolongo.gamestore.domain.models.Game;
import com.mkolongo.gamestore.dtos.GameAddDto;
import com.mkolongo.gamestore.dtos.GameDeleteDto;
import com.mkolongo.gamestore.dtos.GameEditDto;
import com.mkolongo.gamestore.dtos.views.AllGamesDto;
import com.mkolongo.gamestore.dtos.views.DetailsGameDto;

import java.util.List;

public interface GameService {

    String addGame(GameAddDto gameAddDto);

    String editGame(GameEditDto gameEditDto);

    String deleteGame(GameDeleteDto gameDeleteDto);

    List<AllGamesDto> getAllGames();

    DetailsGameDto getGameDetailsByName(String name);

}
