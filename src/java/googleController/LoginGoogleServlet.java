/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package googleController;

/**
 *
 * @author trung
 */
import database.LectureDAO;
import database.StudentDAO;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Lecture;
import model.Student;
import model.User;

public class LoginGoogleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginGoogleServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("login");
            dis.forward(request, response);
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GoogleUser googleUser = GoogleUtils.getUserInfo(accessToken);
            String email = googleUser.getEmail();
            response.getWriter().print(email);
            User user = null;
            if (email.contains("fu")) {
                LectureDAO lectureDAO = new LectureDAO();
                user = (Lecture) lectureDAO.getLoginGoogle(email);
            } else {
                StudentDAO studentDAO = new StudentDAO();
                user = (Student) studentDAO.getLoginGoogle(email);
            }

            if (user == null) {
                String url = "index.jsp";
                String warning = "Email Google Không Đúng! ";
                request.setAttribute("warning", warning);
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                Cookie c_username = new Cookie("email", email);
                c_username.setMaxAge(3600 * 24 * 7);

                Cookie c_password = new Cookie("passWord", user.getPassWord());
                c_password.setMaxAge(3600 * 24 * 7);

                response.addCookie(c_username);
                response.addCookie(c_password);
                //request.getRequestDispatcher("home").forward(request, response);
                response.sendRedirect("home");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
