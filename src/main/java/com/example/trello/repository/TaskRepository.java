package com.example.trello.repository;

import com.example.trello.models.Folders;
import com.example.trello.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findAllByFolderEquals(Folders folder);
}
