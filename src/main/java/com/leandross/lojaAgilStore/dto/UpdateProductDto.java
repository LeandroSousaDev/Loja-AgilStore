package com.leandross.lojaAgilStore.dto;

public record UpdateProductDto(
        String name,
        String category,
        Integer amount,
        Integer price) {
}
