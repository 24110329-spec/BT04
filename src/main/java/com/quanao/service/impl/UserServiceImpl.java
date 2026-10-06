package com.quanao.service.impl;

import java.sql.Date;
import java.util.List;

import com.quanao.dao.UserDao;
import com.quanao.dao.impl.UserDaoImpl;
import com.quanao.model.User;
import com.quanao.service.UserService;

import com.quanao.util.EmailUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User user = this.get(username);
        if (user == null) {
            user = this.getByEmail(username);
        }
        if (user != null && password != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;
    }

    @Override
    public User get(String username) {
        return userDao.get(username);
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }
    @Override
    public void update(User user) {
        userDao.update(user);
    }
    @Override
    public boolean register(String username, String password, String email, String fullname, String phone) {
        return registerWithOTP(username, password, email, fullname, phone) != null;
    }

    @Override
    public String registerWithOTP(String username, String password, String email, String fullname, String phone) {
        if (userDao.checkExistUsername(username) || userDao.checkExistEmail(email)) {
            return null;
        }
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        String otp = EmailUtil.generateOTP();
        // Default roleid = 3 (Khách hàng / User), status = 0 (Unactivated), code = OTP
        User newUser = new User(email, username, fullname, password, null, 3, phone, 0, otp, date);
        userDao.insert(newUser);

        // Send OTP email
        EmailUtil.sendOTPEmail(email, otp, "Xác thực kích hoạt tài khoản - Quản Lý Quần Áo");
        return otp;
    }

    @Override
    public boolean activateUser(String email, String code) {
        return userDao.activateUser(email, code);
    }

    @Override
    public boolean sendForgotPasswordOTP(String email) {
        User user = userDao.getByEmail(email);
        if (user == null) {
            return false;
        }
        String otp = EmailUtil.generateOTP();
        boolean updated = userDao.updateCode(email, otp);
        if (updated) {
            EmailUtil.sendOTPEmail(email, otp, "Mã xác nhận OTP Quên Mật Khẩu - Quản Lý Quần Áo");
            return true;
        }
        return false;
    }

    @Override
    public boolean resetPassword(String email, String code, String newPassword) {
        User user = userDao.getByEmail(email);
        if (user != null && code != null && code.equals(user.getCode())) {
            return userDao.updatePassword(email, newPassword);
        }
        return false;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }

    @Override
    public boolean checkExistPhoneExceptUser(String phone, int userId) {
        return userDao.checkExistPhoneExceptUser(phone, userId);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
    
}