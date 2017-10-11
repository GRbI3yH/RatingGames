package ru.game.rate.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.game.rate.main.service.domain.BaseEntity;
import ru.game.rate.main.service.domain.Game;
import ru.game.rate.main.service.repository.RepositoryGame;

import java.util.List;

@Component
public class GameServiceImpl implements GameService{

    @Autowired
    private RepositoryGame repositoryGame;

    /**
     * возвращает все игры
     * @return
     */
    @Override
    public List<Game> findAll(){
        return repositoryGame.findAll();
    }

    /**
     *  Удаляет игру
     * @param id - Ид игры
     */
    @Override
    public void delete(Integer id){
        repositoryGame.delete(id);
    }

    /**
     *  Добавляет  игру
     * @param game - Что добовляем
     */
    @Override
    public void save(Game game){
        repositoryGame.save(game);
    }

    /**
     *  Получаем игру по ИД
     * @param id - Ид игры
     */
    @Override
    public Game getById(Integer id) {
        return repositoryGame.findOne(id);
    }

}
