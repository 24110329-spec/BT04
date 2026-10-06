package com.quanao.dao.impl;

import com.quanao.dao.ClothingDAO;
import com.quanao.jpa.JPAUtil;
import com.quanao.model.Category;
import com.quanao.model.Clothing;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ClothingDAOImpl implements ClothingDAO {
    @Override
    public void insert(Clothing clothing) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            attachCategory(em, clothing);
            em.persist(clothing);
            em.getTransaction().commit();
        } catch (Exception e) {
            rollback(em); e.printStackTrace();
        } finally { em.close(); }
    }

    @Override
    public void edit(Clothing clothing) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            attachCategory(em, clothing);
            em.merge(clothing);
            em.getTransaction().commit();
        } catch (Exception e) {
            rollback(em); e.printStackTrace();
        } finally { em.close(); }
    }

    @Override
    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Clothing clothing = em.find(Clothing.class, id);
            if (clothing != null) em.remove(clothing);
            em.getTransaction().commit();
        } catch (Exception e) {
            rollback(em); e.printStackTrace();
        } finally { em.close(); }
    }

    @Override public Clothing get(int id) { return getById(id); }

    @Override
    public Clothing getById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Clothing c LEFT JOIN FETCH c.category WHERE c.clothingId = :id", Clothing.class)
                    .setParameter("id", id).getResultStream().findFirst().orElse(null);
        } catch (Exception e) { e.printStackTrace(); return null; }
        finally { em.close(); }
    }

    @Override
    public List<Clothing> getAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT c FROM Clothing c LEFT JOIN FETCH c.category ORDER BY c.clothingId DESC", Clothing.class)
                    .getResultList();
        } catch (Exception e) { e.printStackTrace(); return List.of(); }
        finally { em.close(); }
    }

    @Override
    public List<Clothing> getByCategory(int cateId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT c FROM Clothing c LEFT JOIN FETCH c.category " +
                    "WHERE c.category.cateid = :cateId ORDER BY c.clothingId DESC", Clothing.class)
                    .setParameter("cateId", cateId)
                    .getResultList();
        } catch (Exception e) { e.printStackTrace(); return List.of(); }
        finally { em.close(); }
    }

    @Override
    public List<Clothing> search(String keyword) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT c FROM Clothing c LEFT JOIN FETCH c.category " +
                    "WHERE LOWER(c.clothingName) LIKE LOWER(:keyword) ORDER BY c.clothingId DESC",
                    Clothing.class)
                    .setParameter("keyword", "%" + (keyword == null ? "" : keyword) + "%")
                    .getResultList();
        } catch (Exception e) { e.printStackTrace(); return List.of(); }
        finally { em.close(); }
    }

    private void attachCategory(EntityManager em, Clothing clothing) {
        if (clothing.getCateId() > 0) {
            Category category = em.find(Category.class, clothing.getCateId());
            if (category == null) throw new IllegalArgumentException("Không tồn tại category id=" + clothing.getCateId());
            clothing.setCategory(category);
        } else {
            clothing.setCategory(null);
        }
    }

    private void rollback(EntityManager em) {
        try { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        catch (Exception ignored) {}
    }
}
