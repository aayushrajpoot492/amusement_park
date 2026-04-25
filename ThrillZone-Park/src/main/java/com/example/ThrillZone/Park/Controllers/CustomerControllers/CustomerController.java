package com.example.ThrillZone.Park.Controllers.CustomerControllers;

import com.example.ThrillZone.Park.FoodCourt.FoodCourt_Entity;
import com.example.ThrillZone.Park.FoodCourt.FoodCourt_Entity_Repo;
import com.example.ThrillZone.Park.Rides.Ride_Entity;
import com.example.ThrillZone.Park.Rides.Ride_Entity_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
            ride_entity_repo.save(ride);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/user/rides";
    }

    @GetMapping("/rides")
    public String showRides(Model model) {
        List<Ride_Entity> rides = ride_entity_repo.findAll();
        model.addAttribute("rides", rides);
        return "rides";
    }

//    @Configuration
//    public class WebConfig implements WebMvcConfigurer {
//
//        @Override
//        public void addResourceHandlers(ResourceHandlerRegistry registry) {
//            registry.addResourceHandler("/uploads/**")
//                    .addResourceLocations("file:" + System.getProperty("user.dir") + "/uploads/");
//        }
//    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {

            String path = System.getProperty("user.dir") + "/uploads/";

            registry.addResourceHandler("/uploads/**")
                    .addResourceLocations("file:" + path);
        }
    }

    @GetMapping("/BookingForm")
    public String bookingform(){
        return "BookingForm";
    }

    @GetMapping("/addFoodForm")
    public String foodform() {
        return "foodCourtForm";
    }

//    @Autowired
//    private FoodCourt_Entity_Repo foodCourtEntityRepo;
//
//    @PostMapping("/addFood")
//    public String addfood(
//            @RequestParam("f_name") String name,
//            @RequestParam("food_desc") String description,
//            @RequestParam("category") String category,
//            @RequestParam("f_price") int price,
//            @RequestParam("f_image") MultipartFile file,
//            @RequestParam(value = "is_available", required = false) Boolean isAvailable
//
//    ) {
//
//        try {
//            // 👉 1. Folder path (auto create if not exists)
//            String uploadDirr = System.getProperty("user.dir") + "/uploads/";
//            Files.createDirectories(Paths.get(uploadDirr));
//
//            // 👉 2. Unique file name (important 🔥)
//            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//
//            // 👉 3. Save file
//            Path filePath = Paths.get(uploadDirr, fileName);
//            Files.write(filePath, file.getBytes());
//
//            // 👉 4. Save data in DB
//            FoodCourt_Entity food = new FoodCourt_Entity();
//            food.setF_name(name);
//            food.setDesc(description);
//            food.setCategory(category);
//            food.setF_price(price);
//            food.setIs_available(isAvailable != null ? isAvailable : false);
//
//            // 👉 DB me sirf path save hoga
//            food.setF_image("/uploads/" + fileName);
//            foodCourtEntityRepo.save(food);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "redirect:/success";
//    }
}

