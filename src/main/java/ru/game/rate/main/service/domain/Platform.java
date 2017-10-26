package ru.game.rate.main.service.domain;

public enum Platform {
    WINDOWS("WINDOWS"),
    LINUX("LINUX"),
    MACOS("MACOS"),
    ANDROID("ANDROID");

    private final String type;

    Platform(String type) {
        this.type = type;
    }
}
