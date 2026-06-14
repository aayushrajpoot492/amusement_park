package com.example.ThrillZone.Park.CustomerControllers.PublicControllers;


import com.example.ThrillZone.Park.Master.User_Master;
import com.example.ThrillZone.Park.Master.User_Master_Repo;
import com.example.ThrillZone.Park.Master_Role.Master_Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminAuth {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private User_Master_Repo userRepo;

    @GetMapping("/force-create-admin")
    @ResponseBody
    public String forceCreateAdmin() {
        userRepo.findByEmail("admin@thrillzone.com").ifPresent(existingAdmin -> {
            userRepo.delete(existingAdmin);
        });

        User_Master admin = new User_Master();
        admin.setEmail("admin@thrillzone.com");
        admin.setM_name("Super Admin");
        admin.setPhone_no(9999999999L);
        admin.setIs_verified(true);

        Master_Role adminRole = new Master_Role(2L, "ADMIN");
        admin.setRole(adminRole);

        admin.setPassword(passwordEncoder.encode("Admin@123"));

        userRepo.save(admin);
        return "<h1>Dynamic Admin Generated Successfully with exact system salt matching! Now try logging in.</h1>";
    }
}