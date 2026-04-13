package com.example.ThrillZone.Park.Controllers.PublicControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitialScreens {

    @GetMapping("/splash-screen")
    public String Frontpage () {
        return "splash-screen";
    }

    @GetMapping("/home")
    public String home () {
        return "home";
    }
}
