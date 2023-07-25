package com.example.trello.repository;

import com.example.trello.models.Comments;
import com.example.trello.models.Folders;
import com.example.trello.models.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAllByTaskEquals(Tasks task);
}
