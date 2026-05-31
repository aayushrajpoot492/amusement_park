package com.example.ThrillZone.Park.Controllers.CustomerControllers;

import com.example.ThrillZone.Park.Events.Event_Entity;
import com.example.ThrillZone.Park.Events.Event_Entity_Repo;
import com.example.ThrillZone.Park.FoodCourt.FoodCourt_Entity;
import com.example.ThrillZone.Park.FoodCourt.FoodCourt_Entity_Repo;
import com.example.ThrillZone.Park.Rides.Ride_Entity;
import com.example.ThrillZone.Park.Rides.Ride_Entity_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
    @Autowired
    private Ride_Entity_Repo ride_entity_repo;

    @GetMapping("/addRideForm")
    public String addRideForm() {
        return "ridesForm";
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
            ride_entity_repo.save(ride);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/user/rides";
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {

            String path = System.getProperty("user.dir") + "/uploads/";

            registry.addResourceHandler("/uploads/**")
                    .addResourceLocations("file:" + path);
        }
    }
    @GetMapping("/rides")
    public String showRides(Model model) {
        List<Ride_Entity> rides = ride_entity_repo.findAll();
        model.addAttribute("rides", rides);
        model.addAttribute("activePage", "rides");
        return "rides";
    }
    @GetMapping("/foodCourtForm")
    public String foodform() {
        return "foodCourtForm";
    }

    @Autowired
    private FoodCourt_Entity_Repo foodCourtEntityRepo;

    @PostMapping("/addFood")
    public String addfood(
            @RequestParam("f_name") String name,
            @RequestParam("food_desc") String description,
            @RequestParam("category") String category,
            @RequestParam("f_price") int price,
            @RequestParam("f_image") MultipartFile file,
            @RequestParam(value = "is_available", required = false) Boolean isAvailable

    ) {

        try {
            String uploadDirr = System.getProperty("user.dir") + "/uploads/";
            Files.createDirectories(Paths.get(uploadDirr));

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path filePath = Paths.get(uploadDirr, fileName);
            Files.write(filePath, file.getBytes());

            FoodCourt_Entity food = new FoodCourt_Entity();
            food.setF_name(name);
            food.setDesc(description);
            food.setCategory(category);
            food.setF_price(price);
            food.setIs_available(isAvailable != null ? isAvailable : false);

            food.setF_image("/uploads/" + fileName);
            foodCourtEntityRepo.save(food);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/user/foods";
    }

    @GetMapping("/foods")
    public String showfoods(Model model) {
        List<FoodCourt_Entity> foods = foodCourtEntityRepo.findAll();
        model.addAttribute("foods", foods);
        model.addAttribute("activePage", "food");
        return "foods";
    }
    @GetMapping("/rideDetails")
    public String getRideDetails(@RequestParam("id") Long rideId, Model model) {
        Ride_Entity ride = ride_entity_repo.findById(rideId).orElse(null);

        model.addAttribute("ride", ride);
        model.addAttribute("activePage", "rides");
        return "rideDetail";
    }
    @GetMapping("/foodDetails")
    public String getFoodDetails(@RequestParam("id") Long foodId, Model model) {
        FoodCourt_Entity food = foodCourtEntityRepo.findById(foodId).orElse(null);

        model.addAttribute("food", food);
        model.addAttribute("activePage", "food");
        return "foodDetail";
    }
    @GetMapping("/EventForm")
    public String addevents(){
        return "EventForm";
    }

    @Autowired
    private Event_Entity_Repo eventEntityRepo;

    @PostMapping("/addEvent")
    public String addevent(
            @RequestParam("e_name") String name,
            @RequestParam("e_desc") String description,
            @RequestParam("e_price") int price,
            @RequestParam("e_date")
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate e_date,
            @RequestParam("e_time")
            @DateTimeFormat(pattern = "HH:mm")
            LocalTime e_time,
            @RequestParam("e_image") MultipartFile file,
            @RequestParam(value = "is_available", required = false) Boolean isAvailable
    ) {

        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            Files.createDirectories(Paths.get(uploadDir));

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path filePath = Paths.get(uploadDir, fileName);
            Files.write(filePath, file.getBytes());

            Event_Entity event = new Event_Entity();
            event.setE_name(name);
            event.setE_desc(description);
            event.setE_date(e_date);
            event.setE_time(e_time);
            event.setE_price(price);
            event.setIs_available(isAvailable != null ? isAvailable : false);

            event.setE_image("/uploads/" + fileName);
            eventEntityRepo.save(event);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/user/events";
    }
    @GetMapping("/events")
    public String showevents(Model model) {
        List<Event_Entity> events = eventEntityRepo.findAll();
        model.addAttribute("events", events);
        model.addAttribute("activePage", "event");
        return "events";
    }
    @GetMapping("/eventDetails")
    public String getEventDetail(@RequestParam("id") Long eid, Model model) {
        Event_Entity event = eventEntityRepo.findById(eid).orElse(null);

        model.addAttribute("event", event);
        model.addAttribute("activePage", "event");
        return "eventDetail";
    }

}

