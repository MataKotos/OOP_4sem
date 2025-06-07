package com.omgtu.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omgtu.model.Bouquet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BouquetService {

    private final File jsonFile = new File("bouquets.json");
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Bouquet> loadBouquets() {
        if (!jsonFile.exists()) return new ArrayList<>();

        try {
            return mapper.readValue(jsonFile, new TypeReference<List<Bouquet>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveBouquet(Bouquet bouquet) {
        List<Bouquet> bouquets = loadBouquets();
        bouquets.add(bouquet);
        saveAll(bouquets);
    }

    private void saveAll(List<Bouquet> bouquets) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, bouquets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
