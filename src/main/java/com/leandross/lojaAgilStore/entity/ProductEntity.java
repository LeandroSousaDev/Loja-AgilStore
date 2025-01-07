package com.leandross.lojaAgilStore.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_user")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer idProduct;

    @Column(name = "name_product")
    private String name;

    @Column(name = "category_product")
    private String category;

    @Column(name = "amount_product")
    private Integer amount;

    @Column(name = "price_product")
    private Integer price;

    public ProductEntity() {
    }

    public ProductEntity(Integer idproduct,
            String name,
            String category,
            Integer amount,
            Integer price) {
        this.idProduct = idproduct;
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.price = price;
    }
}