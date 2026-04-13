package com.example.ThrillZone.Park.Controllers.PublicControllers;


import com.example.ThrillZone.Park.Master.User_Master_Repo;
import com.example.ThrillZone.Park.Service.EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    User_Master_Repo User_Repo;

    @Autowired
     EmailService emailService;

    private static Map<String, String> otpStorage = new HashMap<>();

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String email, @RequestParam(defaultValue = "signup") String mode, HttpSession session) {

        boolean userExists = User_Repo.existsByEmail(email);

        if (mode.equals("signup") && userExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered!");
        }

        if (mode.equals("forgot") && !userExists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found!");
        }

        String otp = String.valueOf(new Random().nextInt(9000) + 1000);
        otpStorage.put(email, otp);
        emailService.sendOtpEmail(email, otp);
        session.setAttribute("userEmail", email);
        return ResponseEntity.ok("OTP Sent Successfully");
    }


    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp, HttpSession session) {
        if (otpStorage.containsKey(email) && otpStorage.get(email).equals(otp)) {
            otpStorage.remove(email);
            session.setAttribute("userEmail", email);
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
    }
}
