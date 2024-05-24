package com.example.demo_security.Service.ServiceImpl;

import com.example.demo_security.Model.User;
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
    public void createUser(User user) {
        userRepo.save(user);
    }
}
