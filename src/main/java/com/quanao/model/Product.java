package com.quanao.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name", nullable = false, length = 150)
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cate_id")
    private Category category;

    @Transient private int cateId;
    @Transient private String categoryName;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_date")
    private Timestamp createdDate;

    public Product() {}

    public Product(int productId, String productName, Category category, double price, int quantity, String image, String description, Timestamp createdDate) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.createdDate = createdDate;
    }

    @PostLoad
    private void syncCompatibilityFields() {
        if (category != null) {
            cateId = category.getCateid();
            categoryName = category.getCatename();
        }
    }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    @Transient public int getId() { return productId; }
    public void setId(int id) { this.productId = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) {
        this.category = category;
        if (category != null) {
            this.cateId = category.getCateid();
            this.categoryName = category.getCatename();
        }
    }

    @Transient public int getCateId() {
        return category != null ? category.getCateid() : cateId;
    }
    public void setCateId(int cateId) { this.cateId = cateId; }

    @Transient public String getCategoryName() {
        return category != null ? category.getCatename() : categoryName;
    }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Timestamp getCreatedDate() { return createdDate; }
    public void setCreatedDate(Timestamp createdDate) { this.createdDate = createdDate; }
}
