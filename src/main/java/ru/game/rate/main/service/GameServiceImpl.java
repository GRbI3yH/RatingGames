package ru.game.rate.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.game.rate.main.service.dto.GameDto;
import ru.game.rate.main.service.dto.search.GameSearchCriteria;
import ru.game.rate.main.service.dtoConverter.DtoConverter;
import ru.game.rate.main.service.repository.RepositoryGame;

import java.util.List;

@Component
public class GameServiceImpl implements GameService{

    @Autowired
    private RepositoryGame repositoryGame;

    @Autowired
    private DtoConverter dtoConverter;

    /**
     * возвращает все игры
     * @return
     */
    @Override
    public List<GameDto> findAll(){

        return dtoConverter.toListDto(repositoryGame.findAll());
    }

    /**
     *  Удаляет игру
     * @param id - Ид игры
     */
    @Override
    public void delete(String id){
        repositoryGame.delete(id);
    }

    /**
     *  Добавляет  игру
     * @param gameDto - Что добовляем
     */
    @Override
    public GameDto save(GameDto gameDto){
        return dtoConverter.toDto(repositoryGame.save(dtoConverter.toEntity(gameDto)));
    }

    /**
     *  Получаем игру по ИД
     * @param id - Ид игры
     */
    @Override
    public GameDto getById(String id) {
        return dtoConverter.toDto(repositoryGame.get(id));
    }


    @Override
    public List<GameDto> search(GameSearchCriteria searchCriteria) {
        return dtoConverter.toListDto(repositoryGame.findByCriteria(searchCriteria));
    }

}
