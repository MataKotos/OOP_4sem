package com.omgtu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omgtu.model.Bouquet;
import com.omgtu.service.BouquetService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/bouquets")
public class BouquetServlet extends HttpServlet {

    private final BouquetService bouquetService = new BouquetService();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        List<Bouquet> bouquets = bouquetService.loadBouquets();
        mapper.writeValue(resp.getWriter(), bouquets);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        Bouquet bouquet = mapper.readValue(req.getReader(), Bouquet.class);
        bouquetService.saveBouquet(bouquet);

        resp.setStatus(HttpServletResponse.SC_CREATED);
        mapper.writeValue(resp.getWriter(), bouquet);
    }
}
