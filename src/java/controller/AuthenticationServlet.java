/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.LectureDAO;
import database.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Lecture;
import model.Student;
import model.User;

/**
 *
 * @author trung
 */
public abstract class AuthenticationServlet extends HttpServlet {

    private User getAuthentication(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                String email = null;
                String passWord = null;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("email")) {
                        email = cookie.getValue();
                    }
                    if (cookie.getName().equals("passWord")) {
                        passWord = cookie.getValue();
                    }
                    if (email != null && passWord != null) {
                        break;
                    }
                }
                if (email == null || passWord == null) {
                    return null;
                } else {
                    User test = null;
                    if (email.contains("fu")) {
                        LectureDAO lectureDAO = new LectureDAO();
                        user = (Lecture) lectureDAO.getLogin(email, passWord);
                    } else {
                        StudentDAO studentDAO = new StudentDAO();
                        user = (Student) studentDAO.getLogin(email, passWord);
                    }
                    if (test != null) {
                        session.setAttribute("account", test);
                    }
                    return test;
                }
            }
        }
        return user;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAuthentication(req);
        if (user != null) {
            doPost(req, resp, user);
        } else {
            resp.sendRedirect("../login");
        }
    }

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAuthentication(req);
        if (user != null) {
            doGet(req, resp, user);
        } else {
            resp.sendRedirect("../login");
        }

    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException;

}
