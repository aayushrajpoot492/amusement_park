package com.example.ThrillZone.Park.Controllers.CustomerControllers;

import com.example.ThrillZone.Park.Events.Event_Entity;
import com.example.ThrillZone.Park.Events.Event_Entity_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class CustomerController {
    @Autowired
    private Event_Entity_Repo eventRepository;

    @GetMapping("/home")
        public String home() {
            return "home";
        }

    @GetMapping("/ParkProfile")
    public String events(Model model) {
        model.addAttribute("activePage", "ParkProfile");
        return "ParkProfile";
    }

    @GetMapping("/addEvent")
    public String addEventForm() {
        return "addEventForm";
    }
    @PostMapping("/addEvent")
    public String saveEvent(@ModelAttribute Event_Entity event,
                            @RequestParam("imageFile") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                event.setE_image(file.getBytes());
            }

            eventRepository.save(event);

            return "redirect:/user/home?success";

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/user/addEvent?error";
        }
    }

}

