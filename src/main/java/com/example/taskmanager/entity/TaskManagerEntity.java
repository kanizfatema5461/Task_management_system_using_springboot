package com.example.taskmanager.entity;



import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity


public class TaskManagerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    public TaskManagerEntity() {
    }

    public TaskManagerEntity(Long id, String title, String description, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
