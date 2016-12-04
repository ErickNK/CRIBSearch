/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flycode;

/**
 *
 * @author erikn
 * Personal Info data binder and parser
 */
public class DataParser {
    private String First;
    private String Second;
    private String surname;
    private int Tell;
    private int SocialSecurityNo;
    private String Bio;

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

    public int getSocialSecurityNo() {
        return SocialSecurityNo;
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

    public void setSocialSecurityNo(int SocialSecurityNo) {
        this.SocialSecurityNo = SocialSecurityNo;
    }

    public void setBio(String Bio) {
        this.Bio = Bio;
    }
    
}
