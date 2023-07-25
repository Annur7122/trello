package com.example.trello.services;

import com.example.trello.models.Folders;
import com.example.trello.models.Tasks;
import com.example.trello.repository.FoldersRepository;
import com.example.trello.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final FolderService folderService;


    public void saveOrUpdateTask(Tasks task){
        taskRepository.save(task);
    }

    public Tasks getTask(Long id){
        return taskRepository.findById(id).orElse(null);
    }
    public List<Tasks> getTasksReferToFolder(Folders folder){
        return taskRepository.findAllByFolderEquals(folder);
    }

    public void changeStatus(Long taskId, int statusNumber){
        Tasks task = getTask(taskId);
        task.setTasksStatus(statusNumber);
        saveOrUpdateTask(task);
    }

}
