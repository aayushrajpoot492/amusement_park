package com.example.ThrillZone.Park.CustomerControllers.CustomerControllers;

import com.example.ThrillZone.Park.Events.Event_Entity;
import com.example.ThrillZone.Park.Events.Event_Entity_Repo;
import com.example.ThrillZone.Park.FoodCourt.FoodCourt_Entity;
import com.example.ThrillZone.Park.FoodCourt.FoodCourt_Entity_Repo;
import com.example.ThrillZone.Park.Rides.Ride_Entity;
import com.example.ThrillZone.Park.Rides.Ride_Entity_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class CustomerController {

    @Autowired
    private Ride_Entity_Repo ride_entity_repo;

    @Autowired
    private FoodCourt_Entity_Repo foodCourtEntityRepo;

    @Autowired
    private Event_Entity_Repo eventEntityRepo;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/ParkProfile")
    public String parkProfile(Model model) {
        model.addAttribute("activePage", "ParkProfile");
        return "ParkProfile";
    }

    @GetMapping("/rides")
    public String showRides(Model model) {
        List<Ride_Entity> rides = ride_entity_repo.findAll();
        model.addAttribute("rides", rides);
        model.addAttribute("activePage", "rides");
        return "rides";
    }

    @GetMapping("/rideDetails")
    public String getRideDetails(@RequestParam("id") Long rideId, Model model) {
        Ride_Entity ride = ride_entity_repo.findById(rideId).orElse(null);
        model.addAttribute("ride", ride);
        model.addAttribute("activePage", "rides");
        return "rideDetail";
    }

    @GetMapping("/foods")
    public String showfoods(Model model) {
        List<FoodCourt_Entity> foods = foodCourtEntityRepo.findAll();
        model.addAttribute("foods", foods);
        model.addAttribute("activePage", "food");
        return "foods";
    }

    @GetMapping("/foodDetails")
    public String getFoodDetails(@RequestParam("id") Long foodId, Model model) {
        FoodCourt_Entity food = foodCourtEntityRepo.findById(foodId).orElse(null);
        model.addAttribute("food", food);
        model.addAttribute("activePage", "food");
        return "foodDetail";
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