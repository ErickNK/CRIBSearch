package com.flycode.CRIBSearch.Entities;

/**
 * <p>Class used to implement the entity Tenant of the CRIBSearch Entity Model.</p>
 * <p>It implements Attributes of the relation <b>Tenant</b> as variables.</p>
 */
public class Tenant{
    private int id;
    private String First;
    private String Second;
    private String surname;
    private int Tell;
    private int National_ID;
    private String Bio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst() {
        return First;
    }

    public String getSecond() {
        return Second;
    }

    public String getSurname() {
        return surname;
    }

    public int getTell() {
        return Tell;
    }

    public int getNational_ID() {
        return National_ID;
    }

    public String getBio() {
        return Bio;
    }

    public void setFirst(String First) {
        this.First = First;
    }

    public void setSecond(String Second) {
        this.Second = Second;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTell(int Tell) {
        this.Tell = Tell;
    }

    public void setNational_ID(int SocialSecurityNo) {
        this.National_ID = SocialSecurityNo;
    }

    public void setBio(String Bio) {
        this.Bio = Bio;
    }

}
