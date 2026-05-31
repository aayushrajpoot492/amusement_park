package com.example.ThrillZone.Park.Controllers.BookingController;

import com.example.ThrillZone.Park.Booking.Booking_Entity;
import com.example.ThrillZone.Park.Booking.Booking_Entity_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class BookingController {

    @GetMapping("/BookingForm")
    public String bookingform(){
        return "BookingForm";
    }

    @Autowired
    Booking_Entity_Repo bookingRepo;

    @PostMapping("/bookRide")
    public String bookride(
            Booking_Entity booking,
            Model model
    ) {

        int total =(int)
                booking.getTotal_amount()
                * booking.getTotal_tickets();

        model.addAttribute("booking", booking);

        model.addAttribute("finalTotal", total);

        return "bookingSummary";
    }

    @PostMapping("/confirmBooking")
    public String confirmBooking(

            @RequestParam String ride_name,
            @RequestParam String customer_name,
            @RequestParam String customer_phone,

            @RequestParam
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate booking_date,

            @RequestParam
            @DateTimeFormat(pattern = "HH:mm")
            LocalTime booking_time,

            @RequestParam int total_tickets,

            @RequestParam double total_amount,

            @RequestParam String payment_method,
            Model model
    ) {

        Booking_Entity booking =
                new Booking_Entity();

        booking.setRide_name(ride_name);

        booking.setCustomer_name(customer_name);

        booking.setCustomer_phone(customer_phone);
        booking.setBooking_date(booking_date);

        booking.setBooking_time(booking_time);

        booking.setTotal_tickets(total_tickets);

        booking.setTotal_amount(total_amount);
        booking.setPayment_method(payment_method);

        if(payment_method.equals("UPI")) {

            booking.setPayment_status("Paid");

        }
        else {

            booking.setPayment_status("Pending");
        }
        booking.setStatus("Confirmed");

        bookingRepo.save(booking);

        model.addAttribute(
                "bookingId",
                booking.getBooking_id()
        );

        model.addAttribute("name", ride_name);
        model.addAttribute(
                "paymentMethod",
                payment_method
        );

        model.addAttribute(
                "paymentStatus",
                booking.getPayment_status()
        );
        return "bookingSuccess";
    }

    @PostMapping("/editBooking")
    public String editBooking(
            Booking_Entity booking,
            Model model
    ) {

        model.addAttribute("booking", booking);

        return "BookingForm";
    }
    @PostMapping("/BookingForm")
    public String bookingForm(
            @RequestParam("ride_name") String ride_name,
            @RequestParam("price") double price,
            Model model) {

        Booking_Entity booking = new Booking_Entity();

        booking.setRide_name(ride_name);
        booking.setTotal_amount(price);

        model.addAttribute("booking", booking);

        return "BookingForm";
    }

    @PostMapping("/paymentPage")
    public String paymentPage(
            Booking_Entity booking,
            Model model
    ) {
        model.addAttribute("booking", booking);

        return "paymentPage";
    }
}
