package com.example.trello.controllers;

import com.example.trello.models.Comments;
import com.example.trello.models.Folders;
import com.example.trello.models.TaskCategories;
import com.example.trello.models.Tasks;
import com.example.trello.repository.CategoriesRepository;
import com.example.trello.repository.FoldersRepository;
import com.example.trello.services.CategoriesService;
import com.example.trello.services.CommentsService;
import com.example.trello.services.FolderService;
import com.example.trello.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.tokens.CommentToken;

import java.util.ArrayList;
import java.util.List;



@Controller
@RequiredArgsConstructor
public class TrelloController {

    private final FolderService folderService;
    private final CategoriesService categoriesService;
    private final TaskService taskService;
    private final CommentsService commentsService;


    @GetMapping(value="/")
    public String homePage(Model model){
        List<Folders> foldersList = folderService.getAllFolders();
        model.addAttribute("folderster", foldersList);
        return "homepage";
    }

    @PostMapping(value="/addFolder")
    public String addFolder(Folders folder){
        folderService.saveOrUpdateFolder(folder);
        return "redirect:/";
    }

    @PostMapping(value="/addTask")
    public String addTask(Tasks task){

        taskService.saveOrUpdateTask(task);

        return "redirect:/detailsFolder/" + task.getFolder().getId();
    }

    @GetMapping(value = "/detailsFolder/{foldId}")
    public String foldDetails(@PathVariable(name="foldId") Long id, Model model){
       Folders folder = folderService.getFolder(id);
       model.addAttribute("folder", folder);

       List<TaskCategories> taskCategoriesList = categoriesService.getCategories(folder);
       model.addAttribute("categories", taskCategoriesList);

       List<TaskCategories> alltaskCategoriesList = categoriesService.getAllCategories();
       model.addAttribute("allCategories", alltaskCategoriesList);

       List<Tasks> tasksList = taskService.getTasksReferToFolder(folder);
       model.addAttribute("tasks", tasksList);

       return "detailsFolder";

    }

    @GetMapping(value = "/detailsTask/{taskId}")
    public String TaskDetails(@PathVariable(name="taskId") Long id, Model model){
        Tasks task = taskService.getTask(id);
        model.addAttribute("task", task);

        List<Comments> commentsList = commentsService.getCommentsByTask(task);

        model.addAttribute("comments", commentsList);


        return "detailsTask";

    }

    @PostMapping(value = "/add-category")
    public String assignGenre(@RequestParam(name="folder_id") Long folderId,
                              @RequestParam(name="category_id") Long categoryId){
        folderService.addCategory(folderId, categoryId);
        return "redirect:/detailsFolder/" + folderId;
    }


    @PostMapping(value = "/add-comment")
    public String addComment(Comments comment){
        commentsService.saveOrUpdateComment(comment);
        return "redirect:/detailsTask/" + comment.getTask().getId();
    }

    @PostMapping(value = "/change-status")
    public String changeStatus(@RequestParam(name="task_id") Long taskId,
                              @RequestParam(name="status_number") int statusNumber){
        taskService.changeStatus(taskId, statusNumber);
        return "redirect:/detailsFolder/" + taskService.getTask(taskId).getFolder().getId();
    }

    @PostMapping(value = "/remove-category")
    public String unassignGenre(@RequestParam(name="folder_id") Long folderId,
                              @RequestParam(name="category_id") Long categoryId){
        System.out.println();
        System.out.println();
        System.out.println(folderId + " "  +categoryId);
        folderService.removeCategory(folderId, categoryId);
        return "redirect:/detailsFolder/" + folderId;
    }
}
