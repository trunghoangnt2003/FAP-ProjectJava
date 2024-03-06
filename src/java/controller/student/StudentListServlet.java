/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;


import database.inGroupDAO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Student;


/**
 *
 * @author trung
 */
public class StudentListServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idGroup = req.getParameter("idGroup");
        int id = Integer.parseInt(idGroup);
        inGroupDAO groupDAO = new inGroupDAO();
        ArrayList<Student> list = groupDAO.selectStudentsByIdGroup(id);
        req.setAttribute("list", list);
        req.getRequestDispatcher("../StudentView/studentList.jsp").forward(req, resp);
    }
    
}
