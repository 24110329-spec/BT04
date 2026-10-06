package com.quanao.dao;

import java.util.List;
import com.quanao.model.User;

public interface UserDao {
    User get(String username);
    User getById(int id);
    User getByEmail(String email);
    void insert(User user);
    void update(User user);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    boolean checkExistPhoneExceptUser(String phone, int userId);
    List<User> getAll();
    boolean updateCode(String email, String code);
    boolean activateUser(String email, String code);
    boolean updatePassword(String email, String newPassword);
}