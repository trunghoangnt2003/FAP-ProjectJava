/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.lecture;

import controller.AuthenticationServlet;
import database.LessionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import model.Lecture;
import model.Lession;
import model.User;
import java.util.Locale;
import model.Week;

/**
 *
 * @author trung
 */
public class ScheduleLectureServlet extends AuthenticationServlet {

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
        if (week == null) {
            week = date.format(formatter) + "";
        }
        String s[] = week.split("\\/");
        int day = Integer.parseInt(s[0]);
        int month = Integer.parseInt(s[1]);

        // Chọn tất cả các tuần của năm và lưu vào danh sách chuỗi
        List<Week> weeksOfYear = new ArrayList<>();
        while (date.getYear() == Integer.parseInt(year)) {
            String weekOfYear = date.format(formatter) + "";
            LocalDate date2 = date.plusDays(6);
            String detailWeek = date.format(formatter) + " - " + date2.format(formatter);
            Week week1 = new Week();
            week1.setValue(weekOfYear);
            week1.setDetail(detailWeek);
            weeksOfYear.add(week1);
            date = date.plusWeeks(1);
        }

        LocalDate date2 = LocalDate.of(Integer.parseInt(year), month, day);
        LessionDAO lessionDAO = new LessionDAO();
        Lecture lecture = (Lecture) user;

        LocalDate date3 = date2.plusDays(1);
        LocalDate date4 = date2.plusDays(2);
        LocalDate date5 = date2.plusDays(3);
        LocalDate date6 = date2.plusDays(4);
        LocalDate date7 = date2.plusDays(5);
        LocalDate date8 = date2.plusDays(6);
        ArrayList<Lession> list2 = lessionDAO.selectLessionByIdLectureAndDay(lecture.getId(), date2 + "");
        ArrayList<Lession> list3 = lessionDAO.selectLessionByIdLectureAndDay(lecture.getId(), date3 + "");
        ArrayList<Lession> list4 = lessionDAO.selectLessionByIdLectureAndDay(lecture.getId(), date4 + "");
        ArrayList<Lession> list5 = lessionDAO.selectLessionByIdLectureAndDay(lecture.getId(), date5 + "");
        ArrayList<Lession> list6 = lessionDAO.selectLessionByIdLectureAndDay(lecture.getId(), date6 + "");
        ArrayList<Lession> list7 = lessionDAO.selectLessionByIdLectureAndDay(lecture.getId(), date7 + "");
        ArrayList<Lession> list8 = lessionDAO.selectLessionByIdLectureAndDay(lecture.getId(), date8 + "");
        req.setAttribute("week", week);
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

        req.getRequestDispatcher("../LecturersView/schedule.jsp").forward(req, resp);
    }

}
