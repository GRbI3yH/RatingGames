package ru.game.rate.main.service.domain;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "Genre", schema = "public")
public class Genre extends BaseEntity {

    @Column(name = "name",unique = true,nullable = false)
    @Enumerated(STRING)
    private GenreType genre;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game owner;

    public Genre(){

    }

    public Genre(GenreType genre) {
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
