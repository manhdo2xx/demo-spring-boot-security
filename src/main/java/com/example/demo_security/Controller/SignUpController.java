package com.example.demo_security.Controller;


import com.example.demo_security.Model.User;
import com.example.demo_security.Service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    @Autowired
    SignUpService signUpService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        signUpService.createUser(user);
        return ResponseEntity.ok().body("User registered successfully!");
    }
}
