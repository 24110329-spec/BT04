package com.quanao.service;

import java.util.List;
import com.quanao.model.Clothing;

public interface ClothingService {
    void insert(Clothing clothing);
    void edit(Clothing clothing);
    void update(Clothing clothing);
    void delete(int id);
    Clothing get(int id);
    Clothing getById(int id);
    List<Clothing> getAll();
    List<Clothing> getByCategory(int cateId);
    List<Clothing> search(String keyword);
}