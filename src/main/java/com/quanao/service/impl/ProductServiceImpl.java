package com.quanao.service.impl;

import java.util.List;
import com.quanao.dao.ProductDao;
import com.quanao.dao.impl.ProductDaoImpl;
import com.quanao.model.Product;
import com.quanao.service.ProductService;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public void insert(Product product) {
        productDao.insert(product);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(int id) {
        productDao.delete(id);
    }

    @Override
    public Product getById(int id) {
        return productDao.getById(id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public List<Product> getTop10Newest() {
        return productDao.getTop10Newest();
    }

    @Override
    public List<Product> getPaginated(int page, int pageSize) {
        return productDao.getPaginated(page, pageSize);
    }

    @Override
    public int countTotalProducts() {
        return productDao.countTotalProducts();
    }

    @Override
    public int getTotalPages(int pageSize) {
        int total = countTotalProducts();
        if (total == 0) return 1;
        return (int) Math.ceil((double) total / pageSize);
    }

    @Override
    public List<Product> search(String keyword) {
        return productDao.search(keyword);
    }

    @Override
    public List<Product> getByCategory(int cateId) {
        return productDao.getByCategory(cateId);
    }
}
