package ru.game.rate.main.service.dtoConverter;


import ru.game.rate.main.service.domain.Genre;
import ru.game.rate.main.service.dto.GenreDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GenreConverter {

    public Genre toEntity(GenreDto dto){
        Genre entity = new Genre();
        entity.setGenre(dto.genre);
        return entity;
    }

    public GenreDto toDto(Genre entity){
        GenreDto dto = new GenreDto();
        dto.genre = entity.getGenre();
        return dto;
    }

    public List<Genre> toListEntity(List<GenreDto> dtos){
        return dtos.stream().map(this::toEntity).collect(toList());
    }

    public List<GenreDto> toListDto(List<Genre> entity){
        return entity.stream().map(this::toDto).collect(toList());
    }

}
