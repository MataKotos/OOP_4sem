package com.omgtu;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AppInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        sce.getServletContext()
                .addServlet("wordFrequencyServlet", new WordFrequencyServlet())
                .addMapping("/word-frequency");
    }
}