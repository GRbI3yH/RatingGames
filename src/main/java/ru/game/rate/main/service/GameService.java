package ru.game.rate.main.service;

import ru.game.rate.main.service.domain.Game;

import java.util.List;

public interface GameService {

    public List<Game> findAll();

    public void delete(Integer id);

    public void save(Game game);

    public Game getById(Integer id);

}
