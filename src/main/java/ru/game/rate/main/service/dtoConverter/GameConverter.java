package ru.game.rate.main.service.dtoConverter;

import org.springframework.beans.factory.annotation.Autowired;
import ru.game.rate.main.service.domain.Game;
import ru.game.rate.main.service.domain.SystemRequirements;
import ru.game.rate.main.service.dto.GameDto;

public class GameConverter {

    @Autowired
    private SystemRequirementsConverter systemRequirementsConverter;

    public Game toEntity(GameDto dto){
        Game game = new Game();
        game.setPlatform(dto.platform);
        game.setLicense(dto.license);
        game.setDate(dto.date);
        game.setName(dto.name);
        game.setAssessment(dto.assessment);
        game.setDeveloper(dto.developer);
        game.setGenres(dto.genres);
        game.setPrice(dto.price);
        game.setPublisher(dto.publisher);
        game.setSystemRequirements(systemRequirementsConverter.toListEntity(dto.systemRequirements));
        for (SystemRequirements systemRequirements : game.getSystemRequirements()){
            systemRequirements.setOwner(game);
        }
        return game;
    }

    public GameDto toDto(Game entity){
        GameDto gameDto = new GameDto();
        gameDto.platform = entity.getPlatform();
        gameDto.license = entity.getLicense();
        gameDto.date  = entity.getDate();
        gameDto.name = entity.getName();
        gameDto.assessment = entity.getAssessment();
        gameDto.developer = entity.getDeveloper();
        gameDto.genres = entity.getGenres();
        gameDto.price = entity.getPrice();
        gameDto.publisher = entity.getPublisher();
        gameDto.systemRequirements = systemRequirementsConverter.toListDto(entity.getSystemRequirements());
        return gameDto;
    }
}
