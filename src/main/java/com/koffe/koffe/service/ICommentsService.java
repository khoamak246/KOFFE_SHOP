package com.koffe.koffe.service;

import com.koffe.koffe.model.Comments;
import com.koffe.koffe.service.design.IService;

import java.util.List;

public interface ICommentsService extends IService<Comments> {
    boolean isMatchesFullName(String fullName);

    boolean isMatchesEmail(String email);
    List<Integer> getTotalCommentInYear();
    List<Comments> findAllCommentByCommentsFullName(String name);
}
