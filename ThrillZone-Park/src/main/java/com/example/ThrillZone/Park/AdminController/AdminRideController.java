package com.example.ThrillZone.Park.AdminController;

import com.example.ThrillZone.Park.Rides.Ride_Entity;
import com.example.ThrillZone.Park.Rides.Ride_Entity_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/admin")
public class AdminRideController {

    @Autowired
    private Ride_Entity_Repo rideRepo;

    @GetMapping("/Rides")
    public String rides(Model model){
        model.addAttribute("rides", rideRepo.findAll());
        model.addAttribute("totalRides", rideRepo.count());
        model.addAttribute("availableRides", rideRepo.countAvailableRides());
        model.addAttribute("unavailableRides", rideRepo.countUnavailableRides());

        return "Admin/Rides";
    }

    @GetMapping("/addRide")
    public String addRidePage(Model model){
        model.addAttribute("ride", new Ride_Entity());
        return "Admin/ridesForm";
    }

    @PostMapping("/addRide")
    public String addRide(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("capacity") int capacity,
            @RequestParam("min_age") int minAge,
            @RequestParam("r_price") int price,
            @RequestParam("r_image") MultipartFile file,
            @RequestParam(value = "is_available", required = false) Boolean isAvailable
    ) {
        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            Files.createDirectories(Paths.get(uploadDir));

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.write(filePath, file.getBytes());

            Ride_Entity ride = new Ride_Entity();
            ride.setName(name);
            ride.setDescription(description);
            ride.setCapacity(capacity);
            ride.setMin_age(minAge);
            ride.setR_price(price);
            ride.setIs_available(isAvailable != null ? isAvailable : false);
            ride.setR_image("/uploads/" + fileName);

            rideRepo.save(ride);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/Rides";
    }

    @PostMapping("/saveRide")
    public String saveRide(@ModelAttribute Ride_Entity ride){
        rideRepo.save(ride);
        return "redirect:/admin/Rides";
    }

    @GetMapping("/editRide/{id}")
    public String editRide(@PathVariable Long id, Model model){
        Ride_Entity ride = rideRepo.findById(id).orElse(null);
        model.addAttribute("ride", ride);
        return "Admin/editRides";
    }

    @PostMapping("/updateRide")
    public String updateRide(
            @ModelAttribute Ride_Entity ride,
            @RequestParam("oldImage") String oldImage,
            @RequestParam(value = "newImage", required = false) MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                Files.createDirectories(Paths.get(uploadDir));

                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, fileName);
                Files.write(filePath, file.getBytes());

                ride.setR_image("/uploads/" + fileName);
            } else {
                ride.setR_image(oldImage);
            }
            rideRepo.save(ride);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/Rides";
    }

    @GetMapping("/deleteRide/{id}")
    public String deleteRide(@PathVariable Long id){
        rideRepo.deleteById(id);
        return "redirect:/admin/Rides";
    }

    @GetMapping("/changeStatus/{id}")
    public String changeStatus(@PathVariable Long id){
        Ride_Entity ride = rideRepo.findById(id).orElse(null);
        if (ride != null) {
            ride.setIs_available(ride.getIs_available() != null ? !ride.getIs_available() : true);
            rideRepo.save(ride);
        }
        return "redirect:/admin/Rides";
    }
}