package com.koffe.koffe.service.serviceIMPL;

import com.koffe.koffe.model.Comments;
import com.koffe.koffe.repository.ICommentsRepository;
import com.koffe.koffe.repository.repositoryIMPL.CommentsRepositoryIMPL;
import com.koffe.koffe.service.ICommentsService;
import com.koffe.koffe.utils.Validate;

import java.util.List;

public class CommentsServiceIMPL implements ICommentsService {
    ICommentsRepository commentsRepository = new CommentsRepositoryIMPL();

    @Override
    public List<Comments> findAll() {
        return commentsRepository.findAll();
    }

    @Override
    public Comments findById(int id) {
        return null;
    }

    @Override
    public boolean save(Comments comments) {
        return commentsRepository.save(comments);
    }

    @Override
    public boolean update(Comments comments) {
        return false;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public boolean isMatchesFullName(String fullName) {
        return Validate.isMatchesFullName(fullName);
    }

    @Override
    public boolean isMatchesEmail(String email) {
        return Validate.isMatchesEmail(email);
    }

    @Override
    public List<Integer> getTotalCommentInYear() {
        return commentsRepository.getTotalCommentInYear();
    }

    @Override
    public List<Comments> findAllCommentByCommentsFullName(String name) {
        return commentsRepository.findAllCommentByCommentsFullName(name);
    }
}
