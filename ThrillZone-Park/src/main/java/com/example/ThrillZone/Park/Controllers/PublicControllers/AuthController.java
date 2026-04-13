package com.example.ThrillZone.Park.Controllers.PublicControllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

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
