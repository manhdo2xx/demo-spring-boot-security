package com.example.demo_security.Controller;

import com.example.demo_security.Model.CustomUserDetails;
import com.example.demo_security.Model.User;
import com.example.demo_security.Repository.UserRepo;
import com.example.demo_security.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String handleWelcome() {
        return new String("Đây là trang home. "+" Xin chào " + userService.getUserSingle(getLoggedInUserDetails().getUsername()).getName());
    }

    @GetMapping("/user/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> allUser(){
        return new ResponseEntity<>(userService.getUserModels(), HttpStatus.OK);
    }

    @GetMapping("/user/single")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> getUserSingle(){
        return new ResponseEntity<>(userService.getUserSingle(getLoggedInUserDetails().getUsername()),HttpStatus.OK);
    }

    @PutMapping("/user/update")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        CustomUserDetails loggedInUserDetails = (CustomUserDetails) userService.loadUserByUsername(currentUsername);
        User loggedInUser = loggedInUserDetails.getUser();
        loggedInUser.setName(user.getName());
        loggedInUser.setPassword(user.getPassword());
        loggedInUser.setRoles(user.getRoles());
        userService.updateUser(loggedInUser);
        return new ResponseEntity<>("Updated user", HttpStatus.OK);
    }


    @PutMapping("/user/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> updateUserByAdmin(@PathVariable long id,@RequestBody User user){
        userService.updateUserByAdmin(id,user);
        return new ResponseEntity<>("Updated user", HttpStatus.OK);
    }

    @DeleteMapping("/user/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Deleted user",HttpStatus.OK);
    }

    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

}
