package com.example.ThrillZone.Park.Controllers.CustomerControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class CustomerController {

    @GetMapping("/home")
        public String home() {
            return "home";
        }

    @GetMapping("/ParkProfile")
    public String events(Model model) {
        model.addAttribute("activePage", "ParkProfile");
        return "ParkProfile";
    }

}

