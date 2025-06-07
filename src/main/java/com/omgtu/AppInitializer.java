package com.omgtu;

import com.omgtu.servlet.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AppInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        ctx.addServlet("BouquetServlet", new BouquetServlet()).addMapping("/bouquet");
    }
}
