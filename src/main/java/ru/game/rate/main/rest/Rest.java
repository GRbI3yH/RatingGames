package ru.game.rate.main.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.game.rate.main.facade.FacadeService;
import ru.game.rate.main.service.dto.GameDto;
import ru.game.rate.main.service.dto.search.GameSearchCriteria;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class Rest {

    @Autowired
    private FacadeService facade;

    @GetMapping("/get/games")
    @ResponseBody
    public List<GameDto> getAll(){
        return facade.findAll();
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void delete(@PathVariable("id") Integer id){
        facade.delete(id);
    }

    @RequestMapping(value = "getById/{id}", method = RequestMethod.POST)
    public GameDto getById(@PathVariable("id")Integer id){
        return facade.getById(id);
    }

    @RequestMapping(value = "/save", method = POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin
    @ResponseBody
    public GameDto save(@RequestBody GameDto game){
        return facade.save(game);
    }

    @RequestMapping(value = "/searchGame", method = POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @CrossOrigin
    @ResponseBody
    public List<GameDto> search(@RequestBody GameSearchCriteria searchCriteria){
        return facade.search(searchCriteria);
    }
}
