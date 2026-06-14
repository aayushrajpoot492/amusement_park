package com.example.ThrillZone.Park.CustomerControllers.CustomerControllers;

import com.example.ThrillZone.Park.Events.Event_Entity;
import com.example.ThrillZone.Park.Events.Event_Entity_Repo;
import com.example.ThrillZone.Park.Events.Registration_Entity;
import com.example.ThrillZone.Park.Events.Registration_Entity_repo;
import com.example.ThrillZone.Park.Payment.Payment_Entity;
import com.example.ThrillZone.Park.Payment.Payment_Entity_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequestMapping("/user")
public class EventRegistrationController {

    @Autowired
    private Event_Entity_Repo eventRepo;

    @Autowired
    private Registration_Entity_repo registrationRepo;

    @Autowired
    private Payment_Entity_Repo paymentRepo;

    @GetMapping("/bookEvent")
    public String openRegistrationForm(
            @RequestParam("id") Long eventId,
            @RequestParam("price") double price,
            Model model) {

        Event_Entity event = eventRepo.findById(eventId).orElse(null);
        if (event == null) return "redirect:/user/events";

        Registration_Entity registration = new Registration_Entity();
        registration.setEvent(event);

        model.addAttribute("registration", registration);
        model.addAttribute("event", event);
        model.addAttribute("ticketPrice", price);
        model.addAttribute("eventId", eventId);

        return "eventRegistrationForm";
    }

    @PostMapping("/registerEventSummary")
    public String registerEventSummary(
            @ModelAttribute("registration") Registration_Entity registration,
            @RequestParam("eventId") Long eventId,
            @RequestParam("ticketPrice") double ticketPrice,
            Model model
    ) {
        Event_Entity event = eventRepo.findById(eventId).orElse(null);
        registration.setEvent(event);

        double finalTotal = ticketPrice * registration.getParticipants();
        registration.setTotalAmount(finalTotal);

        model.addAttribute("registration", registration);
        model.addAttribute("event", event);
        model.addAttribute("ticketPrice", ticketPrice);
        model.addAttribute("eventId", eventId);

        return "eventRegistrationSummary";
    }

    @PostMapping("/eventPaymentPage")
    public String eventPaymentPage(
            @ModelAttribute("registration") Registration_Entity registration,
            @RequestParam("eventId") Long eventId,
            @RequestParam("ticketPrice") double ticketPrice,
            Model model
    ) {
        Event_Entity event = eventRepo.findById(eventId).orElse(null);
        registration.setEvent(event);

        model.addAttribute("registration", registration);
        model.addAttribute("event", event);
        model.addAttribute("ticketPrice", ticketPrice);
        model.addAttribute("eventId", eventId);

        return "eventPaymentPage";
    }

    @PostMapping("/editRegistration")
    public String editRegistration(
            @ModelAttribute("registration") Registration_Entity registration,
            @RequestParam("eventId") Long eventId,
            @RequestParam("ticketPrice") double ticketPrice,
            Model model
    ) {
        Event_Entity event = eventRepo.findById(eventId).orElse(null);
        registration.setEvent(event);

        model.addAttribute("registration", registration);
        model.addAttribute("event", event);
        model.addAttribute("ticketPrice", ticketPrice);
        model.addAttribute("eventId", eventId);

        return "eventRegistrationForm";
    }

    @PostMapping("/confirmRegistration")
    public String confirmRegistration(
            @RequestParam("eventId") Long eventId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam int participants,
            @RequestParam double totalAmount,
            @RequestParam String paymentMethod,
            Model model
    ) {
        Event_Entity mainEvent = eventRepo.findById(eventId).orElse(null);
        if (mainEvent == null) return "redirect:/user/events";

        Registration_Entity registration = new Registration_Entity();
        registration.setEvent(mainEvent);
        registration.setName(name);
        registration.setEmail(email);
        registration.setPhone(phone);
        registration.setParticipants(participants);
        registration.setTotalAmount(totalAmount);
        registration.setPaymentMethod(paymentMethod);

        String calculatedStatus = paymentMethod.equals("UPI") ? "Paid" : "Pending";
        registration.setPaymentStatus(calculatedStatus);
        registration.setStatus("Confirmed");
        registration.setRegDate(LocalDate.now());
        registration.setRegTime(LocalTime.now());

        Payment_Entity payment = new Payment_Entity();
        payment.setP_mode(paymentMethod);
        payment.setStatus(calculatedStatus);
        payment.setTotal_amount(totalAmount);
        payment.setPayment_date_time(LocalDateTime.now());

        payment.setRegistration(registration);
        registration.setPayment(payment);

        registrationRepo.save(registration);

        int newBookedSeats = mainEvent.getBookedSeats() + participants;
        mainEvent.setBookedSeats(newBookedSeats);

        if (newBookedSeats >= mainEvent.getTotalSeats()) {
            mainEvent.setIs_available(false);
        }
        eventRepo.save(mainEvent);

        model.addAttribute("registrationId", registration.getRegistrationId());
        model.addAttribute("eventName", mainEvent.getE_name());
        model.addAttribute("paymentMethod", paymentMethod);
        model.addAttribute("paymentStatus", calculatedStatus);

        return "eventRegistrationSuccess";
    }
}