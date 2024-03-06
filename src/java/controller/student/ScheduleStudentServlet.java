/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.AuthenticationServlet;
import database.LessionDAO;
import database.StatusDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import model.Status;
import model.Student;
import model.User;
import model.Week;

/**
 *
 * @author trung
 */
public class ScheduleStudentServlet extends AuthenticationServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        doGet(req, resp, user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String year = req.getParameter("year");       
        String week = req.getParameter("week");
        LocalDate date = LocalDate.of(Integer.parseInt(year), 1, 1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        if(week==null){
            week = date.format(formatter)+"";
        }
        String s[] = week.split("\\/");
        int day = Integer.parseInt(s[0]);
        int month = Integer.parseInt(s[1]);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        
        // Chọn tất cả các tuần của năm và lưu vào danh sách chuỗi
        List<Week> weeksOfYear = new ArrayList<Week>();
        while (date.getYear() == Integer.parseInt(year)) {
            String weekOfYear = date.format(formatter)+"";
            LocalDate date2 = date.plusDays(6);
            String detailWeek = date.format(formatter)+" - "+date2.format(formatter);
            Week week1 = new Week();
            week1.setValue(weekOfYear);
            week1.setDetail(detailWeek);
            weeksOfYear.add(week1);
            date = date.plusWeeks(1);
        }
        LocalDate date2 = LocalDate.of(Integer.parseInt(year), month, day);

        LessionDAO lessionDAO = new LessionDAO();
        Student student = (Student)user;
        StatusDAO statusDAO = new StatusDAO();
        LocalDate date3 = date2.plusDays(1);
        LocalDate date4 = date2.plusDays(2);
        LocalDate date5 = date2.plusDays(3);
        LocalDate date6 = date2.plusDays(4);
        LocalDate date7 = date2.plusDays(5);
        LocalDate date8 = date2.plusDays(6);
        ArrayList<Status> list2 = statusDAO.selectByIdStudentAndDay(student.getId(),date2+"" );
        ArrayList<Status> list3 = statusDAO.selectByIdStudentAndDay(student.getId(),date3+"" );
        ArrayList<Status> list4 = statusDAO.selectByIdStudentAndDay(student.getId(),date4+"" );
        ArrayList<Status> list5 = statusDAO.selectByIdStudentAndDay(student.getId(),date5+"" );
        ArrayList<Status> list6 = statusDAO.selectByIdStudentAndDay(student.getId(),date6+"" );
        ArrayList<Status> list7 = statusDAO.selectByIdStudentAndDay(student.getId(),date7+"" );
        ArrayList<Status> list8 = statusDAO.selectByIdStudentAndDay(student.getId(),date8+"" );
        req.setAttribute("week", week);
        req.setAttribute("day", day);
        req.setAttribute("month", month);
        req.setAttribute("year", year);
        req.setAttribute("list2", list2);
        req.setAttribute("list3", list3);
        req.setAttribute("list4", list4);
        req.setAttribute("list5", list5);
        req.setAttribute("list6", list6);
        req.setAttribute("list7", list7);
        req.setAttribute("list8", list8);
        req.setAttribute("date2", date2.format(formatter));
        req.setAttribute("date3", date3.format(formatter));
        req.setAttribute("date4", date4.format(formatter));
        req.setAttribute("date5", date5.format(formatter));
        req.setAttribute("date6", date6.format(formatter));
        req.setAttribute("date7", date7.format(formatter));
        req.setAttribute("date8", date8.format(formatter));
        req.setAttribute("weeksOfYear", weeksOfYear);
        
        req.getRequestDispatcher("../StudentView/schedule.jsp").forward(req, resp);
    
    
    }
    
}
