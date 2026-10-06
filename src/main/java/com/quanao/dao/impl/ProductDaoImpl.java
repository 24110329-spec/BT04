package com.quanao.dao.impl;

import com.quanao.dao.ProductDao;
import com.quanao.jpa.JPAUtil;
import com.quanao.model.Product;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public void insert(Product product) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            rollback(em);
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Product product) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            rollback(em);
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Product p = em.find(Product.class, id);
            if (p != null) {
                em.remove(p);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            rollback(em);
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Product getById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product p LEFT JOIN FETCH p.category WHERE p.productId = :id", Product.class)
                    .setParameter("id", id)
                    .getResultStream().findFirst().orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> getAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product p LEFT JOIN FETCH p.category ORDER BY p.productId DESC", Product.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> getTop10Newest() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product p LEFT JOIN FETCH p.category ORDER BY p.productId DESC", Product.class)
                    .setMaxResults(10)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> getPaginated(int page, int pageSize) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            int offset = Math.max(0, (page - 1) * pageSize);
            return em.createQuery("SELECT p FROM Product p LEFT JOIN FETCH p.category ORDER BY p.productId DESC", Product.class)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        } finally {
            em.close();
        }
    }

    @Override
    public int countTotalProducts() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(p) FROM Product p", Long.class).getSingleResult();
            return count != null ? count.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> search(String keyword) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product p LEFT JOIN FETCH p.category WHERE LOWER(p.productName) LIKE :kw OR LOWER(p.description) LIKE :kw ORDER BY p.productId DESC", Product.class)
                    .setParameter("kw", "%" + keyword.toLowerCase() + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> getByCategory(int cateId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Product p LEFT JOIN FETCH p.category WHERE p.category.cateid = :cateId ORDER BY p.productId DESC", Product.class)
                    .setParameter("cateId", cateId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        } finally {
            em.close();
        }
    }

    private void rollback(EntityManager em) {
        try {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } catch (Exception ignored) {}
    }
}
