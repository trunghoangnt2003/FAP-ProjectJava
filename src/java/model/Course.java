/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author trung
 */
public class Course {
    private int idCourse;
    private String codeCourse;
    private String nameCourse;
    private int numberLessionOfCourse;

    public Course(int idCourse, String codeCourse, String nameCourse, int numberLessionOfCourse) {
        this.idCourse = idCourse;
        this.codeCourse = codeCourse;
        this.nameCourse = nameCourse;
        this.numberLessionOfCourse = numberLessionOfCourse;
    }

    public int getNumberLessionOfCourse() {
        return numberLessionOfCourse;
    }

    public void setNumberLessionOfCourse(int numberLessionOfCourse) {
        this.numberLessionOfCourse = numberLessionOfCourse;
    }

   

    public Course() {
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getCodeCourse() {
        return codeCourse;
    }

    public void setCodeCourse(String codeCourse) {
        this.codeCourse = codeCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }
    
    
}
