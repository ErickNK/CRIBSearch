package com.flycode.CRIBSearch.Entities;

/**
 * <p>This is the base class for all entities in the CRIBSearch PadEntity Model and ERD</p>
 */
public class PadEntity<T> {
    private int id;
    private T t;


    //Getters
    public int getId() {
        return id;
    }
    public void setType(T t){
        this.t = t;
    }
    public T getType(){
        return t;
    }
}
