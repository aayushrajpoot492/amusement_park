package com.example.ThrillZone.Park;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import java.time.LocalDate;
@Controller
public class SBController {
    @GetMapping("/login")
        public String Login (){
         return "loginform";
        }

        @PostMapping("/login")
        public String login( Model model, String email, String otp) {
            model.addAttribute("email",email);
            model.addAttribute("otp",otp);
            return "logging";
        }
    }
