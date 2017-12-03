package ru.game.rate.main.service.repository;

import ru.game.rate.main.service.domain.Game;
import ru.game.rate.main.service.dto.search.GameSearchCriteria;

import java.util.List;

public interface RepositoryGame {

    Game save(Game game);

    void delete(String id);

    Game get(String id);

    List<Game> findAll();

    List<Game> findByCriteria(GameSearchCriteria searchCriteria);
}
