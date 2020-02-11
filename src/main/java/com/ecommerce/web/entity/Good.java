package com.ecommerce.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Good {
    // 商品id
    @Id
   // @GeneratedValue
    private String id;
    //创建时间
    private LocalDateTime gmtCreated;
    //修改时间
    private LocalDateTime gmtModifiled;
    //卖家
    private int masterId;
    // 商品名称
    private String name;
    // 商品价格
    private BigDecimal price;
    //备注
    private String notice;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
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

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }
}

