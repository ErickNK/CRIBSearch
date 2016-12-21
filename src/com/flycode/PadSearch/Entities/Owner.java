package com.flycode.PadSearch.Entities;

/**
 * <p>Class used to implement the entity Owner of the PadSearch Entity Model.</p>
 * <p>It implements Attributes of the relation <b>Owner</b> as variables.</p>
 */
public class Owner extends PadEntity<Owner> {
    private int id;
    private String First;
    private String Second;
    private String surname;
    private int National_id;
    private int Tell;
    private String Bio;
    private int Owner_id;

    public String getFirst() {
        return First;
    }

    public void setFirst(String first) {
        First = first;
    }

    public String getSecond() {
        return Second;
    }

    public void setSecond(String second) {
        Second = second;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNational_id() {
        return National_id;
    }

    public void setNational_id(int national_id) {
        National_id = national_id;
    }

    public int getTell() {
        return Tell;
    }

    public void setTell(int tell) {
        Tell = tell;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public int getOwner_id() {
        return Owner_id;
    }

    public void setOwner_id(int owner_id) {
        Owner_id = owner_id;
    }
}
