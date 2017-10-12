package ru.game.rate.main.service.dto.search;

/**
 * Диапозон значений
 * @param <T>
 */
public abstract class Range<T> {

    /**
     * Начальное значение
     */
    private T start;

    /**
     * Конечное
     */
    private T end;

    public T getStartRange(){
       return start;
    }

    public T setStartRange(){
        return start;
    }

    public T getEndRange(){
        return end;
    }

    public T setEndRange(){
        return end;
    }
}
