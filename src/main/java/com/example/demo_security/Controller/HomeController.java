package com.example.demo_security.Controller;

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
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String handleWelcome() {
        return new String("Đây là trang home");
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
//
//    @PutMapping("/user/update")
//    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
//    public ResponseEntity<Object> updateUser(@RequestBody User user){
//        userService.updateUser(user.getId(),user);
//        return new ResponseEntity<>("Updated", HttpStatus.OK);
//    }
//
//    @PutMapping("/admin/update/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<Object> adminUpdateUser(@PathVariable("id") long id, @RequestBody User user){
//        userService.updateUser(id, user);
//        return new ResponseEntity<>("Updated", HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<Object> deleteUser(@PathVariable("id") long id){
//        userService.deleteUser(id);
//        return new ResponseEntity<>("Deleted!",HttpStatus.OK);
//    }

    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

}
