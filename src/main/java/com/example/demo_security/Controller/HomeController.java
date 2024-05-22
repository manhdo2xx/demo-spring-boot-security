package com.example.demo_security.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/home")
    public String handleWelcome() {
        return new String("Đây là trang home");
    }

    @GetMapping("/admin/home")
    public String handleAdminHome() {
        return new String("Đây là trang admin");
    }

    @GetMapping("/user/home")
    public String handleUserHome() {
        return new String("Đây là trang user");
    }

    @GetMapping("/login")
    public String handleLogin() {
        return "custom_login";
    }

}
