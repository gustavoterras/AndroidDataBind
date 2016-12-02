package br.com.infoterras.bindapplication.model;

import java.io.Serializable;

/**
 * Created by Gustavo on 02/12/2016.
 */

public class Repository implements Serializable {

    private String name;
    private String html_url;
    private String language;
    private String clone_url;

    public Repository() {
    }

    public Repository(String name, String html_url, String language, String clone_url) {
        this.name = name;
        this.html_url = html_url;
        this.language = language;
        this.clone_url = clone_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getClone_url() {
        return clone_url;
    }

    public void setClone_url(String clone_url) {
        this.clone_url = clone_url;
    }
}
