package ru.game.rate.main.service.domain;

import javax.persistence.*;

/**
 * Сущность "Жанр"
 */
@Entity
@Table(name = "Genre")
public class Genre extends BaseEntity {

    /**
     * Имя
     */
    @Column(name = "name",unique = true,nullable = false)
    private GenreType genre;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game owner;

    public Genre(){

    }

    public Genre(Game owner, GenreType genre) {
        this.owner = owner;
        this.genre = genre;
    }

    public GenreType getGenre() {
        return genre;
    }

    public void setGenre(GenreType genre) {
        this.genre = genre;
    }

    public Game getOwner() {
        return owner;
    }

    public void setOwner(Game owner) {
        this.owner = owner;
    }
}
