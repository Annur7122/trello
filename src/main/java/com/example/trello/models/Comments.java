package com.example.trello.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.config.Task;

import java.util.List;

@Entity
@Table(name="comments")
@Getter
@Setter

public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comments_id")
    private Long id;

    @Column(name="comments_name")
    private String commentsName;

    @ManyToOne
    private Tasks task;
}
