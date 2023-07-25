package com.example.trello.services;

import com.example.trello.models.Folders;
import com.example.trello.models.TaskCategories;
import com.example.trello.repository.CategoriesRepository;
import com.example.trello.repository.FoldersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {
    private final FoldersRepository foldersRepository;
    private final CategoriesService categoriesService;

    public Folders getFolder(Long id){
        return foldersRepository.findById(id).orElse(null);
    }

    public List<Folders> getAllFolders(){
        return foldersRepository.findAll();
    }

    public void saveOrUpdateFolder(Folders folder){
        foldersRepository.save(folder);
    }

    public void addCategory(Long folderId, Long categoryId){
        Folders folder = getFolder(folderId);
        TaskCategories taskCategory = categoriesService.getCategory(categoryId);

        if(folder.getCategories() != null && folder.getCategories().size() > 0){
            if(!folder.getCategories().contains(taskCategory))folder.getCategories().add(taskCategory);
        }else{
            List<TaskCategories> taskCategoriesList = new ArrayList<>();
            taskCategoriesList.add(taskCategory);
            folder.setCategories(taskCategoriesList);
        }

        foldersRepository.save(folder);
    }


    public void removeCategory(Long folderId, Long categoryId){
        Folders folder = getFolder(folderId);
        TaskCategories taskCategory = categoriesService.getCategory(categoryId);

        System.out.println();
        System.out.println();
        System.out.println(folder.getFoldersName() + " "  + taskCategory.getCategoryName());

        if(folder.getCategories() != null && folder.getCategories().size() > 0){
            folder.getCategories().remove(taskCategory);
        }
        foldersRepository.save(folder);
    }



}
