package com.quanao.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "email", nullable = false, unique = true, length = 100) private String email;
    @Column(name = "username", nullable = false, unique = true, length = 50) private String userName;
    @Column(name = "fullname", nullable = false, length = 100) private String fullName;
    @Column(name = "password", nullable = false, length = 255) private String passWord;
    @Column(name = "avatar", length = 255) private String avatar;
    @Column(name = "roleid", nullable = false) private int roleid;
    @Column(name = "phone", unique = true, length = 20) private String phone;
    @Column(name = "status") private int status = 0;
    @Column(name = "code", length = 10) private String code;
    @Column(name = "createddate") private Date createdDate;

    public User() {}
    public User(String email, String userName, String fullName, String passWord, String avatar,
                int roleid, String phone, Date createdDate) {
        this.email = email; this.userName = userName; this.fullName = fullName; this.passWord = passWord;
        this.avatar = avatar; this.roleid = roleid; this.phone = phone; this.createdDate = createdDate;
    }
    public User(String email, String userName, String fullName, String passWord, String avatar,
                int roleid, String phone, int status, String code, Date createdDate) {
        this(email, userName, fullName, passWord, avatar, roleid, phone, createdDate);
        this.status = status; this.code = code;
    }
    public User(int id, String email, String userName, String fullName, String passWord, String avatar,
                int roleid, String phone, Date createdDate) {
        this(email, userName, fullName, passWord, avatar, roleid, phone, createdDate); this.id = id;
    }
    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
    public String getUserName() { return userName; } public void setUserName(String userName) { this.userName = userName; }
    public String getFullName() { return fullName; } public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPassWord() { return passWord; } public void setPassWord(String passWord) { this.passWord = passWord; }
    public String getAvatar() { return avatar; } public void setAvatar(String avatar) { this.avatar = avatar; }
    public int getRoleid() { return roleid; } public void setRoleid(int roleid) { this.roleid = roleid; }
    public String getPhone() { return phone; } public void setPhone(String phone) { this.phone = phone; }
    public int getStatus() { return status; } public void setStatus(int status) { this.status = status; }
    public String getCode() { return code; } public void setCode(String code) { this.code = code; }
    public Date getCreatedDate() { return createdDate; } public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
}
