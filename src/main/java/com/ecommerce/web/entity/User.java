package com.ecommerce.web.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
public class User {
    //用户id
    @Id
    @GeneratedValue
    private String id;
    //创建时间
    private LocalDateTime gmtCreated;
    //修改时间
    private LocalDateTime gmtModifiled;
    //用户姓名
    private String name;
    //用户头像图片地址
    private String profile;
    //用户登录密码
    private String password;
    //用户性别
    private String gender;
    //用户余额
    private BigDecimal amount;
    //用户支付密码
    private String payCode;
    //用户购物车
    private String shopping;
    //用户消费记录
    private String recording;
    //用户地址
    private String address;

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", gmtCreated=" + gmtCreated +
                ", gmtModifiled=" + gmtModifiled +
                ", name='" + name + '\'' +
                ", profile='" + profile + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", amount=" + amount +
                ", payCode='" + payCode + '\'' +
                ", shopping='" + shopping + '\'' +
                ", recording='" + recording + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public LocalDateTime getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(LocalDateTime gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public LocalDateTime getGmtModifiled() {
        return gmtModifiled;
    }

    public void setGmtModifiled(LocalDateTime gmtModifiled) {
        this.gmtModifiled = gmtModifiled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getShopping() {
        return shopping;
    }

    public void setShopping(String shopping) {
        this.shopping = shopping;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
