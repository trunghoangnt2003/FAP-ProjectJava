/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student;

import controller.AuthenticationServlet;
import database.CategoryDAO;
import database.GroupDAO;
import database.PointDAO;
import database.ScoreDAO;
import database.SemesterDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Category;
import model.Group;
import model.Point;
import model.Score;
import model.Semester;
import model.Student;
import model.User;

/**
 *
 * @author trung
 */
public class ScoreServlet extends AuthenticationServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        doGet(req, resp, user);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String rollName = request.getParameter("rollName");
        int idSemester = Integer.parseInt(request.getParameter("idSemester"));
        boolean check = true;
        if (rollName == null) {
            Student student = (Student) user;
            SemesterDAO semesterDAO = new SemesterDAO();
            ArrayList<Semester> semesters = semesterDAO.selectAll();
            GroupDAO groupDAO = new GroupDAO();
            ArrayList<Group> group = groupDAO.selectAllByIdStudentAndIdSemester(student.getId(), idSemester);
            request.setAttribute("semesters", semesters);
            request.setAttribute("group", group);
            request.setAttribute("check", check);

        } else {
            String idGroup = request.getParameter("group");
            int id = Integer.parseInt(idGroup);
            check = false;
            GroupDAO groupDAO = new GroupDAO();
            Group groupNow = groupDAO.selectIdGroup(id);
            ArrayList<Group> group = groupDAO.selectAllByIdStudentAndIdSemester(rollName, idSemester);
            request.setAttribute("group", group);
            request.setAttribute("check", check);
            ScoreDAO scoreDAO = new ScoreDAO();
            ArrayList<Score> scores = scoreDAO.selectScore(id, rollName);
            request.setAttribute("scores", scores);
            SemesterDAO semesterDAO = new SemesterDAO();
            ArrayList<Semester> semesters = semesterDAO.selectAll();
            request.setAttribute("semesters", semesters);
            PointDAO pointDAO = new PointDAO();
            ArrayList<Point> point = pointDAO.selectScore(groupNow.getCourse().getIdCourse());
            request.setAttribute("point", point);
            CategoryDAO categoryDAO = new CategoryDAO();
            ArrayList<Category> category = categoryDAO.selectCategoryByIdCourse(groupNow.getCourse().getIdCourse());
            request.setAttribute("category", category);
        }

        RequestDispatcher rd = request.getRequestDispatcher("../StudentView/score.jsp");
        rd.forward(request, response);
    }

}
