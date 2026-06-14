package com.example.ThrillZone.Park.CustomerControllers.CustomerControllers;

import com.example.ThrillZone.Park.Rides.Booking_Entity;
import com.example.ThrillZone.Park.Rides.Booking_Entity_Repo;
import com.example.ThrillZone.Park.Rides.Ride_Entity;
import com.example.ThrillZone.Park.Rides.Ride_Entity_Repo;
import com.example.ThrillZone.Park.Payment.Payment_Entity;
import com.example.ThrillZone.Park.Payment.Payment_Entity_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequestMapping("/user")
public class RideBookingController {

    @Autowired
    private Booking_Entity_Repo bookingRepo;

    @Autowired
    private Ride_Entity_Repo rideRepo;

    @Autowired
    private Payment_Entity_Repo paymentRepo;

    @PostMapping("/BookingForm")
    public String bookingForm(
            @RequestParam(value = "id", required = false) Long rideId,
            @RequestParam(value = "ride_name", required = false) String ride_name,
            @RequestParam("price") double price,
            Model model) {

        Ride_Entity ride = null;
        if (rideId != null) {
            ride = rideRepo.findById(rideId).orElse(null);
        } else if (ride_name != null) {
            ride = rideRepo.findAll().stream()
                    .filter(r -> r.getName().equalsIgnoreCase(ride_name))
                    .findFirst().orElse(null);
        }

        if (ride == null) {
            return "redirect:/user/rides";
        }

        Booking_Entity booking = new Booking_Entity();
        booking.setRide(ride);
        booking.setTotal_amount(price);

        model.addAttribute("booking", booking);
        model.addAttribute("ride", ride);
        model.addAttribute("rideId", ride.getRide_id());

        return "rideBookingForm";
    }

    @PostMapping("/bookRide")
    public String bookride(
            @ModelAttribute("booking") Booking_Entity booking,
            @RequestParam("rideId") Long rideId,
            Model model
    ) {
        Ride_Entity ride = rideRepo.findById(rideId).orElse(null);
        booking.setRide(ride);

        int total = (int) booking.getTotal_amount() * booking.getTotal_tickets();

        model.addAttribute("booking", booking);
        model.addAttribute("ride", ride);
        model.addAttribute("rideId", rideId);
        model.addAttribute("finalTotal", total);

        return "rideBookingSummary";
    }

    @PostMapping("/paymentPage")
    public String paymentPage(
            @ModelAttribute("booking") Booking_Entity booking,
            @RequestParam("rideId") Long rideId,
            Model model
    ) {
        Ride_Entity ride = rideRepo.findById(rideId).orElse(null);
        booking.setRide(ride);

        model.addAttribute("booking", booking);
        model.addAttribute("ride", ride);
        model.addAttribute("rideId", rideId);

        return "ridePaymentPage";
    }

    @PostMapping("/editBooking")
    public String editBooking(
            @ModelAttribute("booking") Booking_Entity booking,
            @RequestParam("rideId") Long rideId,
            Model model
    ) {
        Ride_Entity ride = rideRepo.findById(rideId).orElse(null);
        booking.setRide(ride);

        model.addAttribute("booking", booking);
        model.addAttribute("ride", ride);
        model.addAttribute("rideId", rideId);

        return "rideBookingForm";
    }

    @PostMapping("/confirmBooking")
    public String confirmBooking(
            @RequestParam("rideId") Long rideId,
            @RequestParam String customer_name,
            @RequestParam String customer_phone,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate booking_date,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime booking_time,
            @RequestParam int total_tickets,
            @RequestParam double total_amount,
            @RequestParam String payment_method,
            Model model
    ) {
        Ride_Entity ride = rideRepo.findById(rideId).orElse(null);
        if (ride == null) return "redirect:/user/rides";

        Booking_Entity booking = new Booking_Entity();
        booking.setRide(ride);
        booking.setCustomer_name(customer_name);
        booking.setCustomer_phone(customer_phone);
        booking.setBooking_date(booking_date);
        booking.setBooking_time(booking_time);
        booking.setTotal_tickets(total_tickets);
        booking.setTotal_amount(total_amount);
        booking.setPayment_method(payment_method);

        String calculatedStatus = payment_method.equals("UPI") ? "Paid" : "Pending";
        booking.setPayment_status(calculatedStatus);
        booking.setStatus("Confirmed");

        Payment_Entity payment = new Payment_Entity();
        payment.setP_mode(payment_method);
        payment.setStatus(calculatedStatus);
        payment.setTotal_amount(total_amount);
        payment.setPayment_date_time(LocalDateTime.now());

        payment.setBooking(booking);
        booking.setPayment(payment);

        bookingRepo.save(booking);

        model.addAttribute("bookingId", booking.getBooking_id());
        model.addAttribute("name", ride.getName());
        model.addAttribute("paymentMethod", payment_method);
        model.addAttribute("paymentStatus", booking.getPayment_status());

        return "rideBookingSuccess";
    }
}