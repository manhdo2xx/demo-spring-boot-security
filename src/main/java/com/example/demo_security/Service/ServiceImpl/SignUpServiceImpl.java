package com.example.demo_security.Service.ServiceImpl;

import com.example.demo_security.Model.User;
import com.example.demo_security.Model.UserRequest;
import com.example.demo_security.Repository.UserRepo;
import com.example.demo_security.Service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserRequest userRequest) {
        User user = new User();
        user.setRoles(userRequest.getRoles());
        user.setName(userRequest.getName());
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User savedUser = userRepo.save(user);
    }
}
