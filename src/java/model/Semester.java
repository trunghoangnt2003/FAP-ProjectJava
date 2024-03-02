/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author trung
 */
public class Semester {
    private int idSemester;
    private String codeSemester;
    private String nameSemester;
    private String dateStart,dateEnd;

    public Semester(int idSemester, String codeSemester, String nameSemester, String dateStart, String dateEnd) {
        this.idSemester = idSemester;
        this.codeSemester = codeSemester;
        this.nameSemester = nameSemester;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    

    public Semester() {
    }

    public int getIdSemester() {
        return idSemester;
    }

    public void setIdSemester(int idSemester) {
        this.idSemester = idSemester;
    }

    public String getCodeSemester() {
        return codeSemester;
    }

    public void setCodeSemester(String codeSemester) {
        this.codeSemester = codeSemester;
    }

    public String getNameSemester() {
        return nameSemester;
    }

    public void setNameSemester(String nameSemester) {
        this.nameSemester = nameSemester;
    }
    
    
}
