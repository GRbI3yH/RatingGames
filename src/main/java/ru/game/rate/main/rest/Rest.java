package ru.game.rate.main.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.game.rate.main.facade.FacadeService;
import ru.game.rate.main.service.domain.Game;
import ru.game.rate.main.service.dto.search.GameSearchCriteria;

import java.util.List;

@RestController
public class Rest {

    @Autowired
    private FacadeService facade;

    @GetMapping("/getGame")
    public List<Game> getAll(){
        return facade.findAll();
    }

    @PostMapping("/delete")
    public void delete(Integer id){
        facade.delete(id);
    }

    @PostMapping("/getById")
    public void getById(Integer id){
        facade.getById(id);
    }

    @PostMapping("/saveGame")
    public void save(Game game){
        facade.save(game);
    }

    @PostMapping("/searchGame")
    public void save(GameSearchCriteria searchCriteria){
        facade.search(searchCriteria);
    }
}
