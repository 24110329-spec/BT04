package com.quanao.service;

import java.util.List;
import com.quanao.model.Product;

public interface ProductService {
    void insert(Product product);
    void update(Product product);
    void delete(int id);
    Product getById(int id);
    List<Product> getAll();
    List<Product> getTop10Newest();
    List<Product> getPaginated(int page, int pageSize);
    int countTotalProducts();
    int getTotalPages(int pageSize);
    List<Product> search(String keyword);
    List<Product> getByCategory(int cateId);
}
