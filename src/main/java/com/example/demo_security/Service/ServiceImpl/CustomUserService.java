package com.example.demo_security.Service.ServiceImpl;

import com.example.demo_security.Model.CustomUserDetails;
import com.example.demo_security.Model.User;
import com.example.demo_security.Repository.UserRepo;
import com.example.demo_security.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CustomUserService implements UserDetailsService, UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }

    @Override
    public Collection<User> getUserModels() {
        return userRepo.findAll();
    }

    @Override
    public void updateUser(User user) {
        Optional<User> existingUser = userRepo.findById(user.getId());
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            updatedUser.setRoles(user.getRoles());
            updatedUser.setName(user.getName());
            userRepo.save(updatedUser);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
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
    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void updateUserByAdmin(long id, User user) {
        Optional<User> existingUser = userRepo.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            updatedUser.setRoles(user.getRoles());
            updatedUser.setName(user.getName());
            userRepo.save(updatedUser);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
