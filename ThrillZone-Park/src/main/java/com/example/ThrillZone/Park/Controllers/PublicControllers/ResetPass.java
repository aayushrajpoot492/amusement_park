package com.example.ThrillZone.Park.Controllers.PublicControllers;

import com.example.ThrillZone.Park.Master.User_Master_Repo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ResetPass {

    @Autowired
    User_Master_Repo User_Repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/forgot-pass-form")
    public String forgotPass(HttpSession session) {
        if (session.getAttribute("userEmail") == null) {
            return "redirect:/signup-form?mode=forgot";
        }
        return "forgot-password";
    }
    @PostMapping("/reset-password")
    @ResponseBody
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String newPass,
                                                HttpSession session) {

        String verifiedEmail = (String) session.getAttribute("userEmail");
        if (verifiedEmail == null || !verifiedEmail.equals(email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Session");
        }

        return User_Repo.findByEmail(email).map(user -> {
            user.setPassword(passwordEncoder.encode(newPass));
            User_Repo.save(user);

            session.removeAttribute("userEmail");
            return ResponseEntity.ok("Success");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found"));
    }

}