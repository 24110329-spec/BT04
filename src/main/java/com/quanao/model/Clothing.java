package com.quanao.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "clothing")
public class Clothing implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clothing_id")
    private int clothingId;

    @Column(name = "clothing_name", nullable = false, length = 150)
    private String clothingName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cate_id")
    private Category category;

    // Compatibility fields used by the existing controllers/JSP.
    @Transient private int cateId;
    @Transient private String categoryName;

    @Column(name = "size", length = 20) private String size;
    @Column(name = "color", length = 50) private String color;
    @Column(name = "quantity") private int quantity;
    @Column(name = "price") private double price;
    @Column(name = "image", length = 255) private String image;
    @Column(name = "description", columnDefinition = "TEXT") private String description;
    @Column(name = "created_date") private Timestamp createdDate;

    public Clothing() {}

    public Clothing(int clothingId, String clothingName, int cateId, String size, String color,
                     int quantity, double price, String image, String description, Timestamp createdDate) {
        this.clothingId = clothingId;
        this.clothingName = clothingName;
        this.cateId = cateId;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
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

    public int getClothingId() { return clothingId; }
    public void setClothingId(int clothingId) { this.clothingId = clothingId; }
    @Transient public int getId() { return clothingId; }
    public void setId(int id) { this.clothingId = id; }
    public String getClothingName() { return clothingName; }
    public void setClothingName(String clothingName) { this.clothingName = clothingName; }

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

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Timestamp getCreatedDate() { return createdDate; }
    public void setCreatedDate(Timestamp createdDate) { this.createdDate = createdDate; }
}
