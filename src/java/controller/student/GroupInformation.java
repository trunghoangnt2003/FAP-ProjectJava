/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.AuthenticationServlet;
import database.LessionDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Lession;
import model.User;

/**
 *
 * @author trung
 */
public class GroupInformation extends AuthenticationServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        doGet(req, resp, user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String idLession = req.getParameter("lession");
        LessionDAO lessionDAO = new LessionDAO();
        Lession lession = lessionDAO.selectLessionById(Integer.parseInt(idLession));
        req.setAttribute("lession", lession);
        RequestDispatcher rd = req.getRequestDispatcher("../StudentView/groupInformation.jsp");
        rd.forward(req, resp);
    }
    

    
}
