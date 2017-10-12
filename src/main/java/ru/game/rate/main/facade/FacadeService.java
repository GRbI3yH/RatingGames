package ru.game.rate.main.facade;

import ru.game.rate.main.service.domain.Game;
import ru.game.rate.main.service.dto.search.GameSearchCriteria;

import java.util.List;

public interface FacadeService {

    public List<Game> findAll();

    public void delete(Integer id);

    public void save(Game game);

    public Game getById(Integer id);

    public List<Game> search(GameSearchCriteria searchCriteria);

}
