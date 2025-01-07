package com.leandross.lojaAgilStore.dto;

public record ResponseProductDto(
        Integer idProduct,
        String name,
        String category,
        Integer amount,
        Integer price) {
}
