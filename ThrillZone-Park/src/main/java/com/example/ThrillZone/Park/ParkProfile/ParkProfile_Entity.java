package com.example.ThrillZone.Park.ParkProfile;

import jakarta.persistence.*;

@Entity
public class ParkProfile_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long pp_id;

    String p_name,p_desc,history,p_location;
    @Lob
    @Column(columnDefinition = "bytea")
    byte[] p_image;

    public ParkProfile_Entity() {
    }

    public ParkProfile_Entity(Long pp_id, String p_name, String p_desc, String history, String p_location, byte[] p_image) {
        this.pp_id = pp_id;
        this.p_name = p_name;
        this.p_desc = p_desc;
        this.history = history;
        this.p_location = p_location;
        this.p_image = p_image;
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

    public String getP_location() {
        return p_location;
    }

    public void setP_location(String p_location) {
        this.p_location = p_location;
    }

    public byte[] getP_image() {
        return p_image;
    }

    public void setP_image(byte[] p_image) {
        this.p_image = p_image;
    }
}
