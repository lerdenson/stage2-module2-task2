package com.example.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;

@WebListener
public class ContextListener implements ServletRequestListener {
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        servletRequestEvent.getServletContext().setAttribute("servletTimeInit", LocalDateTime.now());
    }
}
