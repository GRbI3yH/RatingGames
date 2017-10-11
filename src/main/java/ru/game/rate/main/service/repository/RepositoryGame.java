package ru.game.rate.main.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.game.rate.main.service.domain.Game;

public interface RepositoryGame extends JpaRepository<Game, Integer> {

}
