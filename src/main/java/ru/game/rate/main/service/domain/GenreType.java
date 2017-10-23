package ru.game.rate.main.service.domain;

import java.io.Serializable;

public enum GenreType implements Serializable{

    ACTION("ACTION"),//(ЭКШЕН)
    ARCADE("ARCADE"),//(АРКАДА)
    STRATEGY("STRATEGY"),//(СТРАТЕГИЯ)
    ADVENTURE("ADVENTURE"), //(АДВЕНЧУРА, ПРИКЛЮЧЕНЧЕСКАЯ ИГРА)
    QUEST("QUEST"),//(КВЕСТ)
    RPG("RPG"),//— ROLE PLAYING GAME (РПГ, РОЛЕВАЯ ИГРА)
    FIGHTING("FIGHTING"),//(ФАЙТИНГ)
    RACING("RACING"),//(ГОНКА)
    SIMULATION("SIMULATION"),//(СИМУЛЯТОР)
    SPORTS("SPORTS"),//(СПОРТИВНАЯ ИГРА)
    PUZZLELOGIC("PUZZLELOGIC");//(ПАЗЗЛ, ГОЛОВОЛОМКА)

    private final String type;

    GenreType(String type) {
        this.type = type;
    }
}
