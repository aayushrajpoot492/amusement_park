package com.example.ThrillZone.Park.Controllers.SignUp;

import com.example.ThrillZone.Park.Master.User_Master;
import com.example.ThrillZone.Park.Master.User_Master_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("UserDetailform")
    public String UserDetailform() {
        return "User-detailform";
    }

    @Autowired
    User_Master_Repo user_master_repo;

    @PostMapping("/saveuser")
    public String saveuser(String name, Long phoneno, String password, String confirmpassword) {
        User_Master user_master = new User_Master();
        user_master.setM_name(name);
        user_master.setPhone_no(phoneno);
        user_master.setPassword(password);
        user_master_repo.save(user_master);
        return "confirm";
    }

    @GetMapping("signform")
    public String Signupform() {
        return "Signupform";
    }
    @GetMapping("/foodcourt")
    public String foodcourt(){
        return "food";
    }
    @GetMapping("/events")
    public String events(){
        return "event";
    }
    @GetMapping("/dinner")
    public String dinner(){
        return "dinner";
    }

}

