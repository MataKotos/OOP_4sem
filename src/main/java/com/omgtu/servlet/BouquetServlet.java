package com.omgtu.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/bouquets")
public class BouquetServlet extends HttpServlet {

    private final File jsonFile = new File("bouquets.json");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");


        if (!jsonFile.exists()) {
            resp.getWriter().write("[]");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                resp.getWriter().println(line);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder body = new StringBuilder();
        req.setCharacterEncoding("UTF-8");

        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        }

        String newItemJson = body.toString();
        List<String> items = readJsonArrayFromFile();

        items.add(newItemJson);
        writeJsonArrayToFile(items);

        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    private List<String> readJsonArrayFromFile() throws IOException {
        List<String> items = new ArrayList<>();
        if (!jsonFile.exists()) return items;

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }

        String jsonArray = content.toString().trim();
        if (!jsonArray.startsWith("[") || !jsonArray.endsWith("]")) return items;

        String[] jsonItems = jsonArray.substring(1, jsonArray.length() - 1).split("},\\s*\\{");
        for (int i = 0; i < jsonItems.length; i++) {
            String item = jsonItems[i];
            if (!item.startsWith("{")) item = "{" + item;
            if (!item.endsWith("}")) item = item + "}";
            items.add(item);
        }
        return items;
    }

    private void writeJsonArrayToFile(List<String> items) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile, StandardCharsets.UTF_8))) {
            writer.write("[\n");
            for (int i = 0; i < items.size(); i++) {
                writer.write(items.get(i));
                if (i != items.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]");
        }
    }
}
