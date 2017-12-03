package ru.game.rate.main.facade;

import ru.game.rate.main.service.dto.GameDto;
import ru.game.rate.main.service.dto.search.GameSearchCriteria;

import java.util.List;

public interface FacadeService {

    List<GameDto> findAll();

    void delete(String id);

    GameDto save(GameDto game);

    GameDto getById(String id);

    List<GameDto> search(GameSearchCriteria searchCriteria);

}
