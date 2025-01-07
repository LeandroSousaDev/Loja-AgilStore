package com.leandross.lojaAgilStore.repository;

import com.leandross.lojaAgilStore.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findByIdProduct(Integer idProduct);

    List<ProductEntity> findByNameContaining(String name);
}
