package br.com.infoterras.bindapplication.model;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Gustavo on 28/11/2016.
 */

public class User implements Serializable {

    private static final String TAG = User.class.getSimpleName();

    private String firstName;
    private String lastName;
    private boolean active;
    private int age;

    public User() {
    }

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void onSaveUser(User user){
        Log.d(TAG, "user saved!");
    }

    public void onStatusChange(User user, boolean active){
        Log.d(TAG, "user status change! > " + user.getFirstName() + " status: " + active);
    }
}
