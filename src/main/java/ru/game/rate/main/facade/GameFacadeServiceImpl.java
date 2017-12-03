package ru.game.rate.main.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.game.rate.main.service.GameService;
import ru.game.rate.main.service.dto.GameDto;
import ru.game.rate.main.service.dto.search.GameSearchCriteria;

import java.util.List;

@Service
public class GameFacadeServiceImpl implements FacadeService {

    @Autowired
    private GameService service;

    /**
     * Возвращает все игры
     * @return
     */
    @Override
    public List<GameDto> findAll() {
        return service.findAll();
    }

    /**
     * Удаляет по id
     * @param id
     */
    @Override
    public void delete(String id) {
        service.delete(id);
    }

    /**
     * Обновляет или сохроняет игру
     * @param game
     */
    @Override
    public GameDto save(GameDto game) {
        return service.save(game);
    }

    /**
     * Отдает игру по id
     * @param id
     * @return
     */
    @Override
    public GameDto getById(String id) {
        return service.getById(id);
    }

    /**
     * Поиск по критерию
     * @param searchCriteria
     * @return
     */
    @Override
    public List<GameDto> search(GameSearchCriteria searchCriteria) {
        return service.search(searchCriteria);
    }
}
