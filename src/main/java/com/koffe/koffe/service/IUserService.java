package com.koffe.koffe.service;

import com.koffe.koffe.model.User;
import com.koffe.koffe.service.design.IService;

import java.util.List;

public interface IUserService extends IService<User> {
    boolean checkExistUserByPhoneNumber(String phoneNumber);

    boolean checkMatchesFullName(String fullName);

    boolean checkMatchesPhoneNumber(String phoneNumber);

    boolean checkMatchesPassword(String password);

    User findUserByPhoneNumberAndPassword(String phoneNumber, String password);
    int getCountOfUser();
    List<User> findUserByName(String name);
}
