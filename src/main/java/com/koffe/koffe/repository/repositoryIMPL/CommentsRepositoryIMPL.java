package com.koffe.koffe.repository.repositoryIMPL;

import com.koffe.koffe.model.Comments;
import com.koffe.koffe.repository.ICommentsRepository;
import com.koffe.koffe.utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentsRepositoryIMPL implements ICommentsRepository {
    @Override
    public List<Comments> findAll() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Comments> comments = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAllComments()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                comments.add(createComments(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }

    @Override
    public Comments findById(int id) {
        return null;
    }

    @Override
    public boolean save(Comments comments) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call saveComments(?, ?, ?, ?, ?)}");
            callableStatement.setString(1, comments.getCommentsFullName());
            callableStatement.setString(2, comments.getCommentsEmail());
            callableStatement.setString(3, comments.getCommentsSubject());
            callableStatement.setString(4, comments.getCommentsMessage());
            callableStatement.setTimestamp(5, comments.getCommentsTime());
            int response = callableStatement.executeUpdate();
            if (response == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean update(Comments comments) {
        return false;
    }

    @Override
    public void deleteById(int id) {

    }

    private Comments createComments(ResultSet rs) {
        Comments comments;
        try {
            int commentsId = rs.getInt(1);
            String commentsFullName = rs.getString(2);
            String commentsEmail = rs.getString(3);
            String commentsSubject = rs.getString(4);
            String commentsMessage = rs.getString(5);
            Timestamp commentsTime = rs.getTimestamp(6);
            comments = new Comments(commentsId, commentsFullName, commentsEmail, commentsSubject, commentsMessage, commentsTime);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }

    @Override
    public List<Integer> getTotalCommentInYear() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Integer> totalCommentInYear = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            totalCommentInYear.add(0);
        }
        try {
            callableStatement = conn.prepareCall("{call getTotalCommentInYear()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                int month = rs.getInt(1);
                totalCommentInYear.set(month - 1, rs.getInt(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return totalCommentInYear;
    }

    @Override
    public List<Comments> findAllCommentByCommentsFullName(String name) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Comments> comments = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAllCommentByCommentsFullName(?)}");
            callableStatement.setString(1, name);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                comments.add(createComments(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return comments;
    }
}
