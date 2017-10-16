package ru.game.rate.main.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.game.rate.main.facade.FacadeService;
import ru.game.rate.main.service.domain.Game;
import ru.game.rate.main.service.dto.search.GameSearchCriteria;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class Rest {

    @Autowired
    private FacadeService facade;

    @GetMapping("/get/games")
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

    @RequestMapping(value = "/save", method = POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void save(@RequestBody Game game){
        facade.save(game);
    }

    @PostMapping("/searchGame")
    public void save(GameSearchCriteria searchCriteria){
        facade.search(searchCriteria);
    }
}
