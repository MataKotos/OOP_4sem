package com.omgtu.service;

import com.omgtu.model.Bouquet;
import com.omgtu.repo.BouquetRepo;


import java.util.List;


public class BouquetService {

    private final BouquetRepo bouquetRepo = new BouquetRepo();

    // Загрузка всех букетов из базы
    public List<Bouquet> loadBouquets() {
        return bouquetRepo.findAll();
    }

    // Сохранение одного букета в базу
    public void saveBouquet(Bouquet bouquet) {
        bouquetRepo.save(bouquet);
    }
}

