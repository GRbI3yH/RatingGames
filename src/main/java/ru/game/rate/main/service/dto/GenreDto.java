package ru.game.rate.main.service.dto;

import ru.game.rate.main.service.domain.GenreType;

import javax.persistence.Enumerated;

import static javax.persistence.EnumType.STRING;

public class GenreDto {

    @Enumerated(STRING)
    public GenreType genre;

    public GenreDto(){}

    public GenreDto(GenreType genre){
        this.genre = genre;
    }
}
