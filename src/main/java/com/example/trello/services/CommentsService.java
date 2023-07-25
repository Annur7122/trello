package com.example.trello.services;

import com.example.trello.models.Comments;
import com.example.trello.models.Tasks;
import com.example.trello.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentRepository commentRepository;

    public List<Comments> getCommentsByTask(Tasks task){
        return commentRepository.findAllByTaskEquals(task);
    }

    public void saveOrUpdateComment(Comments comment){
        commentRepository.save(comment);
    }
}
