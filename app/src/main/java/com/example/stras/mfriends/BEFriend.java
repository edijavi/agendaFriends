package com.example.stras.mfriends;

import android.graphics.Bitmap;

import java.util.Date;

public class BEFriend {

    private long id;
    private String first_name;
    private String last_name;
    private String address;
    private String website;
    private String email;
    private String phone;
    private Date birthday;
    private Bitmap image;


    public BEFriend(String first_name, String last_name, String address, String website, String email, String phone, Bitmap image) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.website = website;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public BEFriend(long id, String first_name, String last_name, String address, String website, String email, String phone, Bitmap image) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.website = website;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    /**
     * @return String of composite full name with space between first and last
     */
    public String getName() {
        return first_name + ' ' + last_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String toString() {
        return "" + id + ": " + first_name;
    }
}
