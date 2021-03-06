package ru.game.rate.main.service.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.EnumType.STRING;

/**
 * Сущность "Игра"
 */
@Entity
@Table(name = "game", schema = "public")
@NamedQueries({
        @NamedQuery(name = "Game.findAll", query = "SELECT g FROM Game g")
})

public class Game extends BaseEntity {

    /**
     * Имя игры
     */
    @Column(name = "name")
    private String name;

    /**
     * Жанры
     */
//    @ElementCollection
//    @CollectionTable(name = "genres", joinColumns = @JoinColumn(name = "game_id"))
    //@Column(name = "genre")
    @OneToMany(mappedBy = "owner", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Genre> genres;

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
    @Column(name = "platform")
    @Enumerated(STRING)
    private Platform platform;

    /**
     * оценка
     */
    @Column(name = "assessment")
    private Integer assessment;

    /**
     * Цена
     */
    @Column(name = "price")
    private Double price;

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

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
