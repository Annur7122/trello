package com.example.trello.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="folders")
@Getter
@Setter
public class Folders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="foldersId")
    private Long id;

    @Column(name="foldersName")
    private String foldersName;

    @ManyToMany
    private List<TaskCategories> categories;
}
