/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

/**
 *
 * @author trung
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         HttpServletRequest httpRequest = (HttpServletRequest) request;
         HttpSession session = httpRequest.getSession();
         User user = null;
         Object obj = session.getAttribute("user");
         if(obj!=null) user = (User)obj;
        // Kiểm tra đường dẫn yêu cầu
        String path = httpRequest.getRequestURI();
        if (path.equals("/fap/index.jsp")) {
            chain.doFilter(request, response);
            return;
        }
        if (path.equals("/fap/login")||path.contains("/fap/css")||path.contains("/fap/js")) {
            chain.doFilter(request, response);
            return;
        }

        if (user!=null) {
            chain.doFilter(request, response);
        } else {
            
            ((HttpServletResponse) response).sendRedirect("/fap/index.jsp");
            
        }
    }   

    @Override
    public void destroy() {
        Filter.super.destroy(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
}
