/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.GroupDAO;
import database.LessionDAO;
import database.StatusDAO;
import database.inGroupDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import model.Group;
import model.Lession;
import model.Student;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.Status;
import model.User;

/**
 *
 * @author trung
 */
public class TakeAttendance extends AuthenticationServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String[] ids = req.getParameterValues("id");
        int idLession = Integer.parseInt(req.getParameter("idLession"));
        LessionDAO lessionDAO = new LessionDAO();
        lessionDAO.updateLession(idLession, 1);
        StatusDAO statusDAO = new StatusDAO();
        String reTake = req.getParameter("reTake");
        LocalDateTime now = LocalDateTime.now();
        if (reTake != null) {
            for (String id : ids) {
                
                Timestamp timestamp = Timestamp.valueOf(now);
                int status = Integer.parseInt(req.getParameter(id));
                statusDAO.updateStatusByIdStudentAndIdLessionReTake(id, timestamp, idLession, status);
            }
        } else {
            for (String id : ids) {
               
                Timestamp timestamp = Timestamp.valueOf(now);
                int status = Integer.parseInt(req.getParameter(id));
                statusDAO.updateStatusByIdStudentAndIdLession(id, timestamp, idLession, status);
            }
        }
        LocalDate currentDate = LocalDate.now();

        LocalDate monday = currentDate.with(DayOfWeek.MONDAY);
        String month = "";
        if(monday.getMonthValue()<=9){
            month="0"+monday.getMonthValue();
        }else {
            month=monday.getMonthValue()+"";
        }
        //req.getRequestDispatcher("schedule?year=2024&week=05%2F02").forward(req, resp);
        String redirectUrl = "schedule?year="+monday.getYear()+"&week="+monday.getDayOfMonth()+"%2F"+month;
        resp.sendRedirect(redirectUrl);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String id = req.getParameter("id");
        int idLession = Integer.parseInt(id);
        inGroupDAO inGroupDAO = new inGroupDAO();
        LessionDAO lessionDAO = new LessionDAO();
        String reTake = req.getParameter("reTake");
        Lession lession = lessionDAO.selectLessionById(idLession);
        if (reTake != null) {
            lession.setStatus(0);
        }
        ArrayList<Student> list = inGroupDAO.selectStudentsByIdGroup(lession.getGroup().getIdGroup());
        StatusDAO statusDAO = new StatusDAO();
        ArrayList<Status> status = statusDAO.selectByIdLession(idLession);
        req.setAttribute("status", status);
        req.setAttribute("list", list);
        req.setAttribute("lession", lession);
        req.setAttribute("reTake", reTake);

        req.getRequestDispatcher("../LecturersView/attendance.jsp").forward(req, resp);
    }

}
