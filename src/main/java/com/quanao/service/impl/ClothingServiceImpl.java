package com.quanao.service.impl;

import java.io.File;
import java.util.List;

import com.quanao.dao.ClothingDAO;
import com.quanao.dao.impl.ClothingDAOImpl;
import com.quanao.model.Clothing;
import com.quanao.service.ClothingService;
import com.quanao.util.Constant;

public class ClothingServiceImpl implements ClothingService {

    private ClothingDAO clothingDao = new ClothingDAOImpl();

    @Override
    public void insert(Clothing clothing) {
        clothingDao.insert(clothing);
    }

    @Override
    public void edit(Clothing newClothing) {
        Clothing oldClothing = clothingDao.get(newClothing.getClothingId());
        if (oldClothing != null) {
            if (newClothing.getImage() != null && !newClothing.getImage().isEmpty()) {
                // Xoa anh cu neu co
                if (oldClothing.getImage() != null) {
                    File file = new File(Constant.DIR + "/" + oldClothing.getImage());
                    if (file.exists()) {
                        file.delete();
                    }
                }
            } else {
                // Giu nguyen anh cu
                newClothing.setImage(oldClothing.getImage());
            }
            clothingDao.edit(newClothing);
        }
    }

    @Override
    public void update(Clothing clothing) {
        edit(clothing);
    }

    @Override
    public void delete(int id) {
        Clothing oldClothing = clothingDao.get(id);
        if (oldClothing != null && oldClothing.getImage() != null) {
            File file = new File(Constant.DIR + "/" + oldClothing.getImage());
            if (file.exists()) {
                file.delete();
            }
        }
        clothingDao.delete(id);
    }

    @Override
    public Clothing get(int id) {
        return clothingDao.get(id);
    }

    @Override
    public Clothing getById(int id) {
        return clothingDao.getById(id);
    }

    @Override
    public List<Clothing> getAll() {
        return clothingDao.getAll();
    }

    @Override
    public List<Clothing> getByCategory(int cateId) {
        return clothingDao.getByCategory(cateId);
    }

    @Override
    public List<Clothing> search(String keyword) {
        return clothingDao.search(keyword);
    }
}