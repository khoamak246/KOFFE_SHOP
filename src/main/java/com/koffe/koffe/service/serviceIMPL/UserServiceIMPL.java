package com.koffe.koffe.service.serviceIMPL;

import com.koffe.koffe.model.User;
import com.koffe.koffe.repository.IUserRepository;
import com.koffe.koffe.repository.repositoryIMPL.UserRepositoryIMPL;
import com.koffe.koffe.service.IUserService;
import com.koffe.koffe.utils.Validate;

import java.util.List;

public class UserServiceIMPL implements IUserService {
    private static final IUserRepository userRepository = new UserRepositoryIMPL();

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }


    @Override
    public User findUserByPhoneNumberAndPassword(String phoneNumber, String password) {
        return userRepository.findUserByPhoneNumberAndPassword(phoneNumber, password);
    }

    @Override
    public int getCountOfUser() {
        return userRepository.getCountOfUser();
    }

    @Override
    public List<User> findUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public boolean save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean update(User user) {
        return userRepository.update(user);
    }

    @Override
    public void deleteById(int id) {
    }

    @Override
    public boolean checkExistUserByPhoneNumber(String phoneNumber) {
        return userRepository.isExistUser(phoneNumber);
    }

    @Override
    public boolean checkMatchesFullName(String fullName) {
        return Validate.isMatchesFullName(fullName);
    }

    @Override
    public boolean checkMatchesPhoneNumber(String phoneNumber) {
        return Validate.isMatchesPhoneNumber(phoneNumber);
    }

    @Override
    public boolean checkMatchesPassword(String password) {
        return Validate.isMatchesPassword(password);
    }


}
