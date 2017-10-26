package ru.game.rate.main.service.dto;

import ru.game.rate.main.service.domain.Platform;

import java.util.Date;
import java.util.List;

public class GameDto extends BaseDto {
    /**
     * Имя игры
     */
    public String name;

    /**
     * Жанры
     */
    public List<GenreDto> genres;

    /**
     * Дата создания
     */
    public Date date;

    /**
     * Разработчик
     */
    public String developer;

    /**
     * Издатель
     */
    public String publisher;

    /**
     * Системные Требования
     */
    public List<SystemRequirementsDto> systemRequirements;

    /**
     * Платформа
     */
    public Platform platform;

    /**
     * оценка
     */
    public Integer assessment;

    /**
     * Цена
     */
    public Double price;

    /**
     * Лицензия
     */
    public String license;
}
