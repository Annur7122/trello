package com.example.trello.repository;

import com.example.trello.models.TaskCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<TaskCategories, Long> {
}
