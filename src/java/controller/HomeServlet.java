/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

/**
 *
 * @author trung
 */
public class HomeServlet extends AuthenticationServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String url = "";
        if(user.getEmail().contains("fpt")){
            url = "StudentView/HomeOfStudent.jsp";
        }else {
            url = "LecturersView/Home.jsp";
        }
        req.getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String url = "";
        if(user.getEmail().contains("fpt")){
            url = "StudentView/HomeOfStudent.jsp";
        }else {
            url = "LecturersView/Home.jsp";
        }
        req.getRequestDispatcher(url).forward(req, resp);
        
    }
    
    
    
    
}
