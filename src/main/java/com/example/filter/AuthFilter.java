package com.example.filter;

import com.example.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/user/*"
})
public class AuthFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String user = (String) httpServletRequest.getSession(false).getAttribute("user");
        if (user == null || !Users.getInstance().getUsers().contains(user)) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }

    }
}