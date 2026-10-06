package com.quanao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/** Central JPA bootstrap/helper for the web application. */
public final class JPAUtil {
    private static volatile EntityManagerFactory emf;

    private JPAUtil() {}

    public static EntityManager getEntityManager() {
        if (emf == null || !emf.isOpen()) {
            synchronized (JPAUtil.class) {
                if (emf == null || !emf.isOpen()) {
                    try {
                        emf = Persistence.createEntityManagerFactory("QuanLyQuanAoPU");
                    } catch (Throwable ex) {
                        System.err.println("Lỗi khởi tạo EntityManagerFactory: " + ex.getMessage());
                        ex.printStackTrace();
                        throw new RuntimeException("Không thể kết nối CSDL hoặc khởi tạo JPA. Vui lòng kiểm tra lại cấu hình MySQL trong persistence.xml", ex);
                    }
                }
            }
        }
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
