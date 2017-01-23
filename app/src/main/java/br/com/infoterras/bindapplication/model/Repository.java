package br.com.infoterras.bindapplication.model;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by Gustavo on 02/12/2016.
 */

public class Repository implements Serializable {

    private String name;
    private String description;
    private String language;

    public boolean isJava(){
        return !TextUtils.isEmpty(this.language) && this.language.equalsIgnoreCase("JAVA");
    }

    public Repository() {
    }

    public Repository(String name, String description, String language) {
        this.name = name;
        this.language = language;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
