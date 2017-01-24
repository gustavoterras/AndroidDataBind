package br.com.infoterras.bindapplication.model;

import java.io.Serializable;

/**
 * Created by Gustavo on 24/01/2017.
 */

public class Content implements Serializable{

    private String name;
    private String path;
    private String type;
    private String download_url;

    public Content() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }
}
