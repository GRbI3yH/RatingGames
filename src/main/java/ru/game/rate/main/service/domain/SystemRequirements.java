package ru.game.rate.main.service.domain;

import javax.persistence.*;

/**
 * Сущность "Системные Требования"
 */
@Entity
@Table(name = "SystemRequirements")
public class SystemRequirements extends BaseEntity {

    /**
     * Процессор
     */
    @Column(name = "cpu")
    private String cpu;

    /**
     * Оперативная память
     */
    @Column(name = "ram")
    private Integer ram;

    /**
     * Видео карта
     */
    @Column(name = "videoCard")
    private String videoCard;

    /**
     * Место на жоском диске
     */
    @Column(name = "diskSpace")
    private Double diskSpace;

    /**
     * Тип
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="game_id")
    private Game owner;

    public SystemRequirements(){

    }

    public SystemRequirements(Game owner, String cpu, Integer ram, String videoCard, Double diskSpace) {
        this.owner = owner;
        this.cpu = cpu;
        this.ram = ram;
        this.videoCard = videoCard;
        this.diskSpace = diskSpace;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public String getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(String videoCard) {
        this.videoCard = videoCard;
    }

    public Double getDiskSpace() {
        return diskSpace;
    }

    public void setDiskSpace(Double diskSpace) {
        this.diskSpace = diskSpace;
    }

    public Game getOwner() {
        return owner;
    }

    public void setOwner(Game owner) {
        this.owner = owner;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return cpu+" "+ram+" "+videoCard+" "+diskSpace+" "+type;
    }
}
