package com.example.ThrillZone.Park.ParkProfile;

import jakarta.persistence.*;

@Entity
public class ParkProfile_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long pp_id;

    String p_name,p_desc,history;

    public ParkProfile_Entity() {
    }

    public ParkProfile_Entity(Long pp_id, String p_name, String p_desc, String history) {
        this.pp_id = pp_id;
        this.p_name = p_name;
        this.p_desc = p_desc;
        this.history = history;
    }

    public Long getPp_id() {
        return pp_id;
    }

    public void setPp_id(Long pp_id) {
        this.pp_id = pp_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_desc() {
        return p_desc;
    }

    public void setP_desc(String p_desc) {
        this.p_desc = p_desc;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
