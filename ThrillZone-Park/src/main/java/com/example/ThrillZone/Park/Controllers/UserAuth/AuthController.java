package com.example.ThrillZone.Park.Controllers.UserAuth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/signup-form")
    public String   Signup (){
        return "signup-form";
    }

    @GetMapping("/verify-otp-model")
    public String showOtpPage() {
        return "otp-verify";
    }

    @GetMapping("/login-form")
    public String Login (){
        return "login-form";
    }
    @GetMapping("/forgot-pass-form")
    public String forgotPass(){
        return "forgot-password";
    }
}
