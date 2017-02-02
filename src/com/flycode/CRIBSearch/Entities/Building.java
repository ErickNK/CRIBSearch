package com.flycode.CRIBSearch.Entities;

/**
 * <p>Class used to implement the entity Building of the CRIBSearch Entity Model.</p>
 * <p>It implements Attributes of the relation <b>Building</b> as variables.</p>
 */
public class Building{
    private int id;
    private int Registration_id;
    private String Name;
    private String Owner_Name;
    private String License;
    private String Location;
    private int No_of_rooms;

    //TODO:add date added in entity
    //Setters and Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegistration_id() {
        return Registration_id;
    }

    public void setRegistration_id(int registration_id) {
        Registration_id = registration_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOwner_Name() {
        return Owner_Name;
    }

    public void setOwner_Name(String owner_Name) {
        Owner_Name = owner_Name;
    }

    public String getLicense() {
        return License;
    }

    public void setLicense(String license) {
        License = license;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getNo_of_rooms() {
        return No_of_rooms;
    }

    public void setNo_of_rooms(int no_of_rooms) {
        No_of_rooms = no_of_rooms;
    }
}
