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
import model.Student;
import units.Email;
import units.SHA1;


/**
 *
 * @author trung
 */
public class ResetPassWord extends HttpServlet{
    private void sendEmail(String link,String email){
        String tile = "reset your password";
        String content = "Hello,\n"
            + "You have requested to reset your password.\n"
            + "Click the link below to change your password:"
            +  link
            + "\n"
            + "Ignore this email if you do remember your password, "
            + "or you have not made the request";
        Email send = new Email();
        send.sendEmail(tile, content, email);

    
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String warning = "";
        if(email.contains("fpt.edu")){
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.getLoginGoogle(email);
            if(student==null){
                warning = "Email don't have in FAP";              
            }else {
                warning = "Check your mail!";
                String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath()+"/changePass?token="+SHA1.toSHA1(email);
                sendEmail(url, email);
            }         
        }else{
            warning = "Only change student!"; 
        }
        req.setAttribute("warning", warning);
        req.getRequestDispatcher("PassWord/RememberPassWord.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("PassWord/RememberPassWord.jsp").forward(req, resp);
    }
    
}
