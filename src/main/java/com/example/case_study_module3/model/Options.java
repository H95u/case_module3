package com.example.case_study_module3.model;

import java.util.List;

public class Options {
    private int id;
    private String name;
    private double price;
    private List<Partner> partnerList;

    public Options() {
    }

    public Options(int id, String name, double price, List<Partner> partnerList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.partnerList = partnerList;
    }

    public Options(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Partner> getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(List<Partner> partnerList) {
        this.partnerList = partnerList;
    }
}
