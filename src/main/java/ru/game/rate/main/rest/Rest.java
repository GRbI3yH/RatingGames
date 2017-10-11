package ru.game.rate.main.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.game.rate.main.facade.FacadeService;

@RestController
public class Rest {

    @Autowired
    private FacadeService facade;
}
