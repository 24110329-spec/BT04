package com.quanao.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_id")
    private int cateid;

    @Column(name = "cate_name", nullable = false, length = 100)
    private String catename;

    // The SQL schema stores the former icon value in description.
    @Column(name = "description", length = 255)
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Clothing> clothings = new ArrayList<>();

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    public Category() {}

    public Category(int cateid, String catename, String icon) {
        this.cateid = cateid;
        this.catename = catename;
        this.description = icon;
    }

    public Category(String catename, String icon) {
        this.catename = catename;
        this.description = icon;
    }

    public int getCateid() { return cateid; }
    public void setCateid(int cateid) { this.cateid = cateid; }
    public String getCatename() { return catename; }
    public void setCatename(String catename) { this.catename = catename; }

    /** Kept for compatibility with the existing JSP/controllers. */
    @Transient
    public String getIcon() { return description; }
    public void setIcon(String icon) { this.description = icon; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Clothing> getClothings() { return clothings; }
    public void setClothings(List<Clothing> clothings) { this.clothings = clothings; }
    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    @Transient public int getId() { return cateid; }
    public void setId(int id) { this.cateid = id; }
    @Transient public String getName() { return catename; }
    public void setName(String name) { this.catename = name; }
}
