/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Student;
import units.SHA1;

/**
 *
 * @author trung
 */
public class ChangePassWord extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String passWord = req.getParameter("password1");
        StudentDAO studentDAO = new StudentDAO();
        ArrayList<Student>students = studentDAO.selectAll();
        for(Student student:students){
            if(SHA1.toSHA1(student.getEmail()).equals(email)){
                System.out.println("Change Pass : "+student.getEmail());
                studentDAO.updatePassWord(student.getId(), SHA1.toSHA1(passWord));
                break;
            }
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("PassWord/ChangePassWord.jsp").forward(req, resp);
    }
    
}
