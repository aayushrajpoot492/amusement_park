package com.example.ThrillZone.Park.Security;

import com.example.ThrillZone.Park.Master.User_Master;
import com.example.ThrillZone.Park.Master.User_Master_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAccountController implements UserDetailsService {

    @Autowired
    private User_Master_Repo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User_Master user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        String roleStr = "ROLE_CUSTOMER";
        if (user.getRole() != null) {
            roleStr = "ROLE_" + user.getRole().getRole_name();
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .disabled(!user.isIs_verified())
                .authorities(roleStr)
                .build();
    }
}