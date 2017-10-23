package ru.game.rate.main.service.dtoConverter;

import org.springframework.beans.factory.annotation.Autowired;
import ru.game.rate.main.service.domain.Game;
import ru.game.rate.main.service.dto.GameDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class DtoConverter {

    @Autowired
    private GameConverter gameConverter;

    public Game toEntity(GameDto dto){
        return gameConverter.toEntity(dto);
    }

    public GameDto toDto(Game entity){
        return gameConverter.toDto(entity);
    }

    public List<Game> toListEntity(List<GameDto> dtos){
        return dtos.stream().map(this::toEntity).collect(toList());
    }

    public List<GameDto> toListDto(List<Game> entity){
        return entity.stream().map(this::toDto).collect(toList());
    }

}
