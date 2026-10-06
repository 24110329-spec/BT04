package com.quanao.dao.impl;

import com.quanao.dao.UserDao;
import com.quanao.jpa.JPAUtil;
import com.quanao.model.User;
import jakarta.persistence.EntityManager;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User get(String username) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.userName = :username", User.class)
                    .setParameter("username", username)
                    .getResultStream().findFirst().orElse(null);
        } catch (Exception e) { e.printStackTrace(); return null; }
        finally { em.close(); }
    }

    @Override
    public User getById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try { return em.find(User.class, id); }
        catch (Exception e) { e.printStackTrace(); return null; }
        finally { em.close(); }
    }

    @Override
    public void insert(User user) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            rollback(em); e.printStackTrace();
        } finally { em.close(); }
    }

    @Override
    public boolean checkExistEmail(String email) {
        return exists("SELECT COUNT(u) FROM User u WHERE u.email = :value", email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return exists("SELECT COUNT(u) FROM User u WHERE u.userName = :value", username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) return false;
        return exists("SELECT COUNT(u) FROM User u WHERE u.phone = :value", phone);
    }

    @Override
    public boolean checkExistPhoneExceptUser(String phone, int userId) {
        if (phone == null || phone.trim().isEmpty()) return false;
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.phone = :phone AND u.id != :userId", Long.class)
                    .setParameter("phone", phone)
                    .setParameter("userId", userId)
                    .getSingleResult();
            return count != null && count > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
        finally { em.close(); }
    }

    private boolean exists(String jpql, String value) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Long count = em.createQuery(jpql, Long.class).setParameter("value", value).getSingleResult();
            return count != null && count > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
        finally { em.close(); }
    }

    @Override
    public User getByEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getResultStream().findFirst().orElse(null);
        } catch (Exception e) { e.printStackTrace(); return null; }
        finally { em.close(); }
    }

    @Override
    public void update(User user) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            rollback(em); e.printStackTrace();
        } finally { em.close(); }
    }

    @Override
    public boolean updateCode(String email, String code) {
        User u = getByEmail(email);
        if (u != null) {
            u.setCode(code);
            update(u);
            return true;
        }
        return false;
    }

    @Override
    public boolean activateUser(String email, String code) {
        User u = getByEmail(email);
        if (u != null && code != null && code.equals(u.getCode())) {
            u.setStatus(1);
            u.setCode(null);
            update(u);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        User u = getByEmail(email);
        if (u != null) {
            u.setPassWord(newPassword);
            u.setCode(null);
            update(u);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try { return em.createQuery("SELECT u FROM User u ORDER BY u.id ASC", User.class).getResultList(); }
        catch (Exception e) { e.printStackTrace(); return List.of(); }
        finally { em.close(); }
    }

    private void rollback(EntityManager em) {
        try { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        catch (Exception ignored) {}
    }
}
