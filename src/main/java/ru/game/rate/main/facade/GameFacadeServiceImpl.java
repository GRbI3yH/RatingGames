package ru.game.rate.main.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.game.rate.main.service.GameService;

@Service
public class GameFacadeServiceImpl implements FacadeService {
    @Autowired
    private GameService service;

}
