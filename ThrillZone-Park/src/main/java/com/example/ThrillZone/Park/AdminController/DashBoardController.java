
package com.example.ThrillZone.Park.AdminController;

import com.example.ThrillZone.Park.Rides.Booking_Entity;
import com.example.ThrillZone.Park.Rides.Booking_Entity_Repo;
import com.example.ThrillZone.Park.Events.Event_Entity_Repo;
import com.example.ThrillZone.Park.FoodCourt.FoodCourt_Entity_Repo;
import com.example.ThrillZone.Park.Rides.Ride_Entity_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")

public class DashBoardController {
    @Autowired
    private Ride_Entity_Repo rideRepo;

    @Autowired
    private Event_Entity_Repo eventRepo;

    @Autowired
    private FoodCourt_Entity_Repo foodRepo;

    @Autowired
    private Booking_Entity_Repo bookingRepo;

    @GetMapping("/dashboard")
    public String dashboard(Model model){

        model.addAttribute("rideCount", rideRepo.count());
        model.addAttribute("eventCount", eventRepo.count());
        model.addAttribute("foodCount", foodRepo.count());
        model.addAttribute("bookingCount", bookingRepo.count());

        model.addAttribute("bookings",
                bookingRepo.getRecentBookings());

        double revenue = bookingRepo.findAll()
                .stream()
                .mapToDouble(Booking_Entity::getTotal_amount)
                .sum();

        model.addAttribute("revenue", revenue);

        return "Admin/dashboard";
    }
}