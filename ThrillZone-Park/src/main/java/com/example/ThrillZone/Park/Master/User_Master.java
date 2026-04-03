package com.example.ThrillZone.Park.Master;

import com.example.ThrillZone.Park.Master_Role.Master_Role;
import jakarta.persistence.*;

@Entity
public class User_Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long m_id;

    @Column(unique = true, nullable = false)
    String email;

    String m_name,password;

    @Column(unique = true)
    Long phone_no;
    boolean is_verified;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
     Master_Role role;

    public User_Master(Long m_id, String email, String m_name, String password, Long phone_no, boolean is_verified, Master_Role role) {
        this.m_id = m_id;
        this.email = email;
        this.m_name = m_name;
        this.password = password;
        this.phone_no = phone_no;
        this.is_verified = is_verified;
        this.role = role;
    }

    public User_Master() {
    }

    public Long getM_id() {
        return m_id;
    }

    public void setM_id(Long m_id) {
        this.m_id = m_id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(Long phone_no) {
        this.phone_no = phone_no;
    }

    public Master_Role getRole() {
        return role;
    }

    public void setRole(Master_Role role) {
        this.role = role;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }
}

