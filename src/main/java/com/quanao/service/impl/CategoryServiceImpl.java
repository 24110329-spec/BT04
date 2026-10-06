package com.quanao.service.impl;

import java.io.File;
import java.util.List;

import com.quanao.dao.CategoryDao;
import com.quanao.dao.impl.CategoryDaoImpl;
import com.quanao.model.Category;
import com.quanao.service.CategoryService;
import com.quanao.util.Constant;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }

    @Override
    public void edit(Category newCategory) {
        Category oldCategory = categoryDao.get(newCategory.getId());
        if (oldCategory != null) {
            oldCategory.setName(newCategory.getName());
            if (newCategory.getIcon() != null && !newCategory.getIcon().isEmpty()) {
                // Xoa anh cu di neu co
                if (oldCategory.getIcon() != null) {
                    File file = new File(Constant.DIR + "/" + oldCategory.getIcon());
                    if (file.exists()) {
                        file.delete();
                    }
                }
                oldCategory.setIcon(newCategory.getIcon());
            }
            categoryDao.edit(oldCategory);
        }
    }

    @Override
    public void delete(int id) {
        Category oldCategory = categoryDao.get(id);
        if (oldCategory != null && oldCategory.getIcon() != null) {
            File file = new File(Constant.DIR + "/" + oldCategory.getIcon());
            if (file.exists()) {
                file.delete();
            }
        }
        categoryDao.delete(id);
    }

    @Override
    public Category get(int id) {
        return categoryDao.get(id);
    }

    @Override
    public Category get(String name) {
        return categoryDao.get(name);
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public List<Category> search(String keyword) {
        return categoryDao.search(keyword);
    }
}