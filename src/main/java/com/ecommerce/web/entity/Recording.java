package com.ecommerce.web.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Recording {
    // 记录的id
    @Id
   // @GeneratedValue
    private String id;
    // 记录的发生时间(添加入购物车的时间)
    private LocalDateTime gmtCreate;
    // 记录的修改时间（最后的修改时间为购买时间）
    private LocalDateTime gmtModifiled;
    // 买家id
    private int buyer;
    // 商品id
    private String good;
    //商品名称
    private String goodName;
    //购买数量
    private int num;
    //总价
    private BigDecimal amount;
    //是否交易完成
    private boolean isOver;

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModifiled() {
        return gmtModifiled;
    }

    public void setGmtModifiled(LocalDateTime gmtModifiled) {
        this.gmtModifiled = gmtModifiled;
    }

    public int getBuyer() {
        return buyer;
    }

    public void setBuyer(int buyer) {
        this.buyer = buyer;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }
}
