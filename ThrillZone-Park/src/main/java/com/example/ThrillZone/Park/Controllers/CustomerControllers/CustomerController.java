package com.example.ThrillZone.Park.Controllers.CustomerControllers;

import com.example.ThrillZone.Park.Booking.Booking_Entity;
import com.example.ThrillZone.Park.Booking.Booking_Entity_Repo;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
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

    @Autowired
    Booking_Entity_Repo bookingRepo;

//    @PostMapping("/bookRide")
//    public String bookride(Booking_Entity booking) {
//        System.out.println(booking.getBooking_date());
//        System.out.println(booking.getBooking_time());
//        bookingentity.save(booking);
//
//        return "redirect:/user/rides";
//    }
//@PostMapping("/bookRide")
//public String bookride(
//        Booking_Entity booking,
//        Model model
//) {
//
//    int total =(int)
//            booking.getTotal_amount()
//                    * booking.getTotal_tickets();
//
//    model.addAttribute("booking", booking);
//
//    model.addAttribute("finalTotal", total);
//
//    return "bookingSummary";
//}
//
//    @PostMapping("/confirmBooking")
//    public String confirmBooking(
//
//            @RequestParam String ride_name,
//            @RequestParam String customer_name,
//            @RequestParam String customer_phone,
//
//            @RequestParam
//            @DateTimeFormat(pattern = "yyyy-MM-dd")
//            LocalDate booking_date,
//
//            @RequestParam
//            @DateTimeFormat(pattern = "HH:mm")
//            LocalTime booking_time,
//
//            @RequestParam int total_tickets,
//
//            @RequestParam double total_amount,
//
//            @RequestParam String payment_method,
//            Model model
//    ) {
//
//        Booking_Entity booking =
//                new Booking_Entity();
//
//        booking.setRide_name(ride_name);
//
//        booking.setCustomer_name(customer_name);
//
//        booking.setCustomer_phone(customer_phone);
//        booking.setBooking_date(booking_date);
//
//        booking.setBooking_time(booking_time);
//
//        booking.setTotal_tickets(total_tickets);
//
//        booking.setTotal_amount(total_amount);
//        booking.setPayment_method(payment_method);
//
//        if(payment_method.equals("UPI")) {
//
//            booking.setPayment_status("Paid");
//
//        }
//        else {
//
//            booking.setPayment_status("Pending");
//        }
//        booking.setStatus("Confirmed");
//
//        bookingRepo.save(booking);
//
//        model.addAttribute(
//                "bookingId",
//                booking.getBooking_id()
//        );
//
//        model.addAttribute("name", ride_name);
//        model.addAttribute(
//                "paymentMethod",
//                payment_method
//        );
//
//        model.addAttribute(
//                "paymentStatus",
//                booking.getPayment_status()
//        );
//        return "bookingSuccess";
//    }
//
//    @PostMapping("/editBooking")
//    public String editBooking(
//            Booking_Entity booking,
//            Model model
//    ) {
//
//        model.addAttribute("booking", booking);
//
//        return "BookingForm";
//    }
//    @PostMapping("/BookingForm")
//    public String bookingForm(
//            @RequestParam("ride_name") String ride_name,
//            @RequestParam("price") double price,
//            Model model) {
//
//        Booking_Entity booking = new Booking_Entity();
//
//        booking.setRide_name(ride_name);
//        booking.setTotal_amount(price);
//
//        model.addAttribute("booking", booking);
//
//        return "BookingForm";
//    }

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
            // 👉 1. Folder path (auto create if not exists)
            String uploadDirr = System.getProperty("user.dir") + "/uploads/";
            Files.createDirectories(Paths.get(uploadDirr));

            // 👉 2. Unique file name (important 🔥)
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            // 👉 3. Save file
            Path filePath = Paths.get(uploadDirr, fileName);
            Files.write(filePath, file.getBytes());

            // 👉 4. Save data in DB
            FoodCourt_Entity food = new FoodCourt_Entity();
            food.setF_name(name);
            food.setDesc(description);
            food.setCategory(category);
            food.setF_price(price);
            food.setIs_available(isAvailable != null ? isAvailable : false);

            // 👉 DB me sirf path save hoga
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
        return "foods";
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
            // 👉 1. Folder path (auto create if not exists)
            String uploadDirr = System.getProperty("user.dir") + "/uploads_Events/";
            Files.createDirectories(Paths.get(uploadDirr));

            // 👉 2. Unique file name (important 🔥)
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            // 👉 3. Save file
            Path filePath = Paths.get(uploadDirr, fileName);
            Files.write(filePath, file.getBytes());

            // 👉 4. Save data in DB
            Event_Entity event = new Event_Entity();
            event.setE_name(name);
            event.setE_desc(description);
            event.setE_date(e_date);
            event.setE_time(e_time);
            event.setE_price(price);
            event.setIs_available(isAvailable != null ? isAvailable : false);

            // 👉 DB me sirf path save hoga
            event.setE_image("/uploads_Events/" + fileName);
            eventEntityRepo.save(event);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/user/foods";
    }

//    @PostMapping("/paymentPage")
//    public String paymentPage(
//            Booking_Entity booking,
//            Model model
//    ) {
//
//        model.addAttribute("booking", booking);
//
//        return "paymentPage";
//    }


}

