package com.example.ThrillZone.Park.Controllers.ParkProfile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ParkProfileController {

    @GetMapping("/ParkProfile")
    public String parkprofile(){
        return ("ParkProfile.html");
    }
}
