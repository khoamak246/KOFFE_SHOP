package com.koffe.koffe.repository;

import com.koffe.koffe.model.User;
import com.koffe.koffe.repository.design.IRepository;

import java.util.List;

public interface IUserRepository extends IRepository<User> {
    boolean isExistUser(String phoneNumber);
    User findUserByPhoneNumberAndPassword(String phoneNumber, String password);
    int getCountOfUser();
    List<User> findUserByName(String name);
}
