package com.example.demo_security.Service.ServiceImpl;

import com.example.demo_security.Model.User;
import com.example.demo_security.Repository.UserRepo;
import com.example.demo_security.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public Collection<User> getUserModels() {
        return userRepo.findAll();
    }

    @Override
    public User updateUser(long id, User user) {
        if (userRepo.existsById(id)) {
            user.setId(id);
            userRepo.save(user);
        }
        return null;
    }

    @Override
    public User getUserSingle(String username) {
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public User deleteUser(long id) {
        userRepo.deleteById(id);
        return null;
    }
}
