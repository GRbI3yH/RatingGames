package ru.game.rate.main.service.dto.search;

import ru.game.rate.main.service.domain.GenreType;
import ru.game.rate.main.service.domain.Platform;
import ru.game.rate.main.service.domain.SystemRequirements;

import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

import static javax.persistence.EnumType.STRING;

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
    private List<GenreType> genres;

    /**
     * Системные Требования
     */
    private SystemRequirements requirements;

    /**
     * Диапозон цены игры
     */
    private Double startPrice;
    private Double endPrice;

    /**
     * Платформа
     */
    @Enumerated(STRING)
    private Platform platform;

    /**
     * Диапозон даты создания
     */
    private Date startDate;
    private Date endDate;


    /**
     * Диапозон рейтинга
     */
    private Integer startAssessment;
    private Integer endAssessment;

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

    public List<GenreType> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreType> genres) {
        this.genres = genres;
    }

    public SystemRequirements getRequirements() {
        return requirements;
    }

    public void setRequirements(SystemRequirements requirements) {
        this.requirements = requirements;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
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

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Double endPrice) {
        this.endPrice = endPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStartAssessment() {
        return startAssessment;
    }

    public void setStartAssessment(Integer startAssessment) {
        this.startAssessment = startAssessment;
    }

    public Integer getEndAssessment() {
        return endAssessment;
    }

    public void setEndAssessment(Integer endAssessment) {
        this.endAssessment = endAssessment;
    }
}
