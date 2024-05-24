package com.example.demo_security.Service;

import com.example.demo_security.Model.User;

import java.util.Collection;

public interface UserService {
    Collection<User> getUserModels();
    User updateUser(long id, User user);
    User getUserSingle(String username);
    User deleteUser(long id);
}
