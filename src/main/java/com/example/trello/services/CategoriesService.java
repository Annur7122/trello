package com.example.trello.services;

import com.example.trello.models.Folders;
import com.example.trello.models.TaskCategories;
import com.example.trello.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    public List<TaskCategories> getCategories(Folders folder){
        return folder.getCategories();
    }

    public TaskCategories getCategory(Long id){
        return categoriesRepository.findById(id).orElse(null);
    }

    public List<TaskCategories> getAllCategories(){
        return categoriesRepository.findAll();
    }
}


