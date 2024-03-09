/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.AuthenticationServlet;
import database.GroupDAO;
import database.LessionDAO;
import database.SemesterDAO;
import database.StatusDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Group;
import model.Lession;
import model.Semester;
import model.Status;
import model.Student;
import model.User;

/**
 *
 * @author trung
 */
public class AttReport extends AuthenticationServlet{


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp, User user) throws ServletException, IOException {
        String rollName = request.getParameter("rollName");
        int idSemester = Integer.parseInt(request.getParameter("idSemester"));
        boolean check = true;
        if (rollName == null) {
            Student student = (Student) user;
            SemesterDAO semesterDAO = new SemesterDAO();
            ArrayList<Semester> semesters = semesterDAO.selectAll();
            GroupDAO groupDAO = new GroupDAO();
            ArrayList<Group> groups = groupDAO.selectAllByIdStudentAndIdSemester(student.getId(), idSemester);
            request.setAttribute("semesters", semesters);
            request.setAttribute("groups", groups);
            request.setAttribute("check", check);

        }else{
            Student student = (Student) user;
            SemesterDAO semesterDAO = new SemesterDAO();
            ArrayList<Semester> semesters = semesterDAO.selectAll();
            GroupDAO groupDAO = new GroupDAO();
            ArrayList<Group> groups = groupDAO.selectAllByIdStudentAndIdSemester(student.getId(), idSemester);
            request.setAttribute("semesters", semesters);
            request.setAttribute("groups", groups);
            check = false;
            request.setAttribute("check", check);
            
            int idGroup =  Integer.parseInt(request.getParameter("group"));
            Group group = groupDAO.selectIdGroup(idGroup);
            request.setAttribute("group", group);
            StatusDAO statusDAO = new StatusDAO();
            ArrayList<Status> status = statusDAO.selectByIdGroupAndIdStudent(idGroup, rollName);
            request.setAttribute("status", status);
        }
        request.getRequestDispatcher("../StudentView/attendanceStatus.jsp").forward(request, resp);
        
    }
    
}
