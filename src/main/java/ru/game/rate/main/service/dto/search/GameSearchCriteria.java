package ru.game.rate.main.service.dto.search;

import ru.game.rate.main.service.domain.GenreType;
import ru.game.rate.main.service.domain.Platform;
import ru.game.rate.main.service.domain.SystemRequirements;

import java.util.Date;
import java.util.Set;

/**
 * Критерии для поиска игр
 */
public class GameSearchCriteria {

    /**
     * Имя игры
     */
    private String name;

    /**
     * Жанры
     */
    private Set<GenreType> genres;

    /**
     * Системные Требования
     */
    private SystemRequirements requirements;

    /**
     * Диапозон цены игры
     */
    private Range<Double> priceRange;

    /**
     * Платформа
     */
    private Platform platform;

    /**
     * Диапозон даты создания
     */
    private Range<Date> dateRange;

    /**
     * Диапозон рейтинга
     */
    private Range<Byte> assessment;

    /**
     * Лицензия
     */
    private String license;

    /**
     * Разработчик
     */
    private String developer;

    /**
     * Издатель
     */
    private String publisher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GenreType> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreType> genres) {
        this.genres = genres;
    }

    public SystemRequirements getRequirements() {
        return requirements;
    }

    public void setRequirements(SystemRequirements requirements) {
        this.requirements = requirements;
    }

    public Range<Double> getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(Range<Double> priceRange) {
        this.priceRange = priceRange;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Range<Date> getDateRange() {
        return dateRange;
    }

    public void setDateRange(Range<Date> dateRange) {
        this.dateRange = dateRange;
    }

    public Range<Byte> getAssessment() {
        return assessment;
    }

    public void setAssessment(Range<Byte> assessment) {
        this.assessment = assessment;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
