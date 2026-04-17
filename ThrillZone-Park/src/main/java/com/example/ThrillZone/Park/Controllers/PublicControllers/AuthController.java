package com.example.ThrillZone.Park.Controllers.PublicControllers;

import com.example.ThrillZone.Park.Master.User_Master_Repo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @Autowired
    private User_Master_Repo userRepo;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @GetMapping("/signup-form")
    public String   Signup (){
        return "signup-form";
    }

    @GetMapping("/verify-otp-model")
    public String showOtpPage(HttpSession session) {
        if (session.getAttribute("userEmail") == null) {
            return "redirect:/login-form";
        }
        return "otp-verify";
    }

    @GetMapping("/login-form")
    public String Login (){
        return "login-form";
    }

}
