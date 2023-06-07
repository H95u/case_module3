package com.example.case_study_module3.model;

public class Album {
    private int id;
    private byte[] image;
    private Partner partner;

    public Album() {
    }

    public Album(int id, byte[] image, Partner partner) {
        this.id = id;
        this.image = image;
        this.partner = partner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

}
