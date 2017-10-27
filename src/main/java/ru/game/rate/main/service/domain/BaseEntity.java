package ru.game.rate.main.service.domain;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "id", nullable = false)
    @Id
    private String id;

    public BaseEntity() {
    }

    public BaseEntity(Integer id) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @PrePersist
    public void genId() {
        if (id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
