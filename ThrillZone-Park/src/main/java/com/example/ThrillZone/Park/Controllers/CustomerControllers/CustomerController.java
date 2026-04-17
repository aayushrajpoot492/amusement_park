package com.example.ThrillZone.Park.Controllers.CustomerControllers;

import com.example.ThrillZone.Park.Rides.Ride_Entity;
import com.example.ThrillZone.Park.Rides.Ride_Entity_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/user")
public class CustomerController {

    @GetMapping("/home")
        public String home() {
            return "home";
        }

//    @GetMapping("/rides")
//    public String rides(){
//        return "rides";
//    }
@GetMapping("/ParkProfile")
public String parkprofile() {
    return "ParkProfile";
}

    @GetMapping("/addRideForm")
    public String ridesform() {
        return "ridesForm";
    }



    @Autowired
    private Ride_Entity_Repo ride_entity_repo;
    @PostMapping("/addRide")

    public String addrides( @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam int capacity,
                            @RequestParam int min_age,
                            @RequestParam Double r_price,
                            @RequestParam MultipartFile r_image) throws IOException {

        Ride_Entity ride = new Ride_Entity();

        ride.setName(name);
        ride.setDescription(description);
        ride.setCapacity(capacity);
        ride.setMin_age(min_age);
        ride.setR_price(r_price);


        if (!r_image.isEmpty()) {
            ride.setR_image(r_image.getBytes());
        }
        System.out.println("ADD RIDE API HIT");
        ride_entity_repo.save(ride);

        return "redirect:/user/addRideForm";        }
}

