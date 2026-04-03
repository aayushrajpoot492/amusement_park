package com.example.ThrillZone.Park.Controllers.UserDetails;

import com.example.ThrillZone.Park.Master.User_Master;
import com.example.ThrillZone.Park.Master.User_Master_Repo;
import com.example.ThrillZone.Park.Master_Role.Master_Role;
import com.example.ThrillZone.Park.Master_Role.Master_Role_Repo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserDetails {
    @Autowired
    User_Master_Repo User_Repo;

    @Autowired
    Master_Role_Repo roleRepo;

    @GetMapping("/user-details")
    public String showUserDetailForm() {
        return "user-detail-form";
    }
    @PostMapping("/save-user-details")
    public ResponseEntity<String> setUserDetails(@RequestParam String uname, @RequestParam long phoneNo,
                                                 @RequestParam String password, @RequestParam String confirmPass, HttpSession session) {

        String verifiedEmail = (String) session.getAttribute("userEmail");
        if (verifiedEmail == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session Expired");
        }

        if (User_Repo.existsByPhoneNo(phoneNo)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Phone number already registered!");
        }

        if (String.valueOf(phoneNo).length() < 10 || !password.equals(confirmPass)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Input");
        }

        User_Master user = new User_Master();
        user.setM_name(uname);
        user.setPhone_no(phoneNo);
        user.setPassword(password);
        user.setEmail(verifiedEmail);
        user.setIs_verified(true);
        Master_Role customerRole = roleRepo.findById(1L).orElseThrow(() ->
                new RuntimeException("Default Role not found! Please add roles to DB."));

        user.setRole(customerRole);
        User_Repo.save(user);

        session.removeAttribute("userEmail");

        return ResponseEntity.ok("Success");
    }
}
