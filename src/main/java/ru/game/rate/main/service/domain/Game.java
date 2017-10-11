package ru.game.rate.main.service.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Сущность "Игра"
 */
@Entity
@Table(name = "game")
public class Game extends BaseEntity {

    /**
     * Имя игры
     */
    @Column(name = "name")
    private String name;

    /**
     * Жанры
     */
    @OneToMany(mappedBy = "owner", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Genre> genres;

    /**
     * Дата создания
     */
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    /**
     * Разработчик
     */
    @Column(name = "developer")
    private String developer;

    /**
     * Издатель
     */
    @Column(name = "publisher")
    private String publisher;

    /**
     * Системные Требования
     */
    @OneToMany(mappedBy = "owner", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<SystemRequirements> systemRequirements;

    /**
     * Платформа
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "platform")
    private Platform platform;

    /**
     * оценка
     */
    @Column(name = "assessment")
    private Integer assessment;

    /**
     * Цена
     */
    @Column(name = "standing")
    private Double standing;

    /**
     * Лицензия
     */
    @Column(name = "license")
    private String license;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public List<SystemRequirements> getSystemRequirements() {
        return systemRequirements;
    }

    public void setSystemRequirements(List<SystemRequirements> systemRequirements) {
        this.systemRequirements = systemRequirements;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Integer getAssessment() {
        return assessment;
    }

    public void setAssessment(Integer assessment) {
        this.assessment = assessment;
    }

    public Double getStanding() {
        return standing;
    }

    public void setStanding(Double standing) {
        this.standing = standing;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
