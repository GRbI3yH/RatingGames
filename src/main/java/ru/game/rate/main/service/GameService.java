package ru.game.rate.main.service;

import ru.game.rate.main.service.dto.GameDto;
import ru.game.rate.main.service.dto.search.GameSearchCriteria;

import java.util.List;

public interface GameService {

    List<GameDto> findAll();

    void delete(String id);

    GameDto save(GameDto gameDto);

    GameDto getById(String id);

    List<GameDto> search(GameSearchCriteria searchCriteria);

}
