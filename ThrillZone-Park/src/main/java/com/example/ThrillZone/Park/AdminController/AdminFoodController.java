package com.example.ThrillZone.Park.AdminController;

import com.example.ThrillZone.Park.FoodCourt.FoodCourt_Entity;
import com.example.ThrillZone.Park.FoodCourt.FoodCourt_Entity_Repo;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/admin")
public class AdminFoodController {

    @Autowired
    private FoodCourt_Entity_Repo foodCourtEntityRepo;

    @GetMapping("/Foods")
    public String foods(Model model){

        return "Admin/Foods";
    }
    @GetMapping("/addFood")
    public String addFoodPage(Model model) {
        model.addAttribute("food", new FoodCourt_Entity());
        return "Admin/foodCourtForm";
    }

    @PostMapping("/addFood")
    public String addFood(
            @RequestParam("f_name") String name,
            @RequestParam("food_desc") String description,
            @RequestParam("category") String category,
            @RequestParam("f_price") int price,
            @RequestParam("f_image") MultipartFile file,
            @RequestParam(value = "is_available", required = false) Boolean isAvailable
    ) {
        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            Files.createDirectories(Paths.get(uploadDir));

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
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

        return "redirect:/admin/Foods";
    }
}