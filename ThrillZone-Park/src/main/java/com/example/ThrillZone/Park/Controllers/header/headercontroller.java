package com.example.ThrillZone.Park.Controllers.header;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class headercontroller {
    @GetMapping("/header")
    public String header() {
        return "Homefragments/header.html";

    }
    @GetMapping("/home")
    public String home(Model model) {



        return "Homefragments/header.html";
    }
}
