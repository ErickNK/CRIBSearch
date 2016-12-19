package com.flycode.PadSearch;

/**
 * Created by erikn on 12/19/2016.
 */
public class myTable {
    //Database name
    private String DATABASE_NAME;


    //table name
    private String TABLE_NAME;

    private String First_Column;
    private String Second_Column;
    private String Surname_Column;
    private String Tell_Column;
    private String ID_Column;
    private String Bio_Column;

    public String getFirst_Column() {
        return First_Column;
    }

    public void setFirst_Column(String first_Column) {
        First_Column = first_Column;
    }

    public String getSecond_Column() {
        return Second_Column;
    }

    public void setSecond_Column(String second_Column) {
        Second_Column = second_Column;
    }

    public String getSurname_Column() {
        return Surname_Column;
    }

    public void setSurname_Column(String surname_Column) {
        Surname_Column = surname_Column;
    }

    public String getTell_Column() {
        return Tell_Column;
    }

    public void setTell_Column(String tell_Column) {
        Tell_Column = tell_Column;
    }

    public String getID_Column() {
        return ID_Column;
    }

    public void setID_Column(String ID_Column) {
        this.ID_Column = ID_Column;
    }

    public String getBio_Column() {
        return Bio_Column;
    }

    public void setBio_Column(String bio_Column) {
        Bio_Column = bio_Column;
    }
}
