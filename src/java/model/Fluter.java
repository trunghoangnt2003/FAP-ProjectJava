/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author trung
 */
public class Fluter {
    private int idFluter;
    private String nameFluter;
    private String url;

    public Fluter(int idFluter, String nameFluter, String url) {
        this.idFluter = idFluter;
        this.nameFluter = nameFluter;
        this.url = url;
    }

    public int getIdFluter() {
        return idFluter;
    }

    public void setIdFluter(int idFluter) {
        this.idFluter = idFluter;
    }

    public String getNameFluter() {
        return nameFluter;
    }

    public void setNameFluter(String nameFluter) {
        this.nameFluter = nameFluter;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
