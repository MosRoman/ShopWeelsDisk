package com.gmail.morovo1988.Service;

import com.gmail.morovo1988.Entity.User;

import java.util.List;

public interface UserService {
    User getUserByLogin(String login);
    boolean existsByLogin(String login);
    void addUser(User user);
    void updateUser(User user);
    List<User> findAllUsers();


}
