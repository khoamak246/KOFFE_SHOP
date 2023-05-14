package com.koffe.koffe.repository.repositoryIMPL;

import com.koffe.koffe.model.User;
import com.koffe.koffe.repository.IUserRepository;
import com.koffe.koffe.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryIMPL implements IUserRepository {

    @Override
    public List<User> findAll() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<User> listUser = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAllUser()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                User user = createUser(rs);
                listUser.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return listUser;
    }

    @Override
    public User findById(int id) {
        User user = null;
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call findUserById(?)}");
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                user = createUser(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return user;
    }

    @Override
    public User findUserByPhoneNumberAndPassword(String phoneNumber, String password) {
        User user = null;
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call findUserByPhoneNumberAndPassword(?, ?)}");
            callableStatement.setString(1, phoneNumber);
            callableStatement.setString(2, password);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                user = createUser(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return user;
    }

    @Override
    public int getCountOfUser() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call getCountOfUser()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }

        return 0;
    }

    @Override
    public List<User> findUserByName(String name) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<User> users = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findUserByName(?)}");
            callableStatement.setString(1, name);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                users.add(createUser(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return users;
    }


    @Override
    public boolean save(User user) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call saveUser(?, ?, ?)}");
            callableStatement.setString(1, user.getFullName());
            callableStatement.setString(2, user.getPhoneNumber());
            callableStatement.setString(3, user.getPassword());
            int response = callableStatement.executeUpdate();
            if (response == 1)
                return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call updateUser(?, ?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1, user.getUserId());
            callableStatement.setString(2, user.getFullName());
            callableStatement.setString(3, user.getPhoneNumber());
            callableStatement.setString(4, user.getPassword());
            callableStatement.setBoolean(5, user.isGender());
            callableStatement.setString(6, user.getAvatar());
            if (user.isStatus()) {
                callableStatement.setInt(7, 1);
            } else {
                callableStatement.setInt(7, 0);
            }
            callableStatement.setInt(8, user.getRole());
            int response = callableStatement.executeUpdate();
            if (response == 1)
                return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return false;
    }

    @Override
    public boolean isExistUser(String phoneNumber) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call findUserByPhoneNumber(?)}");
            callableStatement.setString(1, phoneNumber);
            ResultSet rs = callableStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
    }

    @Override
    public void deleteById(int id) {
    }

    private User createUser(ResultSet rs) {
        User user;
        try {
            int userId = rs.getInt(1);
            String userFullName = rs.getString(2);
            String userPhoneNumber = rs.getString(3);
            String userPassword = rs.getString(4);
            boolean userGender = rs.getBoolean(5);
            String avatar = rs.getString(6);
            int role = rs.getInt(7);
            boolean status = rs.getBoolean(8);
            user = new User(userId, userFullName, userPhoneNumber, userPassword, userGender, avatar, role, status);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


}
