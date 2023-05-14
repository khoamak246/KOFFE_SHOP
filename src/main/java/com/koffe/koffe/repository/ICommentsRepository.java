package com.koffe.koffe.repository;

import com.koffe.koffe.model.Comments;
import com.koffe.koffe.repository.design.IRepository;

import java.util.List;

public interface ICommentsRepository extends IRepository<Comments> {
    List<Integer> getTotalCommentInYear();
    List<Comments> findAllCommentByCommentsFullName(String name);
}
