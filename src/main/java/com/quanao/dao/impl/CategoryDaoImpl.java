package com.quanao.dao.impl;

import com.quanao.dao.CategoryDao;
import com.quanao.jpa.JPAUtil;
import com.quanao.model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public void insert(Category category) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        } catch (Exception e) {
            rollback(em); e.printStackTrace();
        } finally { em.close(); }
    }

    @Override
    public void edit(Category category) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(category);
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
            Category category = em.find(Category.class, id);
            if (category != null) em.remove(category);
            em.getTransaction().commit();
        } catch (Exception e) {
            rollback(em); e.printStackTrace();
        } finally { em.close(); }
    }

    @Override
    public Category get(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try { return em.find(Category.class, id); }
        catch (Exception e) { e.printStackTrace(); return null; }
        finally { em.close(); }
    }

    @Override
    public Category get(String name) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Category> q = em.createQuery(
                    "SELECT c FROM Category c WHERE c.catename = :name", Category.class);
            q.setParameter("name", name);
            return q.getResultStream().findFirst().orElse(null);
        } catch (Exception e) { e.printStackTrace(); return null; }
        finally { em.close(); }
    }

    @Override
    public List<Category> getAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Category c ORDER BY c.cateid ASC", Category.class)
                    .getResultList();
        } catch (Exception e) { e.printStackTrace(); return List.of(); }
        finally { em.close(); }
    }

    @Override
    public List<Category> search(String keyword) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT c FROM Category c WHERE LOWER(c.catename) LIKE LOWER(:keyword) ORDER BY c.cateid ASC",
                    Category.class)
                    .setParameter("keyword", "%" + (keyword == null ? "" : keyword) + "%")
                    .getResultList();
        } catch (Exception e) { e.printStackTrace(); return List.of(); }
        finally { em.close(); }
    }

    private void rollback(EntityManager em) {
        try { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        catch (Exception ignored) {}
    }
}
