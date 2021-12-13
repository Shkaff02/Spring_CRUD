package com.example.crud;

import javax.persistence.*;

@Entity
@Table(name = "supply")
public class Supply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int supplierId;
    private int productId;
    private int number;
    private int price;
    private String date;

    public Integer getId() {
        return id;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public int getProductId() {
        return productId;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
