package com.quanao.service;

import java.util.List;
import com.quanao.model.User;

public interface UserService {
    User login(String username, String password);
    User get(String username);
    User getById(int id);
    User getByEmail(String email);
    void insert(User user);
    boolean register(String username, String password, String email, String fullname, String phone);
    String registerWithOTP(String username, String password, String email, String fullname, String phone);
    boolean activateUser(String email, String code);
    boolean sendForgotPasswordOTP(String email);
    boolean resetPassword(String email, String code, String newPassword);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    boolean checkExistPhoneExceptUser(String phone, int userId);
    void update(User user);
    List<User> getAll();
}