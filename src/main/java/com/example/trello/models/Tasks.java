package com.example.trello.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="tasks")
@Getter
@Setter

public class Tasks{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tasks_id")
    private Long id;

    @Column(name="tasks_title")
    private String tasksTitle;

    @Column(name="tasks_description")
    private String tasksDescription;

    @Column(name="tasks_status")
    private int tasksStatus;

    @ManyToOne
    private Folders folder;

}
