package com.example.ThrillZone.Park.Master;

import jakarta.persistence.*;

@Entity
public class User_Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long m_id;

    String m_name,email,password;
    int m_age;
    Long phone_no;
    String confirmpassword;

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public User_Master(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public User_Master(Long m_id, String m_name, String email, String password, int m_age, Long phone_no) {
        this.m_id = m_id;
        this.m_name = m_name;
        this.email = email;
        this.password = password;
        this.m_age = m_age;
        this.phone_no = phone_no;
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

    public int getM_age() {
        return m_age;
    }

    public void setM_age(int m_age) {
        this.m_age = m_age;
    }

    public Long getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(Long phone_no) {
        this.phone_no = phone_no;
    }
}

