/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;


/**
 *
 * @author trung
 */
public class Lession {
    private int idLession;
    private Group group;
    private Lecture lecture;
    private Room room;
    private TimeSlot slot;
    private java.sql.Date date;
    private int status;

    public Lession(int idLession, Group group, Lecture lecture, Room room, TimeSlot slot, Date date) {
        this.idLession = idLession;
        this.group = group;
        this.lecture = lecture;
        this.room = room;
        this.slot = slot;
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public Lession() {
    }

    public int getIdLession() {
        return idLession;
    }

    public void setIdLession(int idLession) {
        this.idLession = idLession;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    public void setSlot(TimeSlot slot) {
        this.slot = slot;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return group.getCourse().getCodeCourse()+" at "+room.getNameRoom()+"\n"+slot.getTimeLine();
    }
    
    
}
