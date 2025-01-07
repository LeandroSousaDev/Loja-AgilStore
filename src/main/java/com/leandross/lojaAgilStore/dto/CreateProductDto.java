package com.leandross.lojaAgilStore.dto;

public record CreateProductDto(
        String name,
        String category,
        Integer amount,
        Integer price) {
}
