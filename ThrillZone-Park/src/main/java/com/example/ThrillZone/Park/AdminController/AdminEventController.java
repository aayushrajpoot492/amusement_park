package com.example.ThrillZone.Park.AdminController;

import com.example.ThrillZone.Park.Events.Event_Entity;
import com.example.ThrillZone.Park.Events.Event_Entity_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/admin")
public class AdminEventController {

    @Autowired
    private Event_Entity_Repo eventEntityRepo;

    @GetMapping("/Events")
    public String events(Model model){

        return "Admin/Events";
    }

    @GetMapping("/addEvent")
    public String addEventPage(Model model) {
        model.addAttribute("event", new Event_Entity());
        return "Admin/addEventForm";
    }
    @PostMapping("/addEvent")
    public String addEvent(
            @RequestParam("e_name") String name,
            @RequestParam("e_desc") String description,
            @RequestParam("e_price") int price,
            @RequestParam("e_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate e_date,
            @RequestParam("e_time") @DateTimeFormat(pattern = "HH:mm") LocalTime e_time,
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
        return "redirect:/admin/Events";
    }
}