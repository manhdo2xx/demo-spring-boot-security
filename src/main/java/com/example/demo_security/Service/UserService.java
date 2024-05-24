package com.example.demo_security.Service;

import com.example.demo_security.Model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

public interface UserService extends UserDetailsService {
    Collection<User> getUserModels();
    void updateUser(User user);
    void deleteUser(long id);
    void updateUserByAdmin(long id, User user);
    User getUserSingle(String username);

}
