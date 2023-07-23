package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        if (user == null) {
            try {
                response.sendRedirect("/login.jsp");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                response.sendRedirect("/user/hello.jsp");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        if (Users.getInstance().getUsers().contains(login) && !request.getParameter("password").isEmpty()) {
            request.getSession().setAttribute("user", login);
            try {
                response.sendRedirect("/user/hello.jsp");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } catch (IOException | ServletException ex) {
                ex.printStackTrace();
            }
        }
    }
}
