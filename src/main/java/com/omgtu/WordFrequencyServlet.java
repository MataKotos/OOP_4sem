package com.omgtu;

import jakarta.servlet.http.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.*;

public class WordFrequencyServlet extends HttpServlet {

    private static final String FILE_PATH = "/WEB-INF/words.txt";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String searchWord = request.getParameter("word");

        if (searchWord == null || searchWord.isBlank()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Parameter 'word' is required.");
            return;
        }

        // Нормализуем искомое слово
        String wordToFind = searchWord.toLowerCase();
        int frequency = 0;

        try (InputStream input = getServletContext().getResourceAsStream(FILE_PATH);
             BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {

            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(wordToFind) + "\\b", Pattern.CASE_INSENSITIVE);
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line.toLowerCase());
                while (matcher.find()) {
                    frequency++;
                }
            }

        } catch (IOException | NullPointerException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error reading the file.");
            return;
        }

        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().write("Частота слова '" + searchWord + "': " + frequency);
    }
}

