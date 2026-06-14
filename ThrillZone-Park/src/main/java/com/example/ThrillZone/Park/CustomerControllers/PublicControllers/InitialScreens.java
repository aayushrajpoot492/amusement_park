package com.example.ThrillZone.Park.CustomerControllers.PublicControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitialScreens {

    @GetMapping("/")
    public String Frontpage () {
        return "splash-screen";
    }

}
