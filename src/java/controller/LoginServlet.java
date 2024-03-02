package controller;


import database.LectureDAO;
import database.StudentDAO;
import database.UserDAO;
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
import units.SHA1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author trung
 */
public class LoginServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String str = req.getParameter("pass");
        String pass = SHA1.toSHA1(str);
        String warning;
        String url;
        User user = null;
        if(email.contains("fu")){
            LectureDAO  lectureDAO = new LectureDAO();
            user = (Lecture) lectureDAO.getLogin(email, pass);
        }else{
            StudentDAO studentDAO = new StudentDAO();
             user =(Student) studentDAO.getLogin(email, pass);
        }
        if(user == null){
            url = "index.jsp";
            warning = "Email or Pass Word incorect";
            req.setAttribute("warning", warning);
            req.getRequestDispatcher(url).forward(req, resp);
        }else {
            HttpSession session = req.getSession();
            if(email.contains("fu.edu.vn")){
                session.setAttribute("user", user);
            }else {
                session.setAttribute("user", user);
            }
            Cookie c_username = new Cookie("email", email);
            c_username.setMaxAge(3600*24*7);
            
            Cookie c_password = new Cookie("passWord", pass);
            c_password.setMaxAge(3600*24*7);
            
            resp.addCookie(c_username);
            resp.addCookie(c_password);
            resp.sendRedirect("home");
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
    
}
