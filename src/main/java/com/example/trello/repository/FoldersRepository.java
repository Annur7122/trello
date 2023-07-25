package com.example.trello.repository;

import com.example.trello.models.Folders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoldersRepository extends JpaRepository<Folders, Long> {
}
