package ru.game.rate.main.service.dto;

import ru.game.rate.main.service.domain.Type;

public class SystemRequirementsDto extends BaseDto {
    /**
     * Процессор
     */
    public String cpu;

    /**
     * Оперативная память
     */
    public Integer ram;

    /**
     * Видео карта
     */
    public String videoCard;

    /**
     * Место на жоском диске
     */
    public Double diskSpace;

    /**
     * Тип
     */
    public Type type;
}
