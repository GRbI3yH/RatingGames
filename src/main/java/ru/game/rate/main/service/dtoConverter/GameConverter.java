package ru.game.rate.main.service.dtoConverter;

import org.springframework.beans.factory.annotation.Autowired;
import ru.game.rate.main.service.domain.Game;
import ru.game.rate.main.service.domain.Genre;
import ru.game.rate.main.service.domain.SystemRequirements;
import ru.game.rate.main.service.dto.GameDto;

public class GameConverter {

    @Autowired
    private SystemRequirementsConverter systemRequirementsConverter;

    @Autowired
    private GenreConverter genreConverter;

    public Game toEntity(GameDto dto){
        Game game = new Game();
        game.setPlatform(dto.platform);
        game.setLicense(dto.license);
        game.setDate(dto.date);
        game.setName(dto.name);
        game.setAssessment(dto.assessment);
        game.setDeveloper(dto.developer);
        game.setPrice(dto.price);
        game.setPublisher(dto.publisher);
        game.setGenres(genreConverter.toListEntity(dto.genres));
        for (Genre genre : game.getGenres()){
            genre.setOwner(game);
        }
        game.setSystemRequirements(systemRequirementsConverter.toListEntity(dto.systemRequirements));
        for (SystemRequirements systemRequirements : game.getSystemRequirements()){
            systemRequirements.setOwner(game);
        }
        return game;
    }

    public GameDto toDto(Game entity){
        GameDto gameDto = new GameDto();
        gameDto.id = entity.getId();
        gameDto.platform = entity.getPlatform();
        gameDto.license = entity.getLicense();
        gameDto.date  = entity.getDate();
        gameDto.name = entity.getName();
        gameDto.assessment = entity.getAssessment();
        gameDto.developer = entity.getDeveloper();
        gameDto.genres = genreConverter.toListDto(entity.getGenres());
        gameDto.price = entity.getPrice();
        gameDto.publisher = entity.getPublisher();
        gameDto.systemRequirements = systemRequirementsConverter.toListDto(entity.getSystemRequirements());
        return gameDto;
    }
}
