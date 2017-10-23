package ru.game.rate.main.service.domain;

public enum Type {
    MINIMUM("MINIMUM"),RECOMMENDED("RECOMMENDED");

    private final String type;

    Type(String type) {
        this.type = type;
    }
}
