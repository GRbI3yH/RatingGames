package ru.game.rate.main.service;

import ru.game.rate.main.service.dto.GameDto;
import ru.game.rate.main.service.dto.search.GameSearchCriteria;

import java.util.List;

public interface GameService {

    public List<GameDto> findAll();

    public void delete(Integer id);

    public GameDto save(GameDto gameDto);

    public GameDto getById(Integer id);

    public List<GameDto> search(GameSearchCriteria searchCriteria);

}
